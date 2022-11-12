package viewsAnalista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.pdf.PRStream;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfObject;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfStream;

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
		
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.setBounds(175, 103, 105, 27);
		contentPane.add(btnNewButton);
		
		fc = new JFileChooser();
		getContentPane().add(fc);
		fc.setVisible(true);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//showOpenDialog abre la ventana para seleccionar el archivo
				int retorno = fc.showOpenDialog(null);
				
				//JFileChooser.APPROVE_OPTION retorna un valor según si se selecciona ok o cancelar al seleccionar el archivo
	            if (retorno == JFileChooser.APPROVE_OPTION) {
	                File archivo = fc.getSelectedFile();
	                
	                JOptionPane.showMessageDialog(null, "se sube el archivo" + archivo.getName());
	                String ubicacionPDF = archivo.getAbsolutePath();
	                String destino = "/home/william/Documents/nuevo.pdf";
	                try {	                	
	                	
	                	PdfReader reader = new PdfReader(ubicacionPDF);
	                	 PdfDictionary dict = reader.getPageN(1);
	                     PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
	                     if (object instanceof PdfStream) {
	                         PRStream stream = (PRStream)object;
	                         byte[] data = PdfReader.getStreamBytes(stream);
	                         System.out.println("ENTRA");
	                         String a = new String(data).replace("nombre", "HELLO WORLD");
	                         System.out.println(a.toString());
	                         stream.setData(new String(data).replace("nombre", "HELLO WORLD").getBytes());
	                     }
	                     PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destino));
	                     stamper.close();
	                     reader.close();
	                	
	                	
	                	
	              
	        		} catch (Exception ex) {
	        		    ex.printStackTrace();
	        		}
	                
	                
	                
	                
	                
	            } else {
	            	JOptionPane.showMessageDialog(null, "se cancela la subida del archivo");
	            } 
	        
			}
		});

}
}
