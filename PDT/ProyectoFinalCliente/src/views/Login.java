package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;

import org.wildfly.security.password.Password;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import swingutils.Mensajes;
import validation.Validaciones;
import validation.ValidacionesUsuario;
import validation.ValidationObject;

import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<Roles> comboRol = new JComboBox<Roles>();
		comboRol.setBounds(332, 184, 164, 21);
		contentPane.add(comboRol);
		for (Roles rol : Roles.values()) {
			comboRol.addItem(rol);
		}

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Registrarse registro = new Registrarse();
				registro.setVisible(true);
			}
		});
		btnRegistrarse.setBounds(394, 344, 102, 21);
		contentPane.add(btnRegistrarse);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(220, 218, 102, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(220, 254, 102, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(220, 188, 102, 13);
		contentPane.add(lblNewLabel_2);

		VTextBox textboxUsuario = new VTextBox();
		textboxUsuario.setBounds(332, 215, 164, 21);
		contentPane.add(textboxUsuario);
		textboxUsuario.setValidationFunc(texto -> ValidacionesUsuario.validarNombreUsuario(texto));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Valido el usuario
				if (!textboxUsuario.isValid()) {
					Mensajes.MostrarError(textboxUsuario.getErrorMessage());
					return;
				}
				//Valido la contrasena
				ValidationObject v = ValidacionesUsuario.validarContrasena(String.valueOf(textPassword.getPassword()));
				if (!v.isValid()) {
					Mensajes.MostrarError(v.getErrorMessage());
					return;
				}
				
				if (comboRol.getSelectedItem() == Roles.ANALISTA) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Analista.class);
						setVisible(false);
						ViewAnalista viewAnalista = new ViewAnalista();
						viewAnalista.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				} else if (comboRol.getSelectedItem() == Roles.TUTOR) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Tutor.class);
						setVisible(false);
						ViewTutor ViewTutor = new ViewTutor();
						ViewTutor.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				} else if (comboRol.getSelectedItem() == Roles.ESTUDIANTE) {
					try {
						BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textPassword.getPassword()),
								Estudiante.class);
						setVisible(false);
						ViewEstudiante ViewEstudiante = new ViewEstudiante();
						ViewEstudiante.setVisible(true);
					} catch (Exception E) {
						JOptionPane.showMessageDialog(null,
								"No es posible loguearse al sistema. Compruebe las credenciales ingresadas.");
					}
				}
			}
		});
		btnLogin.setBounds(394, 308, 102, 21);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/logo utec (2).png")));
		lblNewLabel_4.setBounds(570, 10, 117, 105);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images/usuario (3).png")));
		lblNewLabel_3.setBounds(408, 95, 32, 44);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Login");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(353, 105, 45, 30);
		contentPane.add(lblNewLabel_5);

		textPassword = new JPasswordField();
		textPassword.setBounds(332, 250, 164, 21);
		contentPane.add(textPassword);

	}
}
