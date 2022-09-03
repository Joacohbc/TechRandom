package view.persona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.dao.DAOPersona;
import model.dao.DAORol;
import model.entity.Persona;
import model.entity.Rol;
import validation.Formatos;
import validation.Mensajes;
import view.ViewPanel;

public class ModificacionPersonaView extends ViewPanel {

	/**
	 * Create the panel.
	 */
	private JTextField txtDocumento;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtFechNac;
	private JTextField txtclave;
	private JTextField txtMail;
	private JComboBox<Rol> comboRol;

	/**
	 * Create the panel.
	 */
	public ModificacionPersonaView() {
		super();
		setLayout(null);

		JLabel documento = new JLabel("Documento");
		documento.setBounds(69, 74, 72, 17);
		add(documento);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(170, 73, 138, 20);
		add(txtDocumento);
		txtDocumento.setColumns(10);

		JLabel primerNombre = new JLabel("Primer Nombre");
		primerNombre.setBounds(69, 134, 86, 17);
		add(primerNombre);

		txtNombre1 = new JTextField();
		txtNombre1.setColumns(10);
		txtNombre1.setBounds(170, 137, 239, 20);
		add(txtNombre1);

		JLabel segundoNombre = new JLabel("Segundo Nombre");
		segundoNombre.setBounds(69, 162, 86, 17);
		add(segundoNombre);

		txtNombre2 = new JTextField();
		txtNombre2.setColumns(10);
		txtNombre2.setBounds(170, 164, 239, 20);
		add(txtNombre2);

		txtApellido1 = new JTextField();
		txtApellido1.setColumns(10);
		txtApellido1.setBounds(170, 193, 239, 20);
		add(txtApellido1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Primer Apellido");
		lblNewLabel_1_1_1.setBounds(69, 190, 72, 17);
		add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_1_1_1_1.setBounds(69, 249, 101, 17);
		add(lblNewLabel_1_1_1_1);

		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBounds(170, 218, 239, 20);
		add(txtApellido2);

		JLabel lbl = new JLabel("Segundo Apellido");
		lbl.setBounds(69, 226, 86, 13);
		add(lbl);

		txtFechNac = new JTextField();
		txtFechNac.setBounds(170, 248, 239, 19);
		add(txtFechNac);
		txtFechNac.setColumns(10);

		JLabel lblNewLabel = new JLabel("Clave");
		lblNewLabel.setBounds(69, 279, 45, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("e-mail");
		lblNewLabel_1.setBounds(69, 310, 45, 13);
		add(lblNewLabel_1);

		txtclave = new JTextField();
		txtclave.setBounds(170, 276, 239, 19);
		add(txtclave);
		txtclave.setColumns(10);

		txtMail = new JTextField();
		txtMail.setBounds(170, 307, 239, 19);
		add(txtMail);
		txtMail.setColumns(10);

		comboRol = new JComboBox();
		comboRol.setBounds(170, 340, 239, 21);
		add(comboRol);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(69, 344, 45, 13);
		add(lblNewLabel_2);

		JButton btnModPersona = new JButton("Modificar Persona");
		btnModPersona.addActionListener(new ActionListener() {
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
					if (DAOPersona.update(documento, p) > 0) {
						Mensajes.MostrarExito("Exito al modificar la persona");
					}

				} catch (SQLException e1) {
					Mensajes.MostrarError("");
				}
			}
		});
		btnModPersona.setBounds(318, 371, 128, 21);
		add(btnModPersona);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(318, 72, 128, 21);
		add(btnBuscar);

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Persona p = DAOPersona.findByDocumento(txtDocumento.getText());
					if (p == null) {
						Mensajes.MostrarError("No se pudo localizar a la Persona");
						return;
					}

					txtDocumento.setText(p.getDocumento());
					txtNombre1.setText(p.getNombre1());
					txtNombre2.setText(p.getNombre2());
					txtApellido1.setText(p.getApellido1());
					txtApellido2.setText(p.getApellido2());
					txtFechNac.setText(Formatos.ToFormatedString(p.getFechaNacimiento()));
					txtclave.setText(p.getClave());
					txtMail.setText(p.getMail());
					cargarCombo();
					comboRol.setSelectedItem(p.getRol());

				} catch (SQLException e2) {
					e2.printStackTrace();
				}

			}
		});
	}

	private void cargarCombo() throws SQLException {
		List<Rol> roles = DAORol.findAll();
		for (Rol r : roles) {
			comboRol.addItem(r);
		}
	}
}
