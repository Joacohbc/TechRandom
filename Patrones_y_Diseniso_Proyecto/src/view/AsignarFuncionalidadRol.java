package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AsignarFuncionalidadRol extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsignarFuncionalidadRol frame = new AsignarFuncionalidadRol();
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
	public AsignarFuncionalidadRol() {
		setTitle("Asignar funcionalidad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox comboRoles = new JComboBox();
		comboRoles.setBounds(10, 86, 198, 22);
		panel.add(comboRoles);
		
		JComboBox comboFunc = new JComboBox();
		comboFunc.setBounds(278, 86, 198, 22);
		panel.add(comboFunc);
		
		JButton btnAsignarFunc = new JButton("Asignar");
		btnAsignarFunc.setBounds(193, 221, 89, 23);
		panel.add(btnAsignarFunc);
		
		JLabel lblRol = new JLabel("Seleccione un rol");
		lblRol.setBounds(10, 55, 174, 14);
		panel.add(lblRol);
		
		JLabel lblfuncionalidad = new JLabel("Seleccione una funcionalidad");
		lblfuncionalidad.setBounds(278, 55, 174, 14);
		panel.add(lblfuncionalidad);
	}

}
