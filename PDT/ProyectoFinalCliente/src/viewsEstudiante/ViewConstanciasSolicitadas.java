package viewsEstudiante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.TipoConstancia;
import com.entities.enums.EstadoSolicitudes;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;
import views.ViewMedida;
import javax.swing.JSeparator;

public class ViewConstanciasSolicitadas extends JPanel implements ViewMedida {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private JComboBox cmbTipoConstancia;
	private JComboBox cmbEstadoConstancia;

	/**
	 * Create the panel.
	 */
	public ViewConstanciasSolicitadas(Estudiante estudiante) {

		setBounds(100, 100, 700, 800);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 69, 623, 192);
		add(scrollPane);

		JLabel lblConstanciasDisponibles = new JLabel("Constancias Disponibles.");
		lblConstanciasDisponibles.setBounds(20, 11, 176, 23);
		add(lblConstanciasDisponibles);

		JButton btnDescargarConstancia = new JButton("Descargar Constancia");
		btnDescargarConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int row = table.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar un Constancia");
						return;

					}

					Long idConstancia = (Long) table.getModel().getValueAt(row, 0);
					byte[] archivo = BeanIntances.constancia().descargarConstancia(idConstancia);
					if (archivo == null) {
						Mensajes.MostrarError("El archivo esta vacio");
						return;
					}

					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String ubicacionPDF = Path.of(fc.getSelectedFile().getAbsolutePath(), "constancia.pdf")
								.toString();

						try {
							FileOutputStream pdf = new FileOutputStream(ubicacionPDF);
							pdf.write(archivo);
							pdf.close();

							JOptionPane.showMessageDialog(null,
									"Constancia descaragada correctamente en " + ubicacionPDF);
						} catch (FileNotFoundException e1) {
							Mensajes.MostrarError("La ruta: " + ubicacionPDF + " no es una ruta valida");
							e1.printStackTrace();
						} catch (IOException e1) {
							Mensajes.MostrarError("Ocurrio un error al guardar la constancia: " + e1.getMessage());
							e1.printStackTrace();
						}

					}

				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());

				} catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}

			}
		});
		btnDescargarConstancia.setBounds(480, 276, 163, 23);
		add(btnDescargarConstancia);

		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "Id", "Evento", "Fecha de Solicitud", "Detalle", "Tipo De constancia", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(384, 276, 89, 23);
		add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int row = table.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar un Constancia");
						return;

					}

					Long idConstancia = (Long) table.getModel().getValueAt(row, 0);
					BeanIntances.constancia().eliminarConstancia(idConstancia);
					Mensajes.MostrarExito("La Solicitud de constancia fue eliminada con exito.");
					
					if(cmbTipoConstancia.getSelectedItem() == null) {						
						cargarConstancia(table, estudiante, null);
					}else {
						cargarConstancia(table, estudiante, (EstadoSolicitudes) cmbEstadoConstancia.getSelectedItem());
					}
				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());

				} catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}

			}
		});

		JLabel lblNewLabel = new JLabel("Modificar Constancia");
		lblNewLabel.setBounds(20, 335, 191, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Tipo de constancia");
		lblNewLabel_1_1.setBounds(20, 361, 130, 14);
		add(lblNewLabel_1_1);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int row = table.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar un Constancia");
						return;
					}

					Long idConstancia = (Long) table.getModel().getValueAt(row, 0);
					Constancia cons = BeanIntances.constancia().findById(idConstancia);
					cons.setTipoConstancia((TipoConstancia) cmbTipoConstancia.getSelectedItem());
					BeanIntances.constancia().update(cons);
					Mensajes.MostrarExito("La constancia se modifico con exito");

					if (cmbTipoConstancia.getSelectedItem() == null) {
						cargarConstancia(table, estudiante, null);
					} else {
						cargarConstancia(table, estudiante, (EstadoSolicitudes) cmbEstadoConstancia.getSelectedItem());
					}

				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());

				} catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}

			}
		});
		btnModificar.setBounds(166, 387, 256, 23);
		add(btnModificar);

		cmbTipoConstancia = new JComboBox();
		cmbTipoConstancia.setBounds(166, 357, 256, 23);
		add(cmbTipoConstancia);

		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbTipoConstancia.getSelectedItem() == null) {
					cargarConstancia(table, estudiante, null);
				} else {
					cargarConstancia(table, estudiante, (EstadoSolicitudes) cmbEstadoConstancia.getSelectedItem());
				}
			}
		});
		btnNewButton.setBounds(262, 276, 115, 23);
		add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 318, 629, 2);
		add(separator);

		cmbEstadoConstancia = new JComboBox();
		cmbEstadoConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbTipoConstancia.getSelectedItem() == null) {
					cargarConstancia(table, estudiante, null);
				} else {
					cargarConstancia(table, estudiante, (EstadoSolicitudes) cmbEstadoConstancia.getSelectedItem());
				}
			}
		});
		cmbEstadoConstancia.addItem(null);
		cmbEstadoConstancia.addItem(EstadoSolicitudes.INGRESADO);
		cmbEstadoConstancia.addItem(EstadoSolicitudes.EN_PROCESO);
		cmbEstadoConstancia.addItem(EstadoSolicitudes.FINALIZADO);
		cmbEstadoConstancia.setBounds(98, 274, 152, 23);
		add(cmbEstadoConstancia);

		JLabel lblNewLabel_1_1_1 = new JLabel("Filtrar");
		lblNewLabel_1_1_1.setBounds(20, 279, 81, 14);
		add(lblNewLabel_1_1_1);

		List<TipoConstancia> tconstacia = BeanIntances.tipoConstancia().findAll();

		for (TipoConstancia tipoConstancia : tconstacia) {
			cmbTipoConstancia.addItem(tipoConstancia);
		}
		cargarConstancia(table, estudiante, null);
	}

	public void cargarConstancia(JTable table, Estudiante estudiante, EstadoSolicitudes estadoFiltro) {
		// Cargamos todas las Constancias relacionados con el ID de estudiante
		List<Constancia> constancias = BeanIntances.constancia()
				.sacarConstanciaByIdEstudiante(estudiante.getIdEstudiante());
		String columns[] = { "Id", "Evento", "Fecha de Solicitud", "Detalle", "Tipo de Constancia", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (table != null) {
			for (Constancia constancia : constancias) {
				if (estadoFiltro == null) {
					Long id = constancia.getIdConstancia();
					String evento = constancia.getEvento().getTitulo().toString();
					String fechaSolicitud = constancia.getFechaHora().toString();
					String detalle = constancia.getDetalle().toString();
					String tipoConstancia = constancia.getTipoConstancia().getTipo().toString();
					String estado = constancia.getEstado().toString();
					Object[] datos = { id, evento, fechaSolicitud, detalle, tipoConstancia, estado };
					modeloJTable.addRow(datos);
				} else {
					if (estadoFiltro == constancia.getEstado()) {
						Long id = constancia.getIdConstancia();
						String evento = constancia.getEvento().getTitulo().toString();
						String fechaSolicitud = constancia.getFechaHora().toString();
						String detalle = constancia.getDetalle().toString();
						String tipoConstancia = constancia.getTipoConstancia().getTipo().toString();
						String estado = constancia.getEstado().toString();
						Object[] datos = { id, evento, fechaSolicitud, detalle, tipoConstancia, estado };
						modeloJTable.addRow(datos);
					}
				}

			}
		}
		table.setModel(modeloJTable);
	}
}
