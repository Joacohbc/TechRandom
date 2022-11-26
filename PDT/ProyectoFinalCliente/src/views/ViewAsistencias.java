package views;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Analista;
import com.entities.Asistencia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Usuario;
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
import components.VTextBox;
import swingutils.Mensajes;
import validation.Formatos;
import validation.ValidacionesUsuario;
import javax.swing.JComboBox;

public class ViewAsistencias extends JPanel implements ViewMedida{
	private JTable table;
	private VTextBox txtDocumento;
	private Estudiante estudiante;
	private final String columns[] = { "Id", "Titulo", "Inicio", "Fin", "Modalidad", "Lugar", "Calif.", "Asistencia" };;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 500, 500);
		frame.setContentPane(new ViewAsistencias());
		frame.setVisible(true);
		
	}
	
	public ViewAsistencias() {
		setLayout(null);
		setBounds(0, 0, 700, 456);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 115, 368, 202);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(46, 45, 368, 27);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtDocumento.isContentValid()) {
					Mensajes.MostrarError(txtDocumento.getErrorMessage());
					return;
				}
				
				try {
					estudiante = BeanIntances.user().findByDocumento(Estudiante.class, txtDocumento.getText());
					if(estudiante == null) {
						Mensajes.MostrarError("No existe un estudiante con el Documento: " + txtDocumento.getText());
						return;
					}
					
					cargarTabla(estudiante);
				} catch (Exception ex) {
					Mensajes.MostrarError("Error desconocido: " +ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		add(btnBuscar);
		
		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(46, 14, 83, 17);
		add(lblNewLabel);
		
		txtDocumento = new VTextBox();
		txtDocumento.setBounds(136, 12, 278, 21);
		txtDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoNoUruguayo(text));
		add(txtDocumento);
		
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
		btnImprimir.setBounds(46, 323, 368, 27);
		add(btnImprimir);
		
		JComboBox<Estudiante> cmbEstudiante = new JComboBox<Estudiante>();
		cmbEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estudiante est = (Estudiante) cmbEstudiante.getSelectedItem();
				txtDocumento.setText(est.getDocumento());
				btnBuscar.doClick();
			}
		});
		cmbEstudiante.setBounds(46, 80, 368, 26);
		add(cmbEstudiante);
		List<Estudiante> estudiante = BeanIntances.user().findAll(Estudiante.class);
		for (Estudiante e : estudiante) {
			cmbEstudiante.addItem(e);
		}
		
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
