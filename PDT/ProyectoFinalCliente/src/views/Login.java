package views;

import java.awt.BorderLayout;
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
import java.awt.Label;
import javax.swing.ImageIcon;

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
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().add(logo, BorderLayout.CENTER);
		
		JComboBox <Roles> comboRol = new JComboBox <Roles>();
		comboRol.setBounds(317, 175, 164, 21);
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
				Registrarse registro = new Registrarse();
				registro.setVisible(true);
			}
		});
		btnRegistrarse.setBounds(414, 330, 102, 21);
		contentPane.add(btnRegistrarse);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(205, 209, 102, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(205, 245, 102, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Rol");
		lblNewLabel_2.setBounds(205, 179, 102, 13);
		contentPane.add(lblNewLabel_2);
		
		VTextBox textboxUsuario = new VTextBox();
		textboxUsuario.setBounds(317, 206, 164, 21);
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
		btnLogin.setBounds(414, 292, 102, 21);
		contentPane.add(btnLogin);
		
		textContraseña = new JPasswordField();
		textContraseña.setBounds(317, 237, 164, 21);
		contentPane.add(textContraseña);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\bruno\\Desktop\\logo utec (2).png"));
		lblNewLabel_3.setBounds(551, 21, 109, 103);
		contentPane.add(lblNewLabel_3);
		
		
	}
}
