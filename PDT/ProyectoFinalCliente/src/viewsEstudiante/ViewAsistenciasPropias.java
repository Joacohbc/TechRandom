package viewsEstudiante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Asistencia;
import com.entities.Estudiante;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import beans.BeanIntances;
import swingutils.Mensajes;
import validation.Formatos;
import views.ViewMedida;

public class ViewAsistenciasPropias extends JPanel implements ViewMedida{
	
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private Estudiante estudiante;
	private final String columns[] = { "Id", "Titulo", "Inicio", "Fin", "Modalidad", "Lugar", "Calif.", "Asistencia" };;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		frame.setContentPane(new ViewAsistenciasPropias(BeanIntances.user().findById(Estudiante.class, 38l)));
		frame.setVisible(true);
	}
	
	public ViewAsistenciasPropias(Estudiante est) {
		this.estudiante = est;
		setLayout(null);
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 12, 624, 521);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnRecargar = new JButton("Recargar");
		btnRecargar.setBounds(36, 543, 297, 27);
		btnRecargar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				try {
					estudiante = BeanIntances.user().findById(Estudiante.class, estudiante.getIdUsuario());
					if(estudiante == null) {
						Mensajes.MostrarError("No existe un estudiante con el ID: " + estudiante.getIdUsuario());
						return;
					}
					
					cargarTabla(estudiante);
				} catch (Exception ex) {
					Mensajes.MostrarError("Error desconocido: " +ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		add(btnRecargar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(estudiante == null) {
						Mensajes.MostrarError("Debe seleccionar un usuario para poder imprimir la lista");
						return;
					}
					
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					
					if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						String path = Path.of(fc.getSelectedFile().getAbsolutePath(), "InformeEstudiante"+estudiante.getDocumento()+".pdf").toString();
						
						Document pdfDoc = new Document();
						FileOutputStream pdf = new FileOutputStream(path);
						
						PdfWriter writer = PdfWriter.getInstance(pdfDoc, pdf);
						pdfDoc.open();
						
						Font titleFont = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
						Paragraph titulo = new Paragraph();
						titulo.setFont(titleFont);
						titulo.add("INFORME DE LOS EVENTOS POR ESTUDIANTE");
						titulo.setAlignment(Element.ALIGN_CENTER);
						titulo.setFont(titleFont);
						pdfDoc.add(titulo);
						
						pdfDoc.add(Chunk.NEWLINE);
						pdfDoc.add(Chunk.NEWLINE);
						pdfDoc.add(Chunk.NEWLINE);
						
						Paragraph info = new Paragraph();
						info.add(estudiante.getNombres() + " " + estudiante.getApellidos() + " - " + estudiante.getDocumento());
						info.setAlignment(Element.ALIGN_CENTER);
						pdfDoc.add(info);
						
						pdfDoc.add(Chunk.NEWLINE);

						PdfPTable pdfTable = new PdfPTable(columns.length);
						pdfTable.setWidthPercentage(100);
						pdfTable.setSpacingBefore(0f);
						pdfTable.setSpacingAfter(0f);
				        
						for (String s : columns) {
							Font smallfont = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
							PdfPCell cell = new PdfPCell(new Phrase(s, smallfont));
							cell.setBorder(Rectangle.BOX);
							cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cell.setHorizontalAlignment(Element.ALIGN_CENTER);
							pdfTable.addCell(cell);
						}
						
						for(int i = 0; i < table.getRowCount(); i++) {
							for(int j = 0; j < columns.length; j++) {
								pdfTable.addCell(table.getValueAt(i, j).toString());
							}
						}
						
						pdfDoc.add(pdfTable);
						pdfDoc.close();
						pdf.close();
					}

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(350, 543, 310, 27);
		add(btnImprimir);
		btnRecargar.doClick();
	}
	
	private void cargarTabla(Estudiante estudiante) {
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		List<Asistencia> asits = BeanIntances.estudiante().getAsistencias(estudiante.getIdUsuario());
		for (Asistencia a : asits) {
			Object[] datos = { 
					a.getEvento().getIdEvento(),
					a.getEvento().getTitulo(),
					Formatos.ToFormatedString(a.getEvento().getFechaInicio()),
					Formatos.ToFormatedString(a.getEvento().getFechaFin()),
					a.getEvento().getModalidad().toString(),
					a.getEvento().getLocalizacion(),
					a.getCalificacion(),
					a.getAsistencia().toString()
			};
			modeloJTable.addRow(datos);
		}
		table.setModel(modeloJTable);
	}
}
