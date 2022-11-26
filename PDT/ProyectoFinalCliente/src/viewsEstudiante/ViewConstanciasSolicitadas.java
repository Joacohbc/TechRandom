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
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;
import views.ViewMedida;

public class ViewConstanciasSolicitadas extends JPanel implements ViewMedida {
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private JComboBox cBoxTipoConstancia;

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
						String ubicacionPDF = Path.of(fc.getSelectedFile().getAbsolutePath(), "constancia.pdf").toString();

						try {
							FileOutputStream pdf = new FileOutputStream(ubicacionPDF);
							pdf.write(archivo);
							pdf.close();

							JOptionPane.showMessageDialog(null, "Constancia descaragada correctamente en " + ubicacionPDF);
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
					
					
				}catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnDescargarConstancia.setBounds(526, 276, 149, 23);
		add(btnDescargarConstancia);

		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "Id", "Evento", "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		
		cargarConstancia(table, estudiante);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(427, 276, 89, 23);
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
				
				try {
					
			
					int row = table.getSelectedRow();
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar un Constancia");
						return;
					}
					
					
					Long idConstancia = (Long) table.getModel().getValueAt(row, 0);
					Constancia cons = BeanIntances.constancia().findById(idConstancia);
					cons.setTipoConstancia((TipoConstancia) cBoxTipoConstancia.getSelectedItem());
					BeanIntances.constancia().update(cons);
					Mensajes.MostrarExito("La constancia se modifico con exito");
					cargarConstancia(table, estudiante);
					
					
					
					
					
					
					
				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());
					
					
				}catch (Exception e2) {
					Mensajes.MostrarError("Error desconocidos: " + e2.getMessage());

				}
				
				
				
				
				
				
				
			}
		});
		btnModificar.setBounds(164, 380, 89, 23);
		add(btnModificar);
		
		
		cBoxTipoConstancia = new JComboBox();
		cBoxTipoConstancia.setBounds(123, 327, 130, 23);
		add(cBoxTipoConstancia);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarConstancia(table, estudiante);
				
			}
		});
		btnNewButton.setBounds(328, 276, 89, 23);
		add(btnNewButton);
		
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
