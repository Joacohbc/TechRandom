package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.entities.Constancia;
import com.entities.TipoConstancia;
import com.exceptions.InvalidEntityException;
import com.exceptions.ServiceException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import beans.BeanIntances;
import components.InfoButton;
import swingutils.Mensajes;
import validation.Formatos;
import validation.ValidacionesTipoConstancia;
import validation.ValidationObject;
import views.ViewMedida;

public class ViewGenerarPlantilla extends JPanel implements ViewMedida {

	private JTextArea txtAParrafo1;
	private JTextArea txtAParrafo2;
	private JSpinner spinner;
	private String ubicacionPlantilla = null;
	private JTextField txtTitulo;
	private JTextField txtTipoContancia;

	public ViewGenerarPlantilla() {
		setBounds(100, 100, ANCHO_VIEW, LARGO_VIEW);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JButton btnCargarPlantilla = new JButton("Cargar Modelo Plantilla");
		btnCargarPlantilla.setBounds(129, 504, 491, 27);
		add(btnCargarPlantilla);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(129, 51, 491, 21);
		add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblTtulo = new JLabel("Título");
		lblTtulo.setBounds(25, 53, 60, 17);
		add(lblTtulo);

		JLabel lblPrrafo = new JLabel("Párrafo #1");
		lblPrrafo.setBounds(25, 84, 86, 17);
		add(lblPrrafo);

		JLabel lblPrrafo_1 = new JLabel("Párrafo #2");
		lblPrrafo_1.setBounds(25, 282, 86, 17);
		add(lblPrrafo_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(129, 83, 491, 170);
		add(scrollPane);

		txtAParrafo1 = new JTextArea();
		txtAParrafo1.setLineWrap(true);
		scrollPane.setViewportView(txtAParrafo1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(129, 277, 491, 183);
		add(scrollPane_1);

		txtAParrafo2 = new JTextArea();
		txtAParrafo2.setLineWrap(true);
		scrollPane_1.setViewportView(txtAParrafo2);

		JLabel lblPlantilla = new JLabel("Plantilla");
		lblPlantilla.setBounds(25, 509, 60, 17);
		add(lblPlantilla);

		JButton btnPrevisualizar = new JButton("Previsualizar Plantilla PDF");
		btnPrevisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ubicacionPlantilla == null) {
					Mensajes.MostrarError("Seleccione una plantilla antes de realzar la pre-visualización");
					return;
				}

				if (txtAParrafo1.getText().isBlank()) {
					Mensajes.MostrarError("Por lo menos el primer párrafo debe tener contenido");
					return;
				}

				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					String ubicacionPDF = Path.of(fc.getSelectedFile().getAbsolutePath(), "constancia-plantilla.pdf").toString();

					try {
						FileOutputStream pdf = new FileOutputStream(ubicacionPDF);
						pdf.write(obtenerPlantilla());
						pdf.close();

						JOptionPane.showMessageDialog(null, "Plantilla creada correctamente en " + ubicacionPDF);
					} catch (FileNotFoundException e1) {
						Mensajes.MostrarError("La ruta: " + ubicacionPDF + " no es una ruta valida");
						e1.printStackTrace();
					} catch (IOException e1) {
						Mensajes.MostrarError("Ocurrió un error al guardar la plantilla: " + e1.getMessage());
						e1.printStackTrace();
					}

				}
			}
		});
		btnPrevisualizar.setBounds(129, 543, 491, 27);
		add(btnPrevisualizar);

		JLabel lblEspaciado = new JLabel("Espaciado");
		lblEspaciado.setBounds(25, 467, 60, 17);
		add(lblEspaciado);

		// SE UTILIZA UN SPINNER PARA CONTROLAR LOS ESPACIOS DESDE EL ÚLTIMO PÁRRAFO AL
		// FINAL DEL DOCUMENTO
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10010, 1));
		spinner.setBounds(129, 470, 60, 22);
		add(spinner);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (ubicacionPlantilla == null) {
					Mensajes.MostrarError("Seleccione una plantilla antes de realizar la pre-visualización");
					return;
				}

				if (txtAParrafo1.getText().isBlank()) {
					Mensajes.MostrarError("Por lo menos el primer párrafo debe tener contenido");
					return;
				}

				byte[] plantilla = obtenerPlantilla();
				if (plantilla == null) {
					return;
				}

				try {
					TipoConstancia tp = new TipoConstancia();
					tp.setTipo(txtTipoContancia.getText());
					tp.setPlantilla(plantilla);
					tp.setEstado(true);

					ValidationObject valid = ValidacionesTipoConstancia.validarTipoContancia(tp);
					if (!valid.isValid()) {
						Mensajes.MostrarError(valid.getErrorMessage());
						return;
					}

					BeanIntances.tipoConstancia().insert(tp);
					
					Mensajes.MostrarExito("Se dio de alta el Tipo de Constancias exitosamente");
				} catch (Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
					ex.printStackTrace();
				}

			}
		});
		btnGuardar.setBounds(129, 582, 491, 27);
		add(btnGuardar);

		txtTipoContancia = new JTextField();
		txtTipoContancia.setColumns(10);
		txtTipoContancia.setBounds(129, 18, 491, 21);
		add(txtTipoContancia);

		JLabel lblTipoConstancia = new JLabel("Tipo Constancia");
		lblTipoConstancia.setBounds(25, 20, 102, 17);
		add(lblTipoConstancia);

		InfoButton nfbtnespaciadoEsLa = new InfoButton();
		nfbtnespaciadoEsLa.setText(
				"\"Espaciado\" es la cantidad de lineas de separación entre la imagen del modelo y los párrafos");
		nfbtnespaciadoEsLa.setBounds(194, 471, 16, 16);
		add(nfbtnespaciadoEsLa);

		InfoButton infoParrafo1 = new InfoButton();
		infoParrafo1.setText(
				"Para parametrizar la plantilla puede utilizar las siguientes expresiones\n" + "Datos del Estudiante:\n"
						+ "- Nombre/s del Estudiante: &nombre&\n" + "- Apellidos del Estudiante: &apellido&\n"
						+ "- Documento del estudiante: &documento&\n" + "- Generacion del Estudiante: &generacion&\n"
						+ "\n" + "Datos del Evento:\n" + "- Nombre del Evento: &evento&\n"
						+ "- Fecha de Inicio del Evento: &fechainicio&\n" + "- Fecha de Fin del Evento: &fechafin&\n"
						+ "- Modalidad del Evento: &modalidad&\n" + "- Localización del Evento: &lugar&\n");
		infoParrafo1.setBounds(625, 85, 16, 16);
		add(infoParrafo1);

		InfoButton infoParrafo2 = new InfoButton();
		infoParrafo2.setText(
				"Para parametrizar la plantilla puede utilizar las siguientes expresiones\n" + "Datos del Estudiante:\n"
						+ "- Nombre/s del Estudiante: &nombre&\n" + "- Apellidos del Estudiante: &apellido&\n"
						+ "- Documento del estudiante: &documento&\n" + "- Generacion del Estudiante: &generacion&\n"
						+ "\n" + "Datos del Evento:\n" + "- Nombre del Evento: &evento&\n"
						+ "- Fecha de Inicio del Evento: &fechainicio&\n" + "- Fecha de Fin del Evento: &fechafin&\n"
						+ "- Modalidad del Evento: &modalidad&\n" + "- Localización del Evento: &lugar&\n");
		infoParrafo2.setBounds(630, 284, 16, 16);
		add(infoParrafo2);

		btnCargarPlantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FileNameExtensionFilter("Image Files", ImageIO.getReaderFileSuffixes()));

				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					ubicacionPlantilla = fc.getSelectedFile().getAbsolutePath();
				}
			}
		});

	}

	private byte[] obtenerPlantilla() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			// Creo el documento
			Document documento = new Document();

			// Incio el Writter (Que se encarga de crear el PDF)
			PdfWriter writer = PdfWriter.getInstance(documento, baos);

			documento.open();

			// Agrego el Titulo
			Paragraph titulo = new Paragraph(txtTitulo.getText());
			titulo.setAlignment(1);
			documento.add(titulo);

			// Agrego el espaciado entr el Titulo y el contenido
			documento.add(Chunk.NEWLINE);
			documento.add(Chunk.NEWLINE);
			documento.add(Chunk.NEWLINE);

			// Creo la imagen en base a la plantilla
			Image image = Image.getInstance(ubicacionPlantilla);
			image.scaleAbsoluteHeight(PageSize.A4.getHeight());
			image.scaleAbsoluteWidth(PageSize.A4.getWidth());
			image.setAbsolutePosition(0, 0);

			// Agrego la Imagen al documento
			writer.getDirectContentUnder().addImage(image);

			// Genero el primer parrafo
			Paragraph parrafo1 = new Paragraph(txtAParrafo1.getText());
			parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
			documento.add(parrafo1);

			// Agrego la el espaciado entre el primer parrafo y el segundo
			for (int i = 0; i < (Integer) spinner.getValue(); i++) {
				documento.add(Chunk.NEWLINE);
			}

			// Genero el segundo parrafo
			Paragraph parrafo2 = new Paragraph(txtAParrafo2.getText());
			parrafo1.setAlignment(Element.ALIGN_JUSTIFIED);
			documento.add(parrafo2);

			// Genero el archivo
			documento.close();

			return baos.toByteArray();

		} catch (IOException e1) {
			Mensajes.MostrarError(e1.getMessage());
			e1.printStackTrace();
		} catch (DocumentException e1) {
			Mensajes.MostrarError(e1.getMessage());
			e1.printStackTrace();
		}

		return null;
	}
}
