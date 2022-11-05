package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.ServiceException;
import com.models.Rol;
import com.models.Usuario;

import org.hibernate.resource.beans.spi.BeanInstanceProducer;

import beans.BeanIntances;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window.Type;

public class Actualizar extends JFrame {

	private JPanel contentPane;
	private JTextField textnombre;
	private JTextField textApellido;
	private JTextField textClave;
	private JTextField textEmail;
	private JTextField textDocumento;
	private JComboBox <Rol> comboBoxRol;

	/**
	 * Launch the application.
	 */
	private  Actualizar frame;

	/**
	 * Create the frame.
	 */
	public Actualizar(JFrame l, Usuario usu) {
		frame = this;
		l.setVisible(false);
		setResizable(false);
		setType(Type.POPUP);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				List<Rol> roles = BeanIntances.rol().findAll();
				for (Rol r : roles) {
					comboBoxRol.addItem(r);
				}
				textnombre.setText(usu.getNombre());
				textApellido.setText(usu.getApellido());
				textEmail.setText(usu.getMail());
				textClave.setText(usu.getClave());
				textDocumento.setText(usu.getDocumento());
				textDocumento.setEditable(false);
				for (int i = 0 ; i<comboBoxRol.getItemCount();i++) {
					if(comboBoxRol.getItemAt(i).equals(usu.getRole())) {
						comboBoxRol.setSelectedIndex(i);
					}
				}
				//comboBoxRol.setSelectedItem(u.getRole());
			}
			@Override
			public void windowClosing(WindowEvent e) {
				l.setVisible(true);
			}
		});
		setTitle("Registro de Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Actualizar");
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
		textnombre.setBounds(207, 137, 119, 19);
		contentPane.add(textnombre);
		textnombre.setColumns(10);
		
		
		textApellido = new JTextField();
		textApellido.setBounds(207, 160, 119, 19);
		contentPane.add(textApellido);
		textApellido.setColumns(10);
		
		textClave = new JTextField();
		textClave.setBounds(207, 183, 119, 19);
		contentPane.add(textClave);
		textClave.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(207, 206, 119, 19);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textDocumento = new JTextField();
		textDocumento.setBounds(207, 229, 119, 19);
		contentPane.add(textDocumento);
		textDocumento.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Rol");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(76, 253, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(207, 258, 119, 25);
		contentPane.add(comboBoxRol);
		
		
		JButton btnRegistrarse = new JButton("Actualizar");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario u = new Usuario();
				try {
					u.setIdUsuario(usu.getIdUsuario());
					u.setNombre(textnombre.getText());
					u.setApellido(textApellido.getText());
					u.setDocumento(textDocumento.getText());
					u.setMail(textEmail.getText());
					u.setClave(textClave.getText());
					u.setRole((Rol)comboBoxRol.getSelectedItem());
					String validar = BeanIntances.usuario().validarUsuario(u);
					if( validar != null) {
						JOptionPane.showMessageDialog(null,validar, "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
						return;
					}
					BeanIntances.usuario().update(u);
					JOptionPane.showMessageDialog(null, "El usuario fue actualizado con exito.");
					frame.dispose();
					l.setVisible(true);
				}catch(ServiceException | EntityAlreadyExistsException ex ) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnRegistrarse.setBounds(229, 340, 134, 25);
		contentPane.add(btnRegistrarse);
	}
}
