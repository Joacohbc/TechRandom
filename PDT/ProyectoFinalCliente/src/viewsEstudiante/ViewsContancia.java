package viewsEstudiante;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewsContancia extends JPanel {

	private JPanel contentPane;
	private JTable tableConstancia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewsContancia frame = new ViewsContancia();
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
	public ViewsContancia() {
		setBounds(100, 100, 679, 624);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 338, 623, 192);
		add(scrollPane);
		
		tableConstancia = new JTable();
		tableConstancia.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", "", null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Titulo", "Fecha de Inicio", "Fecha de fin", "Modalidad", "Localizacion", "Solicitar Constancia"
			}
		));
		tableConstancia.getColumnModel().getColumn(1).setPreferredWidth(93);
		tableConstancia.getColumnModel().getColumn(5).setPreferredWidth(115);
		scrollPane.setViewportView(tableConstancia);
		
		JButton btnNewButton = new JButton("Solicitar");
		btnNewButton.setBounds(511, 543, 89, 23);
		add(btnNewButton);
		

	}
	
	public void cargarEventos() {
		
		
	}
	
	
}
