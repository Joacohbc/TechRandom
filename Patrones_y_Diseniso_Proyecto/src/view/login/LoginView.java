package view.login;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.GestionController;
import controller.LoginController;
import model.dao.DAOPersona;
import model.entity.Persona;
import validation.Validaciones;
import validation.ValidarInputs;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField passClave;
	private JTextField txtMail;
	
	private LoginController controller;
			
	public JPasswordField getPassClave() {
		return passClave;
	}

	public JTextField getTxtMail() {
		return txtMail;
	}

	public LoginView() {
		controller = new LoginController(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Loguearse();
			}
		});
		btnIngresar.setBounds(200, 195, 105, 27);
		contentPane.add(btnIngresar);
		
		passClave = new JPasswordField();
		passClave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		passClave.setBounds(77, 162, 347, 21);
		contentPane.add(passClave);
		
		JLabel lblContrase = new JLabel("Contraseña");
		lblContrase.setBounds(77, 133, 347, 17);
		contentPane.add(lblContrase);
		
		txtMail = new JTextField();
		txtMail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {	

			}
		});
		txtMail.setColumns(10);
		txtMail.setBounds(77, 100, 347, 21);
		contentPane.add(txtMail);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setIcon(new ImageIcon(LoginView.class.getResource("/view/login/images/login32px.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 32));
		lblNewLabel_1.setBackground(new Color(65, 105, 225));
		lblNewLabel_1.setBounds(0, 0, 500, 72);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(77, 71, 347, 17);
		contentPane.add(lblNewLabel);
	}

}
