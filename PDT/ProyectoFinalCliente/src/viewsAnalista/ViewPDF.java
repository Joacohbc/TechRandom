package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import io.undertow.server.handlers.resource.FileResourceManager;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ViewPDF extends JFrame {

	private JPanel contentPane;
	JFileChooser fc;
	private JTextArea txtAParrafo1;
	private JTextArea txtAParrafo2;
	private JSpinner spinner;
	private String ubicacionPlantilla = null;
	private String ubicacionPDF = null;
	private JTextField txtTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPDF frame = new ViewPDF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *ESTA VIEW TIENE QUE RECIBIR UN
	 *EVENTO
	 *ANALISTA
	 *
	 */
	public ViewPDF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCargarPlantilla = new JButton("Cargar Modelo Plantilla");
		btnCargarPlantilla.setBounds(129, 302, 360, 27);
		contentPane.add(btnCargarPlantilla);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(129, 29, 360, 21);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(25, 31, 60, 17);
		contentPane.add(lblTtulo);

		JLabel lblPrrafo = new JLabel("Párrafo #1");
		lblPrrafo.setBounds(25, 64, 86, 17);
		contentPane.add(lblPrrafo);

		JLabel lblPrrafo_1 = new JLabel("Párrafo #2");
		lblPrrafo_1.setBounds(25, 165, 86, 17);
		contentPane.add(lblPrrafo_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText(
				"En el texto que ingrese puede utilizar las siguientes variables  {nombre_estudiante},{documento}, {carrera},{evento},{semestre},{curso}");
		scrollPane.setBounds(129, 63, 360, 90);
		contentPane.add(scrollPane);

		txtAParrafo1 = new JTextArea();
		txtAParrafo1.setLineWrap(true);
		scrollPane.setViewportView(txtAParrafo1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(129, 165, 360, 82);
		contentPane.add(scrollPane_1);

		txtAParrafo2 = new JTextArea();
		txtAParrafo2.setLineWrap(true);
		scrollPane_1.setViewportView(txtAParrafo2);

		JLabel lblPlantilla = new JLabel("Plantilla");
		lblPlantilla.setBounds(25, 307, 60, 17);
		contentPane.add(lblPlantilla);

		JButton btnGuardar = new JButton("Previsualizar Plantilla PDF");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int retorno_salida = fc.showSaveDialog(null);
					if (retorno_salida == JFileChooser.APPROVE_OPTION) {
						ubicacionPDF = fc.getCurrentDirectory().getAbsolutePath() + "/plantilla.pdf";
						
						FileOutputStream file2 = new FileOutputStream(ubicacionPDF);

						// creo el documento
						Document documento = new Document();

						//
						PdfWriter writer = PdfWriter.getInstance(documento, file2);
						Paragraph titulo = new Paragraph(txtTitulo.getText());
						documento.open();
						titulo.setAlignment(1);
						documento.add(titulo);
						documento.add(Chunk.NEWLINE);
						documento.add(Chunk.NEWLINE);
						documento.add(Chunk.NEWLINE);

						PdfContentByte canvas = writer.getDirectContentUnder();
						Image image = Image.getInstance(ubicacionPlantilla);
						image.scaleAbsoluteHeight(PageSize.A4.getHeight());
						image.scaleAbsoluteWidth(PageSize.A4.getWidth());
						image.setAbsolutePosition(0, 0);
						canvas.addImage(image);

						Paragraph parrafo1 = new Paragraph(txtAParrafo1.getText());
						parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
						documento.add(parrafo1);
						
						documento.add(Chunk.NEWLINE);

						Paragraph parrafo2 = new Paragraph(txtAParrafo2.getText());
						parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
						documento.add(parrafo2);

						for (int i = 0; i < (Integer) spinner.getValue(); i++) {
							documento.add(Chunk.NEWLINE);
						}

						documento.close();
						JOptionPane.showMessageDialog(null, "Plantilla creada correctamente en " + ubicacionPDF);

					} else {
						JOptionPane.showMessageDialog(null, "Acción cancelada");
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				/*
				 * try {
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * documento.add(Chunk.NEWLINE); documento.add(Chunk.NEWLINE);
				 * documento.add(Chunk.NEWLINE); String textoAreemplazar =
				 * "Se deja constancia que {nombre} con Cédula de Identidad N° {documento}" +
				 * " estudiante de la carrera {carrera_est} ,asistió a {evento}  correspondiente al {semestre} semestre."
				 * + " En la misma se realizó la evaluación final / examen del curso de {curso}"
				 * + " en el día {fecha} en el horario de 9 a 17 horas.";
				 * 
				 * // Procesar el texto y reepmplazar String txtoProcesado = ""; for (int i = 0;
				 * i < textoAreemplazar.split(" ").length; i++) { String pal =
				 * textoAreemplazar.split(" ")[i]; if (pal.equalsIgnoreCase("{nombre}")) { pal =
				 * " Juan Perez"; } if (pal.equalsIgnoreCase("{documento}")) { pal =
				 * " 348695959"; } if (pal.equalsIgnoreCase("{carrera_est}")) { pal =
				 * " Licenciatura en Tecnologías de la Información"; } if
				 * (pal.equalsIgnoreCase("{evento}")) { pal = " Jornada presencial obligatoria";
				 * } if (pal.equalsIgnoreCase("{semestre}")) { pal = " segundo"; } if
				 * (pal.equalsIgnoreCase("{curso}")) { pal = " Bases de datos empresariales"; }
				 * if (pal.equalsIgnoreCase("{fecha}")) { pal = " 18/11/2022"; } txtoProcesado
				 * += pal + " "; }
				 * 
				 * Paragraph texto = new Paragraph(txtoProcesado);
				 * 
				 * 
				 * } catch (FileNotFoundException e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); } catch (IOException e1) { // TODO Auto-generated catch
				 * block e1.printStackTrace(); } catch (DocumentException e1) { // TODO
				 * Auto-generated catch block e1.printStackTrace(); }
				 * 
				 */
			}
		});
		btnGuardar.setBounds(129, 341, 360, 27);
		contentPane.add(btnGuardar);

		JLabel lblEspaciado = new JLabel("Espaciado");
		lblEspaciado.setBounds(25, 265, 60, 17);
		contentPane.add(lblEspaciado);
		
		//SE UTILIZA UN SPINNER PARA CONTROLAR LOS ESPACIOS DESDE EL ÚLTIMO PÁRRAFO AL FINAL DEL DOCUMENTO
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10010, 1));
		spinner.setBounds(129, 268, 60, 22);
		contentPane.add(spinner);

		
		fc = new JFileChooser();
		fc.setLocation(0, 29);
		getContentPane().add(fc);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBounds(129, 380, 360, 27);
		contentPane.add(btnNewButton);
		fc.setVisible(true);

		btnCargarPlantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int retorno_plantilla = fc.showOpenDialog(null);

				if (retorno_plantilla == JFileChooser.APPROVE_OPTION) {
					File plantilla = fc.getSelectedFile();
					ubicacionPlantilla = plantilla.getAbsolutePath();
				} else {
					JOptionPane.showMessageDialog(null, "Acción cancelada");
				}
			}
		});

	}
}
