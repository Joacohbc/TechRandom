package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AsignarRolPersona extends JFrame {

	private JPanel contentPane;
	private JTextField txtFiltrarPersona;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarRolPersona frame = new AsignarRolPersona();
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
	public AsignarRolPersona() {
		setTitle("Asignar Rol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JList lstPersonas = new JList();
		lstPersonas.setBounds(10, 79, 609, 257);
		panel.add(lstPersonas);
		
		txtFiltrarPersona = new JTextField();
		txtFiltrarPersona.setBounds(10, 28, 196, 20);
		panel.add(txtFiltrarPersona);
		txtFiltrarPersona.setColumns(10);
		
		JButton btnFiltrarPersona = new JButton("Filtrar");
		btnFiltrarPersona.setBounds(263, 27, 89, 23);
		panel.add(btnFiltrarPersona);
		
		JComboBox comboRol = new JComboBox();
		comboRol.setBounds(10, 370, 196, 22);
		panel.add(comboRol);
		
		JButton btnAsignarRol = new JButton("Guardar");
		btnAsignarRol.setBounds(263, 370, 89, 23);
		panel.add(btnAsignarRol);
	}
}
