package view.rol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.dao.DAOFuncionalidadRol;
import model.dao.DAORol;
import model.entity.Funcionalidad;
import model.entity.Rol;
import validation.Mensajes;
import view.ViewPanel;

public class AltaRol extends ViewPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JComboBox<Funcionalidad> cmbFuncionalidades;
	private JList<Funcionalidad> listFuncionalidades;

	public AltaRol() {
		super();

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(72, 80, 84, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(72, 127, 84, 13);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Funcionalidades");
		lblNewLabel_2.setBounds(72, 170, 109, 13);
		add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(195, 77, 173, 19);
		add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(195, 124, 173, 19);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JButton btnAltaRol = new JButton("Alta Rol");
		btnAltaRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Rol r = new Rol();
				r.setNombre(txtNombre.getText());
				r.setDescripcion(txtDescripcion.getText());

				try {
					if (DAORol.insert(r) > 0) {
						Mensajes.MostrarExito("Rol agregado con exito");
					}
					
					DefaultListModel<Funcionalidad> modelo = ((DefaultListModel<Funcionalidad>) listFuncionalidades.getModel());
					for(int i = 0; i < modelo.getSize(); i++) {
						Funcionalidad f = modelo.getElementAt(i);
						DAOFuncionalidadRol.insertByName(r.getNombre(), f);
					}
					
					Mensajes.MostrarExito("Funcionalidad a los roles");
				} catch (SQLException e1) {
					Mensajes.MostrarError(e1.getMessage());
				}
			}
		});
		btnAltaRol.setBounds(72, 384, 296, 21);
		add(btnAltaRol);

		cmbFuncionalidades = new JComboBox<Funcionalidad>();
		cmbFuncionalidades.setBounds(195, 166, 173, 21);
		RolUtils.cargarTodasLasFuncionalidades(cmbFuncionalidades);
		add(cmbFuncionalidades);

		JButton btnAgregarFunc = new JButton("Agregar");
		btnAgregarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionalidad func = (Funcionalidad) cmbFuncionalidades.getSelectedItem();
				DefaultListModel<Funcionalidad> modelo = ((DefaultListModel<Funcionalidad>) listFuncionalidades.getModel());
				
				for(int i = 0; i < modelo.getSize(); i++) {
					Funcionalidad f = modelo.getElementAt(i);
					if(f == func) {
						Mensajes.MostrarError("No puede agregar una funcion que ya existe");
						return;
					}
				}
				
				modelo.addElement(func);
			}
		});
		btnAgregarFunc.setBounds(72, 209, 296, 25);
		add(btnAgregarFunc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 246, 296, 92);
		add(scrollPane);

		listFuncionalidades = new JList<Funcionalidad>();
		listFuncionalidades.setModel(new DefaultListModel<>());
		scrollPane.setViewportView(listFuncionalidades);

		RolUtils.cargarTodasLasFuncionalidades(cmbFuncionalidades);

		JButton btnBorrarFunc = new JButton("Borrar");
		btnBorrarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = listFuncionalidades.getSelectedIndex();
				if (selected == -1) {
					Mensajes.MostrarError("Seleciones una funcionalidad");
					return;
				}
				((DefaultListModel<Funcionalidad>) listFuncionalidades.getModel()).removeElementAt(selected);
			}
		});
		btnBorrarFunc.setBounds(72, 347, 296, 25);
		add(btnBorrarFunc);
	}
}
