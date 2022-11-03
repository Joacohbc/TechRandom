package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exceptions.ServiceException;

import beans.BeanIntances;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registrarse extends JFrame {

	private JPanel contentPane;
	private JTextField textnombre;
	private JTextField textApellido;
	private JTextField textClave;
	private JTextField textEmail;
	private JTextField textDocumento;

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
		setTitle("Registro de Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrarse");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(186, 39, 104, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(76, 138, 85, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(76, 161, 85, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Clave");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(76, 184, 85, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(76, 207, 85, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Documento");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(76, 230, 85, 13);
		contentPane.add(lblNewLabel_5);
		
		textnombre = new JTextField();
		textnombre.setBounds(207, 137, 96, 19);
		contentPane.add(textnombre);
		textnombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setBounds(207, 160, 96, 19);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		textClave = new JTextField();
		textClave.setBounds(207, 183, 96, 19);
		contentPane.add(textClave);
		textClave.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(207, 206, 96, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textDocumento = new JTextField();
		textDocumento.setBounds(207, 229, 96, 19);
		contentPane.add(textDocumento);
		textDocumento.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Rol");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(76, 253, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(207, 258, 96, 25);
		contentPane.add(comboBoxRol);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BeanIntances.usuario().
				}catch(ServiceException ex) {
					
				}
			}
		});
		btnRegistrarse.setBounds(281, 343, 85, 21);
		contentPane.add(btnRegistrarse);
	}
}
