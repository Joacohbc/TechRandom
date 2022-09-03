package view.persona;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import controller.PersonaController;
import model.dao.DAOPersona;
import model.dao.DAORol;
import model.entity.Persona;
import model.entity.Rol;
import validation.Formatos;
import validation.Mensajes;
import view.ViewPanel;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AltaPersonaView extends ViewPanel {

	private JTextField txtDocumento;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtFechNac;
	private JTextField txtclave;
	private JTextField txtMail;
	private JComboBox<Rol> comboRol;

	public String getDocumento() {
		return txtDocumento.getText();
	}

	public String getNombre1() {
		return txtNombre1.getText();
	}

	public String getNombre2() {
		return txtNombre2.getText();
	}

	public String getApellido1() {
		return txtApellido1.getText();
	}

	public String getApellido2() {
		return txtApellido2.getText();
	}

	public String getFechNac() {
		return txtFechNac.getText();
	}

	public String getClave() {
		return txtclave.getText();
	}

	public String getMail() {
		return txtMail.getText();
	}

	public Rol getRol() {
		return (Rol) comboRol.getSelectedItem();
	}

	public AltaPersonaView() {
		super();
		setLayout(null);

		JLabel documento = new JLabel("Documento");
		documento.setBounds(78, 88, 72, 17);
		add(documento);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(179, 88, 239, 20);
		add(txtDocumento);
		txtDocumento.setColumns(10);

		JLabel primerNombre = new JLabel("Primer Nombre");
		primerNombre.setBounds(78, 116, 86, 17);
		add(primerNombre);

		txtNombre1 = new JTextField();
		txtNombre1.setColumns(10);
		txtNombre1.setBounds(179, 119, 239, 20);
		add(txtNombre1);

		JLabel segundoNombre = new JLabel("Segundo Nombre");
		segundoNombre.setBounds(78, 144, 86, 17);
		add(segundoNombre);

		txtNombre2 = new JTextField();
		txtNombre2.setColumns(10);
		txtNombre2.setBounds(179, 146, 239, 20);
		add(txtNombre2);

		txtApellido1 = new JTextField();
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(179, 175, 239, 20);
		add(txtApellido1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Primer Apellido");
		lblNewLabel_1_1_1.setBounds(78, 172, 72, 17);
		add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_1_1_1_1.setBounds(78, 231, 101, 17);
		add(lblNewLabel_1_1_1_1);

		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBounds(179, 200, 239, 20);
		add(txtApellido2);

		JLabel lbl = new JLabel("Segundo Apellido");
		lbl.setBounds(78, 208, 86, 13);
		add(lbl);

		txtFechNac = new JTextField();
		txtFechNac.setBounds(179, 230, 239, 19);
		add(txtFechNac);
		txtFechNac.setColumns(10);

		JLabel lblNewLabel = new JLabel("Clave");
		lblNewLabel.setBounds(78, 261, 45, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("e-mail");
		lblNewLabel_1.setBounds(78, 292, 45, 13);
		add(lblNewLabel_1);

		txtclave = new JTextField();
		txtclave.setBounds(179, 258, 239, 19);
		add(txtclave);
		txtclave.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(179, 289, 239, 19);
		add(txtMail);
		txtMail.setColumns(10);

		comboRol = new JComboBox<Rol>();
		comboRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboRol.setBounds(179, 322, 239, 21);
		add(comboRol);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(78, 326, 45, 13);
		add(lblNewLabel_2);

		JButton btnAltaPersona = new JButton("Alta Persona");
		btnAltaPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String documento = txtDocumento.getText();
				String apellido1 = txtApellido1.getText();
				String apellido2 = txtApellido2.getText();
				String nombre1 = txtNombre1.getText();
				String nombre2 = txtNombre2.getText();
				String mail = txtMail.getText();
				String clave = txtclave.getText();
				LocalDate fecha = Formatos.Parse(txtFechNac.getText());

				Rol r = (Rol) comboRol.getSelectedItem();
				Persona p = new Persona(documento, apellido1, apellido2, nombre1, nombre2, fecha, clave, mail, r);

				try {
					if (DAOPersona.insert(p) > 0) {
						Mensajes.MostrarExito("Persona agregada con exito");
					}

				} catch (SQLException e1) {
					Mensajes.MostrarError("");
				}
			}
		});
		btnAltaPersona.setBounds(290, 380, 128, 21);
		add(btnAltaPersona);

		try {
			cargarCombo();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	private void cargarCombo() throws SQLException {
		List<Rol> roles = DAORol.findAll();
		for (Rol r : roles) {
			comboRol.addItem(r);
		}
	}
}
