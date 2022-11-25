package viewsAnalista;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.TipoConstancia;
import com.entities.enums.EstadoSolicitudes;

import beans.BeanIntances;
import views.Login;

public class ViewListadoSolicitudConstancias extends JPanel {
	
	private JTable tableSolicitudes;
	private JTable tableEstudiantes;
	private List<Estudiante> estudiantes = BeanIntances.user().findAllEstudiantes();
	private List<Constancia> constancias = BeanIntances.constancia().findAll();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					ViewListadoSolicitudConstancias panel = new ViewListadoSolicitudConstancias();
					frame.setContentPane(panel);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
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
		tableEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tableEstudiantes.getSelectedRow() != -1) {
					int row = tableEstudiantes.getSelectedRow();
					Long id = Long.valueOf(tableEstudiantes.getModel().getValueAt(row, 0).toString());
					Estudiante unE = null;
					for(Estudiante est : estudiantes) {
						if(est.getIdEstudiante()==id) {
							unE=est;
							break;
						}
							
						//unE = ((est.getIdEstudiante()==id ? est:null));
						
					}
					System.out.println(unE.getConstancias());
					if(unE==null) {
						return;
					}
					List<Constancia> constancias = unE.getConstancias();
					String columns[] = { "Id", "Detalle", "Tipo"};
					DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					
					for (Constancia unaC : constancias) {
						
						Long idC = unaC.getIdConstancia();
						String detalle = unaC.getDetalle();
						TipoConstancia tipo = unaC.getTipoConstancia();

						Object[] datos = { idC, detalle, tipo};
						modeloJTable.addRow(datos);
					}
					tableSolicitudes.setModel(modeloJTable);
					
				}
			}
		});
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(35, 246, 60, 17);
		add(lblEstado);
		
		JComboBox comboBoxEstado = new JComboBox();
		comboBoxEstado.setBounds(81, 241, 162, 26);
		add(comboBoxEstado);
		comboBoxEstado.addItem(EstadoSolicitudes.EN_PROCESO);
		comboBoxEstado.addItem(EstadoSolicitudes.FINALIZADO);
		comboBoxEstado.addItem(EstadoSolicitudes.INGRESADO);
		
		cargarEstudiantes(tableEstudiantes);

	}
	public void cargarEstudiantes(JTable tabla) {
		String columns[] = { "Id", "Documento", "Nombres", "Apellidos", "ITR", "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (tabla != null) {
			for (Estudiante est : estudiantes) {
				Long id = est.getIdEstudiante();
				String doc = est.getDocumento();
				String nombres = est.getNombres();
				String apellidos = est.getApellidos();
				String idITR = est.getItr().getNombre();
				String tipo = est.getEstadoUsuario().toString();
				Object[] datos = { id, doc, nombres, apellidos, idITR, tipo };
				modeloJTable.addRow(datos);
			}
		}
		tabla.setModel(modeloJTable);
	}
}
