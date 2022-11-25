package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Analista;
import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.TipoConstancia;
import com.entities.Usuario;
import com.entities.enums.EstadoSolicitudes;

import beans.BeanIntances;
import views.Login;

public class ViewListadoSolicitudConstancias extends JPanel {

	private JTable tableSolicitudes;
	private JTable tableEstudiantes;
	private List<Estudiante> estudiantes = BeanIntances.user().findAllEstudiantes();
	private EstadoSolicitudes filtro;
	private List<Constancia> constancias;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					Estudiante estudiante = BeanIntances.user().findById(Estudiante.class, 15l);
					Analista analista = BeanIntances.user().findById(Analista.class, 9l);
					ViewListadoSolicitudConstancias panel = new ViewListadoSolicitudConstancias(analista);
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
	public ViewListadoSolicitudConstancias(Usuario usu) {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 275, 550, 171);
		add(scrollPane);

		tableSolicitudes = new JTable();
		scrollPane.setViewportView(tableSolicitudes);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(35, 60, 550, 155);
		add(scrollPane_1);

		
		JComboBox comboBoxEstado = new JComboBox();
		// comboBoxEstado.setSelectedIndex(-1);
		comboBoxEstado.addItem(null);
		comboBoxEstado.addItem(EstadoSolicitudes.EN_PROCESO);
		comboBoxEstado.addItem(EstadoSolicitudes.FINALIZADO);
		comboBoxEstado.addItem(EstadoSolicitudes.INGRESADO);
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtro = (EstadoSolicitudes) comboBoxEstado.getSelectedItem();
				cargarConstancias(tableSolicitudes, constancias, filtro);
			}
		});
		comboBoxEstado.setBounds(81, 241, 162, 26);
		add(comboBoxEstado);

		tableEstudiantes = new JTable();
		scrollPane_1.setViewportView(tableEstudiantes);
		tableEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tableEstudiantes.getSelectedRow() != -1) {
					int row = tableEstudiantes.getSelectedRow();
					Long id = Long.valueOf(tableEstudiantes.getModel().getValueAt(row, 0).toString());
					Estudiante unE = null;
					for (Estudiante est : estudiantes) {
						if (est.getIdEstudiante() == id) {
							unE = est;
							break;
						}
					}
					// System.out.println(unE.getConstancias());
					if (unE == null) {
						return;
					}
					constancias = BeanIntances.estudiante().getConstancias(id);
					cargarConstancias(tableSolicitudes, constancias, filtro);
				}
			}
		});

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(35, 246, 60, 17);
		add(lblEstado);

		if (usu instanceof Estudiante) {
			cargarUnEstudiante(tableEstudiantes, (Estudiante) usu);
		} else if (usu instanceof Analista) {
			cargarEstudiantes(tableEstudiantes);
			constancias = BeanIntances.constancia().findAll();
			cargarConstancias(tableSolicitudes, constancias, filtro);
		}

	}

	public void cargarEstudiantes(JTable tabla) {
		String columns[] = { "Id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
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

	public void cargarUnEstudiante(JTable tabla, Estudiante est) {
		String columns[] = { "Id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (tabla != null) {
			Long id = est.getIdEstudiante();
			String doc = est.getDocumento();
			String nombres = est.getNombres();
			String apellidos = est.getApellidos();
			String idITR = est.getItr().getNombre();
			String tipo = est.getEstadoUsuario().toString();
			Object[] datos = { id, doc, nombres, apellidos, idITR, tipo };
			modeloJTable.addRow(datos);

		}
		tabla.setModel(modeloJTable);
	}

	public void cargarConstancias(JTable tabla, List<Constancia> constancias, EstadoSolicitudes filtro) {

		String columns[] = { "Id","Fecha de Solicitus", "Detalle", "Tipo De Constancia", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (constancias == null) {
			return;
		}
		for (Constancia unaC : constancias) {
			if (filtro == null) {
				Long id = unaC.getIdConstancia();
				String evento = unaC.getEvento().getTitulo().toString();
				String fechaSolicitud = unaC.getFechaHora().toString();
				String detalle = unaC.getDetalle().toString();
				String tipoConstancia = unaC.getTipoConstancia().getTipo().toString();
				String estado = unaC.getEstado().toString();
				Object[] datos = {id,evento ,fechaSolicitud, detalle, tipoConstancia, estado};
				modeloJTable.addRow(datos);
			} else {
				if (unaC.getEstado() == filtro) {
					Long id = unaC.getIdConstancia();
					String evento = unaC.getEvento().getTitulo().toString();
					String fechaSolicitud = unaC.getFechaHora().toString();
					String detalle = unaC.getDetalle().toString();
					String tipoConstancia = unaC.getTipoConstancia().getTipo().toString();
					String estado = unaC.getEstado().toString();
					Object[] datos = {id,evento ,fechaSolicitud, detalle, tipoConstancia, estado};
					modeloJTable.addRow(datos);
				}
			}

		}
		tableSolicitudes.setModel(modeloJTable);
	}
}
