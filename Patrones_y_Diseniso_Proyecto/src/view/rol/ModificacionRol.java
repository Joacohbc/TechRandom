package view.rol;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.ViewPanel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

import model.dao.DAOFuncionalidadRol;
import model.dao.DAORol;
import model.entity.Funcionalidad;
import model.entity.Rol;
import validation.Mensajes;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModificacionRol extends ViewPanel {

	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JComboBox<Funcionalidad> cmbTodasFuncs;
	private JComboBox<Funcionalidad> cmbFuncActuales;
	private JComboBox<Rol> cmbRoles;

	public ModificacionRol() {
		super();

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(45, 165, 147, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(45, 208, 147, 13);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Funcionalidades");
		lblNewLabel_2.setBounds(45, 248, 147, 13);
		add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(199, 162, 222, 19);
		add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(199, 205, 222, 19);
		add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JButton btnModificarRol = new JButton("Modificar Rol");
		btnModificarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rol r = new Rol();
				r.setDescripcion(txtDescripcion.getText());
				r.setNombre(txtNombre.getText());

				try {
					String nombre = ((Rol) cmbRoles.getSelectedItem()).getNombre();
					if (DAORol.update(nombre, r) > 0) {
						Mensajes.MostrarExito("EXITO");
						RolUtils.cargarRol(cmbRoles);
					}
				} catch (Exception ex) {
					Mensajes.MostrarError("Error: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnModificarRol.setBounds(45, 377, 376, 21);
		add(btnModificarRol);

		cmbTodasFuncs = new JComboBox();
		cmbTodasFuncs.setBounds(199, 244, 222, 21);
		RolUtils.cargarTodasLasFuncionalidades(cmbTodasFuncs);
		add(cmbTodasFuncs);

		cmbRoles = new JComboBox<Rol>();
		cmbRoles.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					RolUtils.cargarRol(cmbRoles);
				} catch (Exception e1) {
					Mensajes.MostrarError("Error al traer los roles desde la base de Datos: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		cmbRoles.setBounds(199, 97, 222, 21);
		add(cmbRoles);

		JLabel lblNewLabel_3 = new JLabel("Seleccionar Rol");
		lblNewLabel_3.setBounds(45, 101, 147, 13);
		add(lblNewLabel_3);

		JLabel lblNewLabel_2_1 = new JLabel("Funcionalidades");
		lblNewLabel_2_1.setBounds(45, 313, 147, 13);
		add(lblNewLabel_2_1);

		cmbFuncActuales = new JComboBox<Funcionalidad>();
		cmbFuncActuales.setBounds(199, 309, 222, 21);
		add(cmbFuncActuales);

		JButton btnNuevaFunc = new JButton("Agregar Funcionalidad");
		btnNuevaFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = cmbFuncActuales.getItemCount();
				Funcionalidad nuevaFunc = (Funcionalidad) cmbTodasFuncs.getSelectedItem();
				for (int i = 0; i < n; i++) {
					if (nuevaFunc == cmbFuncActuales.getItemAt(i)) {
						Mensajes.MostrarError("Ya esta esa funcionalidad de agregada");
						return;
					}
				}
				try {
					Rol rol = (Rol) cmbRoles.getSelectedItem();
					if (DAOFuncionalidadRol.insertByName(rol.getNombre(), nuevaFunc) > 0) {
						Mensajes.MostrarExito("Funcionalidad agregada");
						RolUtils.cargarFuncionalidadesDeRol(rol.getNombre(), cmbFuncActuales);
					} else {
						Mensajes.MostrarError("Error al agregar la funcionalidad");

					}

				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al agregar una nueva funcionalidad: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnNuevaFunc.setBounds(45, 276, 376, 21);
		add(btnNuevaFunc);

		JButton btnEliminarFunc = new JButton("Eliminar Funcionalidad");
		btnEliminarFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int n = cmbFuncActuales.getItemCount();
				Funcionalidad borrarFunc = (Funcionalidad) cmbFuncActuales.getSelectedItem();

				try {
					Rol rol = (Rol) cmbRoles.getSelectedItem();
					if (DAOFuncionalidadRol.deleteByName(rol.getNombre(), borrarFunc) > 0) {
						Mensajes.MostrarExito("Funcionalidad eliminada");
						RolUtils.cargarFuncionalidadesDeRol(rol.getNombre(), cmbFuncActuales);
					} else {
						Mensajes.MostrarError("Error al agregar la eliminada");
					}

				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al agregar: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnEliminarFunc.setBounds(45, 344, 376, 21);
		add(btnEliminarFunc);

		JButton btnCargarRol = new JButton("Cargar Rol");
		btnCargarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Rol rol = (Rol) cmbRoles.getSelectedItem();
					txtNombre.setText(rol.getNombre());
					txtDescripcion.setText(rol.getDescripcion());
					RolUtils.cargarFuncionalidadesDeRol(rol.getNombre(), cmbFuncActuales);
				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al cargar los datos de rol: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnCargarRol.setBounds(45, 126, 376, 21);
		add(btnCargarRol);
		
		// Cargar el ComboBox de Roles al inicio
		try {
			RolUtils.cargarRol(cmbRoles);
		} catch (Exception e1) {
			Mensajes.MostrarError("Error al traer los roles desde la base de Datos: " + e1.getMessage());
			e1.printStackTrace();
		}
	}
}
