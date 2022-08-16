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

public class AltaFuncionalidad extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreFunc;
	private JTextField txtDescFunc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaFuncionalidad frame = new AltaFuncionalidad();
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
	public AltaFuncionalidad() {
		setTitle("Alta Funcionalidad");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNombreFunc = new JLabel("Nombre Funcionalidad");
		lblNombreFunc.setBounds(3, 45, 165, 14);
		panel.add(lblNombreFunc);
		
		JLabel lblDescFunc = new JLabel("Descripción");
		lblDescFunc.setBounds(3, 93, 165, 14);
		panel.add(lblDescFunc);
		
		txtNombreFunc = new JTextField();
		txtNombreFunc.setBounds(178, 39, 215, 20);
		panel.add(txtNombreFunc);
		txtNombreFunc.setColumns(10);
		
		txtDescFunc = new JTextField();
		txtDescFunc.setBounds(178, 87, 215, 20);
		panel.add(txtDescFunc);
		txtDescFunc.setColumns(10);
		
		JButton btnAltaFunc = new JButton("Crear");
		btnAltaFunc.setBounds(215, 205, 89, 23);
		panel.add(btnAltaFunc);
		
		JComboBox comboRol = new JComboBox();
		comboRol.setBounds(178, 138, 215, 22);
		panel.add(comboRol);
		
		JLabel lblRol = new JLabel("Seleccione un rol");
		lblRol.setBounds(3, 153, 124, 14);
		panel.add(lblRol);
	}

}
