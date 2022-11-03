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

import beans.BeanIntances;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Login frame;
	private JPanel contentPane;
	private JTextField txtDocumento;
	private JPasswordField txtPassword;
	private JButton btnLogOut;
	private JButton btnLoguearse;
	private JTable tableFuncionalidades;
	private JScrollPane scrollPane;
	private JButton btnRegistrarse;
	private JButton btnSalir;

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
			@Override
			public void windowClosed(WindowEvent e) {
					btnLogOut.doClick();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(12, 12, 378, 21);
		contentPane.add(txtDocumento);
		txtDocumento.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(12, 45, 378, 21);
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

				List<Funcionalidad> funcs = BeanIntances.funcionalidad().findAll();
				List<Funcionalidad> funcsUsu = usu.getRole().getFuncionalidades();

				DefaultTableModel model = getTableModel();
				for (Funcionalidad funcionalidad : funcs) {
					if (funcsUsu.contains(funcionalidad))
						model.addRow(new String[] { funcionalidad.toString(), "Tienes acceso" });
					else
						model.addRow(new String[] { funcionalidad.toString(), "No tienes acceso" });
				}
				tableFuncionalidades.setModel(model);
				tableFuncionalidades.setEnabled(true);
				btnLogOut.setEnabled(true);

				txtDocumento.setEditable(false);
				txtDocumento.setEditable(false);
				txtPassword.setEditable(false);
				btnLoguearse.setEnabled(false);
			}
		});
		btnLoguearse.setBounds(12, 78, 378, 27);
		contentPane.add(btnLoguearse);

		btnLogOut = new JButton("Cerrar Sesion");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BeanIntances.sesion().cerrarSesion(txtDocumento.getText());

					tableFuncionalidades.setEnabled(false);
					tableFuncionalidades.setModel(getTableModel());
					btnLogOut.setEnabled(false);

					txtDocumento.setEditable(true);
					txtDocumento.setText("");
					txtPassword.setEditable(true);
					txtPassword.setText("");
					btnLoguearse.setEnabled(true);

				} catch (ServiceException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnLogOut.setEnabled(false);
		btnLogOut.setBounds(12, 275, 378, 27);
		contentPane.add(btnLogOut);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 117, 375, 146);
		contentPane.add(scrollPane);

		tableFuncionalidades = new JTable();
		scrollPane.setViewportView(tableFuncionalidades);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(12, 314, 378, 27);
		contentPane.add(btnRegistrarse);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogOut.doClick();
				frame.dispose();
			}
		});
		btnSalir.setBounds(12, 353, 378, 27);
		contentPane.add(btnSalir);
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
