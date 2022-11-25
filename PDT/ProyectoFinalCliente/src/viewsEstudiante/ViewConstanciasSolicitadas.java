package viewsEstudiante;

import javax.swing.JPanel;

import com.entities.Constancia;
import com.entities.Estudiante;

import beans.BeanIntances;
import views.ViewMedida;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewConstanciasSolicitadas extends JPanel implements ViewMedida {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ViewConstanciasSolicitadas(Estudiante estudiante) {

		setBounds(100, 100, 734, 800);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 211, 623, 192);
		add(scrollPane);

		JLabel lblConstanciasDisponibles = new JLabel("Constancias Disponibles.");
		lblConstanciasDisponibles.setBounds(20, 118, 152, 23);
		add(lblConstanciasDisponibles);

		JButton btnDescargarConstancia = new JButton("Descargar Constancia");
		btnDescargarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDescargarConstancia.setBounds(473, 432, 170, 23);
		add(btnDescargarConstancia);

		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "Id", "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		
		cargarConstancia(table, estudiante);
		
		

	}
	
	public void cargarConstancia(JTable table, Estudiante estudiante) {
		// Cargamos todas las Constancias relacionados con el ID de estudiante
		List<Constancia> constancias = BeanIntances.constancia().sacarConstanciaByIdEstudiante(estudiante.getIdEstudiante());
		String columns[] = {"Id", "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	if (table != null) {
		for (Constancia constancia : constancias) {
			Long id = constancia.getIdConstancia();
			String fechaSolicitud = constancia.getFechaHora().toString();
			String detalle = constancia.getDetalle().toString();
			String tipoConstancia = constancia.getTipoConstancia().getTipo().toString();
			String estado = constancia.getEstado().toString();
			Object[] datos = {id, fechaSolicitud, detalle, tipoConstancia, estado};
			modeloJTable.addRow(datos);
			
			}
		}
		table.setModel(modeloJTable);				
	}
}
