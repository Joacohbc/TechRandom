package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AltaPersona extends JFrame {

	private JPanel contentPane;
	private JTextField txtDocumento;
	private JTextField txtNombre1;
	private JTextField txtNombre2;
	private JTextField txtApellido1;
	private JTextField txtApellido2;
	private JTextField txtFechaNac;
	private JTextField txtCorreo;
	private JTextField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaPersona frame = new AltaPersona();
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
	public AltaPersona() {
		setTitle("Alta Persona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(10, 36, 211, 14);
		panel.add(lblDocumento);
		
		JLabel lblNombre2 = new JLabel("Segundo Nombre");
		lblNombre2.setBounds(10, 136, 211, 14);
		panel.add(lblNombre2);
		
		JLabel lblApellido1 = new JLabel("Primer Apellido");
		lblApellido1.setBounds(10, 186, 211, 14);
		panel.add(lblApellido1);
		
		JLabel lblApellido2 = new JLabel("Segundo Apellido");
		lblApellido2.setBounds(10, 236, 211, 14);
		panel.add(lblApellido2);
		
		JLabel lblFechaNac = new JLabel("Fecha Nacimiento");
		lblFechaNac.setBounds(10, 286, 211, 14);
		panel.add(lblFechaNac);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(10, 336, 211, 14);
		panel.add(lblCorreo);
		
		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(10, 386, 211, 14);
		panel.add(lblClave);
		
		JLabel lblNombre1 = new JLabel("Primer Nombre");
		lblNombre1.setBounds(10, 86, 211, 14);
		panel.add(lblNombre1);
		
		txtDocumento = new JTextField();
		txtDocumento.setBounds(113, 30, 235, 20);
		panel.add(txtDocumento);
		txtDocumento.setColumns(10);
		
		txtNombre1 = new JTextField();
		txtNombre1.setBounds(113, 80, 235, 20);
		panel.add(txtNombre1);
		txtNombre1.setColumns(10);
		
		txtNombre2 = new JTextField();
		txtNombre2.setBounds(113, 130, 235, 20);
		panel.add(txtNombre2);
		txtNombre2.setColumns(10);
		
		txtApellido1 = new JTextField();
		txtApellido1.setBounds(113, 180, 235, 20);
		panel.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		txtApellido2 = new JTextField();
		txtApellido2.setBounds(113, 233, 235, 20);
		panel.add(txtApellido2);
		txtApellido2.setColumns(10);
		
		txtFechaNac = new JTextField();
		txtFechaNac.setBounds(113, 280, 235, 20);
		panel.add(txtFechaNac);
		txtFechaNac.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(113, 330, 235, 20);
		panel.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtClave = new JTextField();
		txtClave.setBounds(113, 380, 235, 20);
		panel.add(txtClave);
		txtClave.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(355, 457, 89, 23);
		panel.add(btnCrear);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(113, 430, 235, 22);
		panel.add(comboBox);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(10, 438, 46, 14);
		panel.add(lblRol);
	}
}
