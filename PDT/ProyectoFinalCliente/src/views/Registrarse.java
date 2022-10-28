package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.entities.Usuario;
import com.entities.enums.Departamento;
import com.entities.enums.Genero;

import components.VTextBox;

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

		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarme.setBounds(439, 314, 110, 21);
		contentPane.add(btnRegistrarme);

		JLabel lblNewLabel_12 = new JLabel("Registro de usuario");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(228, 39, 218, 13);
		contentPane.add(lblNewLabel_12);

		JComboBox<Departamento> comboDepartamento = new JComboBox<Departamento>();
		comboDepartamento.setBounds(439, 183, 110, 17);
		contentPane.add(comboDepartamento);
		for (Departamento departamento : Departamento.values()) {
			comboDepartamento.addItem(departamento);
		}

		JComboBox<Genero> comboGenero = new JComboBox<Genero>();
		comboGenero.setBounds(439, 206, 110, 17);
		contentPane.add(comboGenero);
		for (Genero genero : Genero.values()) {
			comboGenero.addItem(genero);
		}

		JComboBox comboItr = new JComboBox();
		comboItr.setBounds(439, 229, 111, 17);
		contentPane.add(comboItr);

		VTextBox textLocalidad = new VTextBox();
		textLocalidad.setBounds(439, 161, 110, 16);
		contentPane.add(textLocalidad);
	}

	public Registrarse(Usuario usuario) {
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
		textDocumento.setText(usuario.getDocumento());

		VTextBox textUsuario = new VTextBox();
		textUsuario.setBounds(173, 138, 110, 16);
		contentPane.add(textUsuario);
		textUsuario.setText(usuario.getNombreUsuario());

		VTextBox textContraseña = new VTextBox();
		textContraseña.setBounds(173, 161, 110, 16);
		contentPane.add(textContraseña);
		textContraseña.setText(usuario.getContrasena());

		VTextBox textNombres = new VTextBox();
		textNombres.setBounds(173, 184, 110, 16);
		contentPane.add(textNombres);
		textNombres.setText(usuario.getNombres());

		VTextBox textApellidos = new VTextBox();
		textApellidos.setBounds(173, 207, 110, 16);
		contentPane.add(textApellidos);
		textApellidos.setText(usuario.getApellidos());

		VTextBox textFechaNac = new VTextBox();
		textFechaNac.setBounds(173, 231, 110, 16);
		contentPane.add(textFechaNac);
		//textApellidos.setText(usuario.getApellidos());

		VTextBox textTel = new VTextBox();
		textTel.setBounds(439, 115, 110, 16);
		contentPane.add(textTel);
		textTel.setText(usuario.getTelefono());

		VTextBox textMail = new VTextBox();
		textMail.setBounds(439, 137, 110, 16);
		contentPane.add(textMail);
		textMail.setText(usuario.getEmail());

		JButton btnRegistrarme = new JButton("Registrarme");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarme.setBounds(439, 314, 110, 21);
		contentPane.add(btnRegistrarme);

		JLabel lblNewLabel_12 = new JLabel("Registro de usuario");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(228, 39, 218, 13);
		contentPane.add(lblNewLabel_12);

		JComboBox<Departamento> comboDepartamento = new JComboBox<Departamento>();
		comboDepartamento.setBounds(439, 183, 110, 17);
		contentPane.add(comboDepartamento);
		for (Departamento departamento : Departamento.values()) {
			comboDepartamento.addItem(departamento);
		}
		//comboDepartamento.setSelectedItem(usuario.getDepartamento());

		JComboBox<Genero> comboGenero = new JComboBox<Genero>();
		comboGenero.setBounds(439, 206, 110, 17);
		contentPane.add(comboGenero);
		for (Genero genero : Genero.values()) {
			comboGenero.addItem(genero);
		}
		//comboGenero.setSelectedItem(usuario.getGenero());

		JComboBox comboItr = new JComboBox();
		comboItr.setBounds(439, 229, 111, 17);
		contentPane.add(comboItr);
		//comboItr.add(usuario.getItr().getIdItr());
		
		VTextBox textLocalidad = new VTextBox();
		textLocalidad.setBounds(439, 161, 110, 16);
		contentPane.add(textLocalidad);
		textLocalidad.setText(usuario.getLocalidad());
	}

}
