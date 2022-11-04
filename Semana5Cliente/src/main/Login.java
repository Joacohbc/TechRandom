package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.exceptions.ServiceException;
import com.models.Funcionalidad;
import com.models.Usuario;

import org.hibernate.resource.beans.spi.BeanInstanceProducer;

import beans.BeanIntances;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Login frame;
	private JPanel contentPane;
	private JTextField txtDocumento;
	private JPasswordField txtPassword;
	private JButton btnLoguearse;
	private JButton btnRegistrarse;
	private JButton btnSalir;
	private JButton btnDarDeBaja;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
		setTitle("Sistema de Gestion de Usuarios");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(109, 118, 250, 21);
		contentPane.add(txtDocumento);
		txtDocumento.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(109, 160, 250, 21);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		btnLoguearse = new JButton("Iniciar Sesion");
		btnLoguearse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario usu;

				try {
					usu = BeanIntances.sesion().iniciarSesion(txtDocumento.getText(),
							String.valueOf(txtPassword.getPassword()));
				} catch (ServiceException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (usu == null) {
					JOptionPane.showMessageDialog(null, "No existe el usuario", "Fallo al Iniciar Sesion", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new Perfil(usu,frame).setVisible(true);
			}
		});
		btnLoguearse.setBounds(12, 210, 378, 27);
		contentPane.add(btnLoguearse);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrarse registro = new Registrarse(frame);
				registro.setVisible(true);
			}
		});
		btnRegistrarse.setBounds(12, 247, 378, 27);
		contentPane.add(btnRegistrarse);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(12, 325, 378, 27);
		contentPane.add(btnSalir);
		
		btnDarDeBaja = new JButton("Dar de baja");
		btnDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doc = JOptionPane.showInputDialog("Ingrese el documento que quiere dar de baja:");
				Usuario usu = BeanIntances.usuario().findByDocumento(doc);
				if (usu == null) {
					JOptionPane.showMessageDialog(null, "No existe el usuario", "Fallo al Iniciar Sesion", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					BeanIntances.usuario().remove(usu.getIdUsuario());
					JOptionPane.showMessageDialog(null, "El usuario fue dado de baja con exito.");
				}catch (ServiceException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
		});
		btnDarDeBaja.setBounds(12, 284, 378, 27);
		contentPane.add(btnDarDeBaja);
		
		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(12, 118, 100, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(12, 160, 64, 17);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(156, 45, 45, 27);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/img/usuario (3).png")));
		lblNewLabel_3.setBounds(211, 29, 32, 49);
		contentPane.add(lblNewLabel_3);
	}

	private DefaultTableModel getTableModel() {
		DefaultTableModel model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.setColumnIdentifiers(new String[] { "Permisos", "Acceso" });
		return model;
	}
}
