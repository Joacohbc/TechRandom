package view.rol;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.dao.DAORol;
import model.entity.Funcionalidad;
import model.entity.Rol;
import validation.Mensajes;
import view.ViewPanel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
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
		lblNewLabel.setBounds(54, 106, 119, 13);
		add(lblNewLabel);

		txtRol = new JTextField();
		txtRol.setBounds(178, 103, 282, 19);
		add(txtRol);
		txtRol.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 160, 410, 171);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnBuscarRol = new JButton("Buscar Rol");
		btnBuscarRol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (!txtRol.getText().trim().isBlank()) {
						
						Rol rol = DAORol.findByNombre(txtRol.getText().trim());
						if(rol == null) {
							Mensajes.MostrarError("El rol seleccionado no existe");
							return;
						}
						
						DefaultTableModel modelo = RolUtils.GetTableModelFunc();


						
						for(Funcionalidad f : rol.getFuncionalidades()) {
							Object[] func = { rol.getId(), rol.getNombre(), rol.getDescripcion(), f.getNombre() };
							modelo.addRow(func);
						}
						
						table.setModel(modelo);

					} else {
						List<Rol> roles = DAORol.findAll();
						DefaultTableModel modelo = RolUtils.GetTableModel();

						for (Rol rol : roles) {
							Object[] fila = { rol.getId(), rol.getNombre(), rol.getDescripcion() };
							modelo.addRow(fila);
						}

						table.setModel(modelo);

					}
					
				} catch (SQLException e1) {
					Mensajes.MostrarError("Error al cargar rol de la BD");
					e1.printStackTrace();
				}
			}
		});
		btnBuscarRol.setBounds(54, 343, 410, 21);
		add(btnBuscarRol);
	}

}
