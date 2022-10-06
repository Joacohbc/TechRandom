package view.rol;

import model.dao.DAOPersona;
import model.dao.DAORol;
import model.entity.Persona;
import model.entity.Rol;
import validation.Mensajes;
import view.ViewPanel;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class BajaRol extends ViewPanel {

	/**
	 * Create the panel.
	 */
	public BajaRol() {
		super();
		
		JLabel lblNewLabel = new JLabel("Rol");
		lblNewLabel.setBounds(67, 166, 45, 13);
		add(lblNewLabel);
		
		JComboBox<Rol> cmbRoles = new JComboBox<Rol>();
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
		cmbRoles.setBounds(122, 162, 300, 21);
		add(cmbRoles);
		
		JButton btnBorrarRol = new JButton("Borrar Rol");
		btnBorrarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Rol rol = ((Rol) cmbRoles.getSelectedItem());
					List<Persona> personas = DAOPersona.findAll();
					for (Persona persona : personas) {
						if(persona.getRol().getNombre().equals(rol.getNombre())) {
							Mensajes.MostrarError("Existe un usuario con este Rol, debe eliminarlo primero");
							return;
						}
					}
					
					Rol func = DAORol.findByNombre(rol.getNombre());
					if(!func.getFuncionalidades().isEmpty()) {
						Mensajes.MostrarError("Este rol contiene funcionalidades, borrelas para poder borrar el rol.");
						return;
					}
					if(DAORol.delete(rol.getNombre()) >0) {
						Mensajes.MostrarExito("Eliminacion de rol exitosa");
						RolUtils.cargarRol(cmbRoles);
					}else {
						Mensajes.MostrarError("Eliminacion de rol fallida");
					}
				}catch (Exception e1) {
					Mensajes.MostrarError("Error al dar de baja el rol: " + e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		btnBorrarRol.setBounds(67, 191, 355, 21);
		add(btnBorrarRol);
		
		try {
			RolUtils.cargarRol(cmbRoles);
		} catch (Exception e1) {
			Mensajes.MostrarError("Error al traer los roles desde la base de Datos: " + e1.getMessage());
			e1.printStackTrace();
		}
	}
}
