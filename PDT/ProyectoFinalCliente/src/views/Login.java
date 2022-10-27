package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import components.ValidationObject;
import validation.Validaciones;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textContraseña;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox <Roles> comboRol = new JComboBox <Roles>();
		comboRol.setBounds(169, 64, 164, 21);
		contentPane.add(comboRol);
		for (Roles rol : Roles.values()) {
			comboRol.addItem(rol);
		}
		
		/*JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboRol.getSelectedItem() == Roles.ANALISTA) {
					System.out.println(BeanIntances.user().login(txtUsuario.getText(), String.valueOf(txtPassword.getPassword()), Analista.class));
				}else if (comboRol.getSelectedItem() == Roles.TUTOR && textBox.isValid()) {
					System.out.println(BeanIntances.user().login(textBox.getText(), String.valueOf(txtPassword.getPassword()), Tutor.class));
				}else {
					System.out.println(BeanIntances.user().login(txtUsuario.getText(), String.valueOf(txtPassword.getPassword()), Estudiante.class));
				}
			}
		});
		btnLogin.setBounds(264, 168, 102, 21);
		contentPane.add(btnLogin);*/
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarse.setBounds(264, 209, 102, 21);
		contentPane.add(btnRegistrarse);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(57, 98, 102, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(57, 134, 102, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(57, 68, 102, 13);
		contentPane.add(lblNewLabel_2);
		
		VTextBox textboxUsuario = new VTextBox();
		textboxUsuario.setBounds(169, 95, 164, 21);
		contentPane.add(textboxUsuario);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboRol.getSelectedItem() == Roles.ANALISTA && textboxUsuario.isValid()) {
					System.out.println(BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textContraseña.getPassword()), Analista.class));
				}else if (comboRol.getSelectedItem() == Roles.TUTOR && textboxUsuario.isValid()) {
					System.out.println(BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textContraseña.getPassword()), Tutor.class));
				}else if (comboRol.getSelectedItem() == Roles.ESTUDIANTE && textboxUsuario.isValid()){
					System.out.println(BeanIntances.user().login(textboxUsuario.getText(), String.valueOf(textContraseña.getPassword()), Estudiante.class));
					}
				}
		});
		btnLogin.setBounds(264, 171, 102, 21);
		contentPane.add(btnLogin);
		
		textContraseña = new JPasswordField();
		textContraseña.setBounds(169, 126, 164, 21);
		contentPane.add(textContraseña);
		
	}
}
