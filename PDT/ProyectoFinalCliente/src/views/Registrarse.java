package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import components.VTextBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Registrarse extends JFrame {

	private JPanel contentPane;

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
		setBounds(100, 100, 655, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(53, 119, 94, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setBounds(53, 141, 94, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setBounds(53, 164, 94, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombres");
		lblNewLabel_3.setBounds(53, 187, 94, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setBounds(53, 210, 94, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de Nacimiento");
		lblNewLabel_5.setBounds(53, 233, 116, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(347, 119, 116, 13);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("E-Mail");
		lblNewLabel_7.setBounds(347, 141, 116, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Localidad");
		lblNewLabel_8.setBounds(347, 164, 116, 13);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Departamento");
		lblNewLabel_9.setBounds(347, 187, 116, 13);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Genero");
		lblNewLabel_10.setBounds(347, 210, 116, 13);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("ITR");
		lblNewLabel_11.setBounds(347, 233, 116, 13);
		contentPane.add(lblNewLabel_11);
		
		VTextBox textDocumento = new VTextBox();
		textDocumento.setBounds(173, 116, 110, 16);
		contentPane.add(textDocumento);
		
		VTextBox textUsuario = new VTextBox();
		textUsuario.setBounds(173, 138, 110, 16);
		contentPane.add(textUsuario);
		
		VTextBox textContraseña = new VTextBox();
		textContraseña.setBounds(173, 161, 110, 16);
		contentPane.add(textContraseña);
		
		VTextBox textNombres = new VTextBox();
		textNombres.setBounds(173, 184, 110, 16);
		contentPane.add(textNombres);
		
		VTextBox textApellidos = new VTextBox();
		textApellidos.setBounds(173, 207, 110, 16);
		contentPane.add(textApellidos);
		
		VTextBox textFechaNac = new VTextBox();
		textFechaNac.setBounds(173, 231, 110, 16);
		contentPane.add(textFechaNac);
		
		VTextBox textTel = new VTextBox();
		textTel.setBounds(439, 115, 110, 16);
		contentPane.add(textTel);
		
		VTextBox textMail = new VTextBox();
		textMail.setBounds(439, 137, 110, 16);
		contentPane.add(textMail);
		
		VTextBox textLocalidad = new VTextBox();
		textLocalidad.setBounds(439, 160, 110, 16);
		contentPane.add(textLocalidad);
		
		VTextBox textDepartamento = new VTextBox();
		textDepartamento.setBounds(439, 183, 110, 16);
		contentPane.add(textDepartamento);
		
		VTextBox textGenero = new VTextBox();
		textGenero.setBounds(439, 206, 110, 16);
		contentPane.add(textGenero);
		
		VTextBox textItr = new VTextBox();
		textItr.setBounds(439, 230, 110, 16);
		contentPane.add(textItr);
		
		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.setBounds(439, 314, 110, 21);
		contentPane.add(btnRegistrarme);
		
		JLabel lblNewLabel_12 = new JLabel("Registro de usuario");
		lblNewLabel_12.setBounds(207, 40, 218, 13);
		contentPane.add(lblNewLabel_12);
	}
}
