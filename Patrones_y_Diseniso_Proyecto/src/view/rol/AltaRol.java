package view.rol;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.ViewPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import model.dao.DAOPersona;
import model.dao.DAORol;
import model.entity.Funcionalidad;
import model.entity.Rol;
import validation.Mensajes;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AltaRol extends ViewPanel {
	
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
		lblNewLabel_2.setBounds(72, 170, 84, 13);
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
						Mensajes.MostrarExito("");
					}
				} catch (SQLException e1) {
					Mensajes.MostrarError(e1.getMessage());
				}
			}
		});
		btnAltaRol.setBounds(283, 344, 85, 21);
		add(btnAltaRol);
		
		cmbFuncionalidades = new JComboBox<Funcionalidad>();
		cmbFuncionalidades.setBounds(195, 166, 173, 21);
		RolUtils.cargarTodasLasFuncionalidades(cmbFuncionalidades);
		add(cmbFuncionalidades);
		
		JButton btn = new JButton("New button");
		btn.setBounds(72, 209, 296, 25);
		add(btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 246, 296, 92);
		add(scrollPane);
		
		listFuncionalidades = new JList<Funcionalidad>();
		scrollPane.setViewportView(listFuncionalidades);
	}

}
