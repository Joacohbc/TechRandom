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

public class Perfil extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Perfil frame;
	private JPanel contentPane;
	private JButton btnLogOut;
	private JTable tableFuncionalidades;
	private JScrollPane scrollPane;
	private JButton btnActualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Perfil();
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
	public Perfil() {
		setTitle("Mi Perfil");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
					btnLogOut.doClick();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnLogOut = new JButton("Cerrar Sesion");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BeanIntances.sesion().cerrarSesion(txtDocumento.getText());

					tableFuncionalidades.setEnabled(false);
					tableFuncionalidades.setModel(getTableModel());
					btnLogOut.setEnabled(false);
					btnActualizar.setEnabled(false);
					btnDarDeBaja.setEnabled(false);

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
		btnLogOut.setBounds(12, 352, 378, 27);
		contentPane.add(btnLogOut);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 46, 375, 258);
		contentPane.add(scrollPane);

		tableFuncionalidades = new JTable();
		scrollPane.setViewportView(tableFuncionalidades);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String doc = JOptionPane.showInputDialog("Ingrese el documento que quiere actualizar:");
				Usuario usu = BeanIntances.usuario().findByDocumento(doc);
				if (usu == null) {
					JOptionPane.showMessageDialog(null, "No existe el usuario", "Fallo al Iniciar Sesion", JOptionPane.ERROR_MESSAGE);
					return;
				}
				new Actualizar(frame, usu).setVisible(true);
			}
		});
		btnActualizar.setBounds(12, 389, 378, 27);
		contentPane.add(btnActualizar);
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
