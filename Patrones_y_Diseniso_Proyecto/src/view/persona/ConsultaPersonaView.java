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
import java.util.List;
import java.awt.event.ActionEvent;
import validation.Validaciones;

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
				try {
					String nombre1 = txtPrimerNombre.getText().trim();
					String apellido1 = txtApellido1.getText().trim();

					if (!Validaciones.ValidarNumerosYLetras(nombre1, false)) {
						Mensajes.MostrarError("Error en el nombre1");
						return;
					}

					if (!Validaciones.ValidarNumerosYLetras(apellido1, false)) {
						Mensajes.MostrarError("Error en el apellido1");
						return;
					}

					List<Persona> personas = DAOPersona.findByNombreApellido(nombre1, apellido1);

					DefaultTableModel modelo = PersonaUtils.GetTableModel();
					for (Persona persona : personas) {
						modelo.addRow(PersonaUtils.ToTableRow(persona));
					}
					table.setModel(modelo);

				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al cargar las personas de la base de datos");
					e1.printStackTrace();
				}
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
					String documento = txtDocumento.getText().trim();
					if (!Validaciones.ValidarCedulaUruguaya(documento)) {
						Mensajes.MostrarError("Error en el documento");
						return;
					}
					Persona p = DAOPersona.findByDocumento(documento);
					if (p == null) {
						Mensajes.MostrarError("No existe una persona con el documento ingresado");
						return;
					}
					
					DefaultTableModel modelo = PersonaUtils.GetTableModel();
					modelo.addRow(PersonaUtils.ToTableRow(p));
					table.setModel(modelo);
				
				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al cargar la persona de la base de datos");
					e1.printStackTrace();
				}
			}
		});
		btnBuscarDocumento.setBounds(56, 87, 409, 21);
		add(btnBuscarDocumento);

	}
}
