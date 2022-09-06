package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtCorreo;
	private JTextField txtClave;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(49, 69, 61, 14);
		panel.add(lblCorreo);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(49, 137, 61, 14);
		panel.add(lblClave);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(138, 63, 166, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setBounds(138, 131, 166, 20);
		panel.add(txtClave);
		txtClave.setColumns(10);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(215, 203, 89, 23);
		panel.add(btnIngresar);
	}

}
