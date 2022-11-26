package viewsAnalista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.TipoConstancia;
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;
import views.ViewMedida;

public class ViewListadoTipoConstancias extends JPanel implements ViewMedida{
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private List<TipoConstancia> tipos;

	/**
	 * Create the panel.
	 */
	public ViewListadoTipoConstancias() {
		setLayout(null);
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 57, 538, 189);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cargarTipos(table);
		
		JLabel lblTiposDeConstancia = new JLabel("Tipos de Constancia");
		lblTiposDeConstancia.setBounds(12, 31, 152, 17);
		add(lblTiposDeConstancia);
		
		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if (row == -1) {
					Mensajes.MostrarError("Debe seleccionar un Constancia");
					return;						
				}	
				Long id = Long.valueOf(table.getModel().getValueAt(row, 0).toString());
				BeanIntances.tipoConstancia().reactivar(id);
				cargarTipos(table);
				Mensajes.MostrarExito("El tipo constancia se modifico con exito");
			}
		});
		btnActivar.setBounds(12, 275, 105, 27);
		add(btnActivar);
		
		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) {
					Mensajes.MostrarError("Debe seleccionar un Constancia");
					return;						
				}	
				Long id = Long.valueOf(table.getModel().getValueAt(row, 0).toString());
				BeanIntances.tipoConstancia().eliminar(id);
				cargarTipos(table);
				Mensajes.MostrarExito("El tipo constancia se modifico con exito");
			}
		});
		btnDesactivar.setBounds(139, 275, 105, 27);
		add(btnDesactivar);
		
		JButton btnDescargar = new JButton("Descargar");
		btnDescargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					int row = table.getSelectedRow();
					Long id = Long.valueOf(table.getModel().getValueAt(row, 0).toString());
					if (row == -1) {
						Mensajes.MostrarError("Debe seleccionar un Constancia");
						return;						
					}										
					
					byte[] archivo = BeanIntances.tipoConstancia().descargarPlantilla(id);
					if (archivo == null) {
						Mensajes.MostrarError("El archivo esta vacio");
						return;
					}
					
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

					if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String ubicacionPDF = Path.of(fc.getSelectedFile().getAbsolutePath(), "plantilla.pdf").toString();

						try {
							FileOutputStream pdf = new FileOutputStream(ubicacionPDF);
							pdf.write(archivo);
							pdf.close();

							JOptionPane.showMessageDialog(null, "Plantilla descaragada correctamente en " + ubicacionPDF);
						} catch (FileNotFoundException e1) {
							Mensajes.MostrarError("La ruta: " + ubicacionPDF + " no es una ruta valida");
							e1.printStackTrace();
						} catch (IOException e1) {
							Mensajes.MostrarError("Ocurrio un error al guardar la plantilla: " + e1.getMessage());
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
		btnDescargar.setBounds(256, 275, 105, 27);
		add(btnDescargar);

	}
	
	public void cargarTipos(JTable table) {
		tipos = BeanIntances.tipoConstancia().findAll();
		String columns[] = { "Id", "Tipo", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		if (tipos == null) {
			return;
		}
		for (TipoConstancia unT : tipos) {
			Long id = unT.getIdTipoConstancia();
			String tipoConstancia = unT.getTipo();
			String estado = unT.getEstado().toString();
			Object[] datos = {id, tipoConstancia, estado};
			modeloJTable.addRow(datos);
			

		}
		table.setModel(modeloJTable);
	}
}
