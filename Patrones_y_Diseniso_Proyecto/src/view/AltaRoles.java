package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AltaRoles extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreRol;
	private JTextField txtDescRol;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaRoles frame = new AltaRoles();
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
	public AltaRoles() {
		setTitle("Alta Rol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNombreRol = new JLabel("Nombre Rol");
		lblNombreRol.setBounds(20, 44, 96, 14);
		panel.add(lblNombreRol);
		
		JLabel lblDescRol = new JLabel("Descripción");
		lblDescRol.setBounds(20, 85, 96, 14);
		panel.add(lblDescRol);
		
		txtNombreRol = new JTextField();
		txtNombreRol.setBounds(126, 38, 183, 20);
		panel.add(txtNombreRol);
		txtNombreRol.setColumns(10);
		
		txtDescRol = new JTextField();
		txtDescRol.setBounds(126, 79, 183, 20);
		panel.add(txtDescRol);
		txtDescRol.setColumns(10);
		
		JButton btnCrearRol = new JButton("Crear");
		btnCrearRol.setBounds(149, 160, 89, 23);
		panel.add(btnCrearRol);
	}

}
