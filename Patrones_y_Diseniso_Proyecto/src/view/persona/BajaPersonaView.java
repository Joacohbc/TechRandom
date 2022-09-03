package view.persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.DAOPersona;
import model.entity.Persona;
import validation.Mensajes;
import validation.Validaciones;
import view.ViewPanel;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.management.modelmbean.ModelMBeanOperationInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BajaPersonaView extends ViewPanel {
	private JTextField txtDocumento;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public BajaPersonaView() {
		super();

		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(42, 91, 81, 13);
		add(lblNewLabel);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(127, 88, 190, 19);
		add(txtDocumento);
		txtDocumento.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 150, 430, 181);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnEliminarPersona = new JButton("Eliminar Persona");
		btnEliminarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String documento = txtDocumento.getText().trim();

				if (!Validaciones.ValidarCedulaUruguaya(documento)) {
					Mensajes.MostrarError("Error en el documento");
					return;
				}

				try {
					Persona p = DAOPersona.findByDocumento(documento);
					if (p == null) {
						Mensajes.MostrarError("La persona no existe");
						return;
					}

					if (DAOPersona.delete(documento) > 0) {
						Mensajes.MostrarExito("Persona eliminada");
					} else {
						Mensajes.MostrarError("Error al eliminar la persona");
					}

				} catch (SQLException ex) {
					Mensajes.MostrarError("Error al eliminar la persona");
				}
			}
		});
		btnEliminarPersona.setBounds(42, 341, 430, 21);
		add(btnEliminarPersona);

		JButton btnBuscarPersonaEliminar = new JButton("Buscar Persona");
		btnBuscarPersonaEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String documento = txtDocumento.getText().trim();
				if (!Validaciones.ValidarCedulaUruguaya(documento)) {
					Mensajes.MostrarError("Error en el documento");
					return;
				}
				try {
					Persona p = DAOPersona.findByDocumento(documento);
					if (p == null) {
						Mensajes.MostrarError("La persona no existe");
						return;
					}
					DefaultTableModel modelo = PersonaUtils.GetTableModel();
					modelo.addRow(PersonaUtils.ToTableRow(p));
					table.setModel(modelo);

				} catch (SQLException ex) {
					Mensajes.MostrarError("Error al cargar la persona de la BD");
				}

			}
		});
		btnBuscarPersonaEliminar.setBounds(329, 87, 143, 21);
		add(btnBuscarPersonaEliminar);

	}

}
