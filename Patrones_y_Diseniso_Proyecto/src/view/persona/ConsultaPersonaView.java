package view.persona;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.dao.DAOPersona;
import model.entity.Persona;
import validation.Formatos;
import validation.Mensajes;
import view.ViewPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ConsultaPersonaView extends ViewPanel {
	private JTextField txtApellido1;
	private JTextField txtPrimerNombre;
	private JTable table;
	private JTextField txtDocumento;

	/**
	 * Create the panel.
	 */
	public ConsultaPersonaView() {
		super();
		
		JLabel lblNewLabel = new JLabel("Primer Apellido");
		lblNewLabel.setBounds(56, 123, 110, 13);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Primer Nombre");
		lblNewLabel_1.setBounds(56, 151, 126, 13);
		add(lblNewLabel_1);
		
		txtApellido1 = new JTextField();
		txtApellido1.setBounds(184, 120, 281, 19);
		add(txtApellido1);
		txtApellido1.setColumns(10);
		
		txtPrimerNombre = new JTextField();
		txtPrimerNombre.setBounds(184, 148, 281, 19);
		add(txtPrimerNombre);
		txtPrimerNombre.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 208, 409, 171);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBuscarNomApe = new JButton("Buscar Persona");
		btnBuscarNomApe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBuscarNomApe.setBounds(56, 175, 409, 21);
		add(btnBuscarNomApe);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(56, 62, 110, 13);
		add(lblDocumento);
		
		txtDocumento = new JTextField();
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(184, 59, 281, 19);
		add(txtDocumento);
		
		JButton btnBuscarDocumento = new JButton("Buscar Persona");
		btnBuscarDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Persona p = DAOPersona.findByDocumento(txtDocumento.getText());
					if(p == null) {
						Mensajes.MostrarError("No existe una persona con el documento ingresado");
						return;
					}
					

					DefaultTableModel modelo = PersonaUtils.GetTableModel();
					
					Object[] fila = {
						p.getId(),
						p.getApellido1(),
						p.getApellido2(),
						p.getNombre1(),
						p.getNombre2(),
						Formatos.ToFormatedString(p.getFechaNacimiento()),
						p.getMail(),
						p.getRol().getNombre()
					};
					
					modelo.addRow(fila);
					table.setModel(modelo);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBuscarDocumento.setBounds(56, 87, 409, 21);
		add(btnBuscarDocumento);

	}
}
