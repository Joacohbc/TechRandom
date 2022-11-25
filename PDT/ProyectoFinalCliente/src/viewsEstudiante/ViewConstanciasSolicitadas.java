package viewsEstudiante;

import javax.swing.JPanel;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.TipoConstancia;
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;
import views.ViewMedida;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ViewConstanciasSolicitadas extends JPanel implements ViewMedida {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ViewConstanciasSolicitadas(Estudiante estudiante) {

		setBounds(100, 100, 700, 800);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 58, 623, 192);
		add(scrollPane);

		JLabel lblConstanciasDisponibles = new JLabel("Constancias Disponibles.");
		lblConstanciasDisponibles.setBounds(20, 11, 176, 23);
		add(lblConstanciasDisponibles);

		JButton btnDescargarConstancia = new JButton("Descargar Constancia");
		btnDescargarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDescargarConstancia.setBounds(494, 276, 149, 23);
		add(btnDescargarConstancia);

		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "Id", "Evento", "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		
		cargarConstancia(table, estudiante);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(375, 276, 89, 23);
		add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					int row = table.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe Solicitar un Evento");
						return;
						
					}
					
					
					Long idConstancia = (Long) table.getModel().getValueAt(row, 0);
					BeanIntances.constancia().eliminarConstancia(idConstancia);
					Mensajes.MostrarExito("La Solicitud de constancia fue eliminada con exito.");
					cargarConstancia(table, estudiante);
					
					
					
				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());
					
					
				}catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}


				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Modificar Constancia");
		lblNewLabel.setBounds(20, 280, 124, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tipo de constancia");
		lblNewLabel_1_1.setBounds(20, 331, 108, 14);
		add(lblNewLabel_1_1);
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(164, 380, 89, 23);
		add(btnModificar);
		
		
		JComboBox cBoxTipoConstancia = new JComboBox();
		cBoxTipoConstancia.setBounds(123, 327, 130, 23);
		add(cBoxTipoConstancia);
		
		List<TipoConstancia> tconstacia = BeanIntances.tipoConstancia().findAll();

		for (TipoConstancia tipoConstancia : tconstacia) {

			cBoxTipoConstancia.addItem(tipoConstancia);

		}
		
		

	}
	
	public void cargarConstancia(JTable table, Estudiante estudiante) {
		// Cargamos todas las Constancias relacionados con el ID de estudiante
		List<Constancia> constancias = BeanIntances.constancia().sacarConstanciaByIdEstudiante(estudiante.getIdEstudiante());
		String columns[] = {"Id", "Evento" , "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	if (table != null) {
		for (Constancia constancia : constancias) {
			Long id = constancia.getIdConstancia();
			String evento = constancia.getEvento().getTitulo().toString();
			String fechaSolicitud = constancia.getFechaHora().toString();
			String detalle = constancia.getDetalle().toString();
			String tipoConstancia = constancia.getTipoConstancia().getTipo().toString();
			String estado = constancia.getEstado().toString();
			Object[] datos = {id,evento ,fechaSolicitud, detalle, tipoConstancia, estado};
			modeloJTable.addRow(datos);
			
			}
		}
		table.setModel(modeloJTable);				
	}
}
