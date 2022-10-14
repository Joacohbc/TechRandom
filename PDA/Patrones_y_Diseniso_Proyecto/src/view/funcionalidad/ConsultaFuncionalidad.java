package view.funcionalidad;

import view.ViewPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.entity.Funcionalidad;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultaFuncionalidad extends ViewPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	public ConsultaFuncionalidad() {
		super();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				DefaultTableModel modelo = new DefaultTableModel();

				Object[] columnas = { "ID", "Nombre", "Descripcion" };
				modelo.setColumnIdentifiers(columnas);

				for (Funcionalidad f : Funcionalidad.values()) {
					Object[] fila = { f.getId(), f.getNombre(), f.getDescripcion() };
					modelo.addRow(fila);
				}
				table.setModel(modelo);
			}
		});
		setLayout(new BorderLayout(0, 0));

		table = new JTable();
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

}
