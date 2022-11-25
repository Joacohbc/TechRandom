package viewsAnalista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class ViewListadoSolicitudConstancias extends JPanel {
	private JTable tableSolicitudes;
	private JTable tableEstudiantes;

	/**
	 * Create the panel.
	 */
	public ViewListadoSolicitudConstancias() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 275, 550, 171);
		add(scrollPane);
		
		tableSolicitudes = new JTable();
		scrollPane.setViewportView(tableSolicitudes);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(35, 60, 550, 155);
		add(scrollPane_1);
		
		tableEstudiantes = new JTable();
		scrollPane_1.setViewportView(tableEstudiantes);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(35, 246, 60, 17);
		add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(81, 241, 148, 26);
		add(comboBoxEstado);

	}
}
