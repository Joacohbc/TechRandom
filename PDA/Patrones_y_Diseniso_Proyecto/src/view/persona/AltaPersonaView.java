package view.persona;

import javax.swing.JLabel;
import model.dao.DAOPersona;
import model.entity.Persona;
import model.entity.Rol;
import validation.Formatos;
import validation.Mensajes;
import view.ViewPanel;
import view.rol.RolUtils;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import validation.Validaciones;
import validation.Validaciones.ValidacionesFecha;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
		comboRol.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				try {
					RolUtils.cargarRol(comboRol);
				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al cargar roles de la BD");
					e1.printStackTrace();
				}
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

				String documento = txtDocumento.getText().trim();
				String apellido1 = txtApellido1.getText().trim();
				String apellido2 = txtApellido2.getText().trim();
				String nombre1 = txtNombre1.getText().trim();
				String nombre2 = txtNombre2.getText().trim();
				String mail = txtMail.getText().trim();
				String clave = txtclave.getText();

				if (!Validaciones.ValidarCedulaUruguaya(documento)) {
					Mensajes.MostrarError("Error en el documento");
					return;
				}

				if (!Validaciones.ValidarSoloLetras(nombre1, false)) {
					Mensajes.MostrarError("Error en el nombre1");
					return;
				}

				if (!nombre2.isBlank() && !Validaciones.ValidarSoloLetras(nombre2, false)) {
					Mensajes.MostrarError("Error en el nombre2");
					return;
				}

				if (!Validaciones.ValidarSoloLetras(apellido1, false)) {
					Mensajes.MostrarError("Error en el apellido1");
					return;
				}

				if (!apellido2.isBlank() && !Validaciones.ValidarSoloLetras(apellido2, false)) {
					Mensajes.MostrarError("Error en el apellido2");
					return;
				}

				LocalDate fecha = null;
				if (Validaciones.IsValid(txtFechNac.getText()))
					fecha = Formatos.Parse(txtFechNac.getText());
				else {
					Mensajes.MostrarError("Error en la fecha");
					return;
				}

				if (!Validaciones.ValidarFechaMax(fecha, LocalDate.now(), ValidacionesFecha.ESTRICTAMENTE)) {
					Mensajes.MostrarError("Error en la fecha");
					return;
				}

				if (!Validaciones.ValidarNoVacio(clave)) {
					Mensajes.MostrarError("Error en el clave");
					return;
				}

				if (!Validaciones.ValidarMail(mail)) {
					Mensajes.MostrarError("Error en el mail");
					return;
				}

				Rol r = (Rol) comboRol.getSelectedItem();
				Persona p = new Persona(documento, apellido1, apellido2, nombre1, nombre2, fecha, clave, mail, r);

				try {					
					if(DAOPersona.findByDocumento(documento) != null) {
						Mensajes.MostrarError("Ya existe una persona con ese documento");
						return;
					}
					
					if (DAOPersona.insert(p) > 0) {
						Mensajes.MostrarExito("Persona agregada con exito");
					}

				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al dar de alta la persona");
				}
			}
		});
		btnAltaPersona.setBounds(290, 380, 128, 21);
		add(btnAltaPersona);

		try {
			RolUtils.cargarRol(comboRol);
		} catch (SQLException e1) {
			Mensajes.MostrarError("Error al cargar roles de la BD");
			e1.printStackTrace();
		}
	}
}
