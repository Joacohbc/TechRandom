package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Itr;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;
import com.toedter.calendar.JDateChooser;

import beans.BeanIntances;
import components.VTextBox;
import swingutils.Mensajes;
import validation.Formatos;
import validation.ValidacionesUsuario;
import validation.ValidationObject;
import components.Roles;

public class Registrarse extends JFrame {

	private JPanel contentPane;
	private JDateChooser dtFechaDeNacimiento;
	private JPasswordField textpassword;
	private JComboBox <Itr> comboItr;
	private JComboBox <Genero> comboGenero;
	private JComboBox <Departamento> comboDepartamento;
	private VTextBox textLocalidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registrarse frame = new Registrarse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registrarse() {
		setTitle("Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(33, 119, 136, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setBounds(33, 141, 136, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setBounds(33, 164, 136, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombres");
		lblNewLabel_3.setBounds(33, 187, 136, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setBounds(33, 210, 136, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_5.setBounds(33, 233, 136, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(408, 117, 116, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("E-Mail");
		lblNewLabel_7.setBounds(408, 139, 116, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Localidad");
		lblNewLabel_8.setBounds(408, 162, 116, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Departamento");
		lblNewLabel_9.setBounds(408, 185, 116, 13);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Genero");
		lblNewLabel_10.setBounds(408, 208, 116, 13);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("ITR");
		lblNewLabel_11.setBounds(408, 231, 116, 13);
		contentPane.add(lblNewLabel_11);
		
		VTextBox textDocumento = new VTextBox();
		textDocumento.setBounds(173, 116, 110, 16);
		contentPane.add(textDocumento);
		textDocumento.setValidationFunc(texto -> ValidacionesUsuario.validarDocumentoUruguayo(texto));
		
		VTextBox textUsuario = new VTextBox();
		textUsuario.setBounds(173, 138, 110, 16);
		contentPane.add(textUsuario);
		textUsuario.setValidationFunc(texto -> ValidacionesUsuario.validarNombreUsuario(texto));
		
		VTextBox textNombres = new VTextBox();
		textNombres.setBounds(173, 184, 110, 16);
		contentPane.add(textNombres);
		textNombres.setValidationFunc(texto -> ValidacionesUsuario.validarNombres(texto));
		
		VTextBox textApellidos = new VTextBox();
		textApellidos.setBounds(173, 207, 110, 16);
		contentPane.add(textApellidos);
		textApellidos.setValidationFunc(texto -> ValidacionesUsuario.validarApellido(texto));
		
		VTextBox textTel = new VTextBox();
		textTel.setBounds(500, 113, 110, 16);
		contentPane.add(textTel);
		textTel.setValidationFunc(texto -> ValidacionesUsuario.ValidarTelefono(texto));
		
		VTextBox textMail = new VTextBox();
		textMail.setBounds(500, 135, 110, 16);
		contentPane.add(textMail);
		textMail.setValidationFunc(texto -> ValidacionesUsuario.validarEmailUTEC(texto));
		
		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Se valida fecha de nacimiento
				LocalDate fecha = Formatos.ToLocalDate(dtFechaDeNacimiento.getDate());
				ValidationObject v = ValidacionesUsuario.validarFechaNacimiento(fecha);
				
				if (!v.isValid()) {
					Mensajes.MostrarError(v.getErrorMessage());
					return;
				}
				v = ValidacionesUsuario.validarContrasena(String.valueOf(textpassword.getPassword()));
				if (!v.isValid()) {
					Mensajes.MostrarError(v.getErrorMessage());
					return;
				}
				
				if (!textDocumento.isValid() || !textApellidos.isValid() || !textMail.isValid() || !textNombres.isValid() || !textTel.isValid() || !textUsuario.isValid()) {
					Mensajes.MostrarError("Para realizar el registro, debe de tener todos los campos correctamente ingresados.");
					return;
				}
				try {
					Analista a = new Analista();
					a.setNombres(textNombres.getText());
					a.setApellidos(textApellidos.getText());
					a.setNombreUsuario(textUsuario.getText());
					a.setContrasena(String.valueOf(textpassword.getPassword()));
					a.setEmail(textMail.getText());
					a.setDocumento(textDocumento.getText());
					a.setTelefono(textTel.getText());
					a.setFecNacimiento(fecha);
					a.setDepartamento((Departamento)comboDepartamento.getSelectedItem());
					a.setEstado(true);
					a.setGenero((Genero)comboGenero.getSelectedItem());
					a.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
					a.setLocalidad(textLocalidad.getText());
					a.setItr((Itr)comboItr.getSelectedItem());
					BeanIntances.user().register(a);
				}catch(Exception E) {
					Mensajes.MostrarError(E.getMessage());
				}
				
			}
		});
		btnRegistrarme.setBounds(500, 420, 110, 21);
		contentPane.add(btnRegistrarme);
		
		JLabel lblNewLabel_12 = new JLabel("Registro de usuario");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(228, 39, 218, 13);
		contentPane.add(lblNewLabel_12);
		
		
		comboDepartamento = new JComboBox <Departamento>();
		comboDepartamento.setBounds(500, 181, 110, 17);
		contentPane.add(comboDepartamento);
		for (Departamento departamento : Departamento.values()) {
			comboDepartamento.addItem(departamento);
		}
		
		comboGenero = new JComboBox <Genero>();
		comboGenero.setBounds(500, 204, 110, 17);
		contentPane.add(comboGenero);
		for (Genero genero : Genero.values()) {
			comboGenero.addItem(genero);
		}
		
		
		comboItr = new JComboBox<Itr>();
		comboItr.setBounds(500, 227, 111, 17);
		contentPane.add(comboItr);
		List <Itr> itrs = BeanIntances.itr().findAll();
		for (Itr itr : itrs) {
			comboItr.addItem(itr);
		}
		
		textLocalidad = new VTextBox();
		textLocalidad.setBounds(500, 159, 110, 16);
		contentPane.add(textLocalidad);
		
		dtFechaDeNacimiento = new JDateChooser();
		dtFechaDeNacimiento.setBounds(173, 233, 110, 19);
		contentPane.add(dtFechaDeNacimiento);
		
		textpassword = new JPasswordField();
		textpassword.setBounds(173, 161, 110, 16);
		contentPane.add(textpassword);
		
		JComboBox<Roles> comboRol = new JComboBox<Roles>();
		comboRol.setBounds(173, 262, 110, 21);
		contentPane.add(comboRol);
		for (Roles rol : Roles.values()) {
			comboRol.addItem(rol);
		}
		
		JLabel lblNewLabel_13 = new JLabel("Rol");
		lblNewLabel_13.setBounds(33, 256, 45, 13);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_6_1 = new JLabel("Generacion");
		lblNewLabel_6_1.setBounds(408, 258, 116, 13);
		contentPane.add(lblNewLabel_6_1);
		
		VTextBox textTel_1 = new VTextBox();
		textTel_1.setBounds(500, 254, 110, 16);
		contentPane.add(textTel_1);
		
		JLabel lblNewLabel_6_2 = new JLabel("Area");
		lblNewLabel_6_2.setBounds(408, 285, 116, 13);
		contentPane.add(lblNewLabel_6_2);
		
		JLabel lblNewLabel_7_2 = new JLabel("Tipo Tutor");
		lblNewLabel_7_2.setBounds(408, 307, 116, 13);
		contentPane.add(lblNewLabel_7_2);
		
		VTextBox textTipoTutor = new VTextBox();
		textTipoTutor.setBounds(500, 303, 110, 16);
		contentPane.add(textTipoTutor);
		
		VTextBox textArea = new VTextBox();
		textArea.setBounds(500, 281, 110, 16);
		contentPane.add(textArea);
		
		
	}
}
