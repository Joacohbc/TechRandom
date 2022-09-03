package view.rol;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import view.ViewPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaRol extends ViewPanel {
	private JTextField txtRol;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public ConsultaRol() {
		super();
		
		JLabel lblNewLabel = new JLabel("Nombre del Rol");
		lblNewLabel.setBounds(89, 106, 81, 13);
		add(lblNewLabel);
		
		txtRol = new JTextField();
		txtRol.setBounds(217, 103, 282, 19);
		add(txtRol);
		txtRol.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(89, 160, 410, 171);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBuscarRol = new JButton("Buscar Rol");
		btnBuscarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarRol.setBounds(373, 361, 126, 21);
		add(btnBuscarRol);
	}

}
