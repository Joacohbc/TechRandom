package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidEntityException;
import com.exceptions.ServiceException;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import swingutils.Mensajes;
import validation.ValidacionesUsuario;
import validation.ValidationObject;
import viewsEstudiante.ViewEstudiante;
import viewsEstudiante.ViewsEstudiante;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPasswordField textPassword;
	private VTextBox txtUsuario;
	
	public static final String VERSION = "v1.2";

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

	private boolean estadoValido(Usuario usuario) {
		if (usuario == null) {
			Mensajes.MostrarError("No es posible loguearse al sistema. Compruebe las credenciales ingresadas");
			return false;
		}

		if (usuario.getEstadoUsuario() == EstadoUsuario.SIN_VALIDAR
				|| usuario.getEstadoUsuario() == EstadoUsuario.ELIMINADO) {
			Mensajes.MostrarError("El usuario " + usuario.getNombreUsuario()
					+ " no esta vaidado, consulte con el Analista responsable");
			return false;
		}
		
		return true;
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
		comboRol.setBounds(277, 184, 270, 21);
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
		btnRegistrarse.setBounds(165, 343, 102, 21);
		contentPane.add(btnRegistrarse);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(165, 218, 123, 13);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(165, 254, 123, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(165, 188, 102, 13);
		contentPane.add(lblNewLabel_2);

		txtUsuario = new VTextBox();
		txtUsuario.setBounds(277, 215, 270, 21);
		contentPane.add(txtUsuario);
		txtUsuario.setValidationFunc(texto -> ValidacionesUsuario.validarNombreUsuario(texto));

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Valido el usuario
				if (!txtUsuario.isContentValid()) {
					Mensajes.MostrarError(txtUsuario.getErrorMessage());
					return;
				}

				// Valido la contrasena
				ValidationObject v = ValidacionesUsuario.validarContrasena(String.valueOf(textPassword.getPassword()));
				if (!v.isValid()) {
					Mensajes.MostrarError(v.getErrorMessage());
					return;
				}

				try {
					if (comboRol.getSelectedItem() == Roles.ANALISTA) {
						Analista ana = BeanIntances.user().login(txtUsuario.getText(),
								String.valueOf(textPassword.getPassword()), Analista.class);

						if (!estadoValido(ana))
							return;

						setVisible(false);
						ViewAnalista viewAnalista = new ViewAnalista();
						viewAnalista.setVisible(true);

					} else if (comboRol.getSelectedItem() == Roles.TUTOR) {
						Tutor tut = BeanIntances.user().login(txtUsuario.getText(),
								String.valueOf(textPassword.getPassword()), Tutor.class);
						
						if (!estadoValido(tut))
							return;
						
						setVisible(false);
						ViewTutor ViewTutor = new ViewTutor();
						ViewTutor.setVisible(true);

					} else {
						Estudiante est = BeanIntances.user().login(txtUsuario.getText(),
								String.valueOf(textPassword.getPassword()), Estudiante.class);
						
						if (!estadoValido(est))
							return;
						
						setVisible(false);
						ViewsEstudiante ViewEstudiante = new ViewsEstudiante();
						ViewEstudiante.setVisible(true);
					}

				} catch (ServiceException | InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());

				} catch (Exception ex) {
					Mensajes.MostrarError("Error desconocido:" + ex.getMessage());
				}

			}
		});
		btnLogin.setBounds(397, 343, 150, 21);
		contentPane.add(btnLogin);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/logo utec (2).png")));
		lblNewLabel_4.setBounds(570, 10, 117, 105);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/images/usuario (3).png")));
		lblNewLabel_3.setBounds(398, 108, 32, 41);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("Login");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(324, 119, 45, 30);
		contentPane.add(lblNewLabel_5);

		textPassword = new JPasswordField();
		textPassword.setBounds(277, 250, 270, 21);
		contentPane.add(textPassword);

		JLabel lblNewLabel_6 = new JLabel("Ha olvidado su contrasena");
		lblNewLabel_6.setBounds(165, 307, 165, 13);
		contentPane.add(lblNewLabel_6);

		JButton btnRestorePassword = new JButton("Restablecer contrasena");
		btnRestorePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Contactese con el Analista correspondiente para reiniciar su clave.");
			}
		});
		btnRestorePassword.setBounds(355, 303, 192, 21);
		contentPane.add(btnRestorePassword);

	}
}
