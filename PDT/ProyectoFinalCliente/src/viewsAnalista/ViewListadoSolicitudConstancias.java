package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entities.AccionConstancia;
import com.entities.Analista;
import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Usuario;
import com.entities.enums.EstadoSolicitudes;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import beans.BeanIntances;
import swingutils.Mensajes;
import validation.Formatos;
import views.Login;
import views.ViewMedida;

public class ViewListadoSolicitudConstancias extends JPanel implements ViewMedida{

	private static final long serialVersionUID = 1L;
	
	private JTable tableSolicitudes;
	private JTable tableEstudiantes;
	private List<Estudiante> estudiantes = BeanIntances.user().findAllEstudiantes();
	private EstadoSolicitudes filtro;
	private List<Constancia> constancias;
	private JComboBox comboBoxEstadosCons;
	private JButton btnModEstCons;
	
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
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 364, 560, 214);
		add(scrollPane);

		tableSolicitudes = new JTable();
		scrollPane.setViewportView(tableSolicitudes);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(35, 52, 560, 214);
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
				constancias = BeanIntances.constancia().findAll();
				cargarConstancias(tableSolicitudes, constancias, filtro);
			}
		});
		comboBoxEstado.setBounds(148, 289, 162, 26);
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

		JLabel lblEstado = new JLabel("Filtro por Estado");
		lblEstado.setBounds(35, 294, 116, 17);
		add(lblEstado);
		
		JLabel lblEstudiantes = new JLabel("Estudiantes");
		lblEstudiantes.setBounds(35, 23, 116, 17);
		add(lblEstudiantes);
		
		JLabel lblConstancias = new JLabel("Constancias");
		lblConstancias.setBounds(35, 335, 98, 17);
		add(lblConstancias);
		
		JLabel lblNewLabel = new JLabel("Seleccione un nuevo estado");
		lblNewLabel.setBounds(35, 600, 191, 17);
		add(lblNewLabel);
		
		comboBoxEstadosCons = new JComboBox();
		comboBoxEstadosCons.setBounds(216, 591, 162, 26);
		add(comboBoxEstadosCons);
		comboBoxEstadosCons.addItem(EstadoSolicitudes.EN_PROCESO);
		comboBoxEstadosCons.addItem(EstadoSolicitudes.FINALIZADO);
		comboBoxEstadosCons.addItem(EstadoSolicitudes.INGRESADO);
		
		
		
		btnModEstCons = new JButton("Modificar Estado Constancia");
		btnModEstCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int row = tableSolicitudes.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar una constancia");
						return;
					}

					Long id = Long.valueOf(tableSolicitudes.getModel().getValueAt(row, 0).toString());
					EstadoSolicitudes estado = (EstadoSolicitudes) comboBoxEstadosCons.getSelectedItem();

					Constancia cons = BeanIntances.constancia().findById(id);
					if(cons == null) {
						Mensajes.MostrarError("No existe una constancia con ese ID");
						return;
					}
					
					AccionConstancia acc = new AccionConstancia();
					acc.setAnalista((Analista) usu);
					acc.setConstancia(cons);
					acc.setDetalle("Se modifico la constancia al estado: " + estado.toString());
					
					if(estado == EstadoSolicitudes.FINALIZADO) {
						if(Mensajes.MostrarSioNo("¿Seguro que quiere finalizar la solicitud de constancia?") == Mensajes.OPCION_NO) 
							return;
						
						byte[] plantilla = BeanIntances.tipoConstancia().descargarPlantilla(cons.getTipoConstancia().getIdTipoConstancia());
						
						PdfReader reader = new PdfReader(plantilla);

						PdfDictionary dict = reader.getPageN(1);
						PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
						if (object instanceof PRStream) {
							PRStream stream = (PRStream) object;
							byte[] data = PdfReader.getStreamBytes(stream);
							String replacedData = new String(data).
									replace("&nombre&", cons.getEstudiante().getNombres()).
									replace("&apellido&", cons.getEstudiante().getApellidos()).
									replace("&documento&", cons.getEstudiante().getDocumento()).
									replace("&generacion&", cons.getEstudiante().getGeneracion().toString()).
									replace("&evento&", cons.getEvento().getTitulo()).
									replace("&fechainicio&", Formatos.ToFormatedString(cons.getEvento().getFechaInicio())).
									replace("&fechafin&", Formatos.ToFormatedString(cons.getEvento().getFechaFin())).
									replace("&modalidad&", cons.getEvento().getModalidad().toString()).
									replace("&lugar&", cons.getEvento().getLocalizacion());
							
							stream.setData(replacedData.getBytes(StandardCharsets.UTF_8));
						}
						
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						PdfStamper stamper = new PdfStamper(reader, baos);
						stamper.close();
						reader.close();
												
						BeanIntances.constancia().updateEstado(id, estado, acc);

						cons.setArchivo(baos.toByteArray());
						BeanIntances.constancia().update(cons);
					} else {
						BeanIntances.constancia().updateEstado(id, estado, acc);
					}
					
					Mensajes.MostrarExito("Se modifico correctamente el estado de la constancia");
					constancias = BeanIntances.constancia().findAll();
					cargarConstancias(tableSolicitudes, constancias, (EstadoSolicitudes) comboBoxEstado.getSelectedItem());
				} catch (Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
					ex.printStackTrace();
				}
	
			}
		});
		btnModEstCons.setBounds(390, 590, 205, 27);
		add(btnModEstCons);

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
		
		String columns[] = { "Id","Evento","Fecha de Solicitud", "Detalle", "Tipo De Constancia", "Estado" };
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
