package view.persona;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.dao.DAOPersona;
import model.entity.Persona;
import validation.Mensajes;
import view.ViewPanel;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		lblNewLabel.setBounds(61, 91, 81, 13);
		add(lblNewLabel);

		txtDocumento = new JTextField();
		txtDocumento.setBounds(144, 88, 147, 19);
		add(txtDocumento);
		txtDocumento.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 150, 378, 181);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnEliminarPersona = new JButton("Eliminar Persona");
		btnEliminarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String documento = txtDocumento.getText();
				try {
					Persona p = DAOPersona.findByDocumento(documento);
					if(p == null) {
						return;
					}
					if (DAOPersona.delete(documento) > 0) {
						Mensajes.MostrarExito("Persona eliminada");
					} else {
						Mensajes.MostrarError("ERROR");
					}
				} catch (SQLException ex) {
					Mensajes.MostrarError("ERROR");
				}
			}
		});
		btnEliminarPersona.setBounds(329, 341, 110, 21);
		add(btnEliminarPersona);

		JButton btnBuscarPersonaEliminar = new JButton("Buscar Persona");
		btnBuscarPersonaEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String documento = txtDocumento.getText();
				try {
					Persona p = DAOPersona.findByDocumento(documento);
					DefaultTableModel modeloTabla = new DefaultTableModel(new String[] { "DOCUMENTO", "NOMBRE1",
							"NOMBRE2", "APELLIDO1", "APELLIDO2", "FECHA NACIMIENTO", "CORREO" }, 0);
					modeloTabla.addRow(new Object[] { p.getDocumento(), p.getNombre1(), p.getNombre2(),
							p.getApellido1(), p.getApellido2(), p.getFechaNacimiento().toString(), p.getMail() });
					table.setModel(modeloTabla);

				} catch (SQLException ex) {
					Mensajes.MostrarError("ERROR");
				}

			}
		});
		btnBuscarPersonaEliminar.setBounds(329, 87, 110, 21);
		add(btnBuscarPersonaEliminar);

	}

}
