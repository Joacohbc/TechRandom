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
	
	
	public Perfil(Usuario usu, Login l) {
		l.setVisible(false);
		frame =this;
		setTitle("Mi Perfil");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
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
					System.out.println(BeanIntances.sesion().getUsuarios());
					BeanIntances.sesion().cerrarSesion(usu.getDocumento());
					frame.dispose();
				} catch (ServiceException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Operaccion Fallida", JOptionPane.ERROR_MESSAGE);
					return;
				}
				l.setVisible(true);
			}
		});
		btnLogOut.setBounds(9, 386, 378, 27);
		contentPane.add(btnLogOut);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 46, 375, 258);
		contentPane.add(scrollPane);

		tableFuncionalidades = new JTable();
		scrollPane.setViewportView(tableFuncionalidades);
		
		btnActualizar = new JButton("Actualizar");
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
		btnActualizar.setBounds(12, 349, 378, 27);
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
