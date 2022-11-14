package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ViewPDF extends JFrame {

	private JPanel contentPane;
	JFileChooser fc;

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
	 */
	public ViewPDF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("CREAR");
		btnNewButton.setBounds(175, 103, 105, 27);
		contentPane.add(btnNewButton);

		fc = new JFileChooser();
		getContentPane().add(fc);
		fc.setVisible(true);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destino = "/home/william/Documents/nuevo.pdf";
				File file = new File(destino);

				try {
					FileOutputStream file2 = new FileOutputStream(destino);
					Document documento = new Document();
					PdfWriter writer = PdfWriter.getInstance(documento, file2);
					Paragraph titulo = new Paragraph("Plantilla Personalizada");
					documento.open();
					titulo.setAlignment(1);
					Image logo = null;
					
					logo =  Image.getInstance("/home/william/Documents/GitHub/TechRandom/PDT/ProyectoFinalCliente/src/images/logo utec (2).png");//carga imagen
					logo.scaleAbsolute(150, 100);//cambia tamaño
					logo.setAbsolutePosition(415, 750);//coloca imagen en la posicion
	                
	                Image firma = null;
	                firma =  Image.getInstance("/home/william/Documents/GitHub/TechRandom/PDT/ProyectoFinalCliente/src/images/logo utec (2).png");//carga imagen
	                firma.scaleAbsolute(150, 100);//cambia tamaño
	                firma.setAbsolutePosition(0, 0);//coloca imagen en la posicion
					
	                documento.add(logo);//agrega la imagen al documento
	                documento.add(firma);//agrega la imagen al documento
	                
	                documento.add(titulo);
	                
	                documento.add(new Paragraph("Nombre: el poronga" ));
	                documento.add(new Paragraph("Apellidos: del barrio"));
	                
	                documento.add(Chunk.NEWLINE);
	                
	                //EL SECRETARIO DE CARRERA CARGA EL TEXTO DEL TEMPLATE USANDO CAMPOS DETERMINADOS POR NOSOTROS {NOMBRE} ETC
	                //ESOS CAMPOS LOS REEMPLAZAMOS DINAMICAMENTE
	                String textoAreemplazar = "It is a long established {nombre} that a reader will be distracted by the readable content of "
	                        + "a page when looking at its layout. The point of using {apellido} Ipsum is that it has a more-or-less normal distribution"
	                        + " of letters, as opposed to using 'Content here, content here', making it look like readable English. "
	                        + "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for "
	                        + "'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, "
	                        + "sometimes on purpose (injected humour and the like).";
	                
	                //Procesar el texto y reepmplazar
	                String txtoProcesado = "";
	                for(int i = 0; i < textoAreemplazar.split(" ").length; i++) {
	                	String pal = textoAreemplazar.split(" ")[i];
	                	if(pal.equalsIgnoreCase("{nombre}")) {
							pal=" EL PUTO NOMBRE";
						}
						if(pal.equalsIgnoreCase("{apellido}")) {
							pal=" EL PUTO APELLIDO";
						}
						txtoProcesado += pal + " ";
	                }

	                Paragraph texto = new Paragraph(txtoProcesado);
	                texto.setAlignment(Element.ALIGN_JUSTIFIED);
	                documento.add(texto);
	                
	                documento.add(Chunk.NEWLINE);
	                
	                
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(Chunk.NEWLINE);
	                documento.add(new Paragraph("Fecha: "));
	                Paragraph textoFirma = new Paragraph("firma: La reconcha de la lora");
	                documento.add(textoFirma);

	                documento.close();
	                System.out.println("Archivo creado correctamente!");
					
					

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
			}
		});

	}
}
