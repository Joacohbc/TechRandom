package viewsAnalista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;

import views.ViewDatosPersonales;
import viewsEstudiante.ViewsContancia;

public class ViewsAnalista extends JFrame {

	private JPanel contentPane;
	private JPanel panelActual;
//	private final ViewsContancia panelConstancia = new ViewsContancia();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ViewsAnalista frame = new ViewsAnalista();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the frame.
	 */
//	public ViewsAnalista() {
//		
//	}
//	
	
	public ViewsAnalista(Analista ana) {
		setTitle("Analista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 784);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Funcionalidades");
		menuBar.add(mnNewMenu);
		
		JMenuItem DatosPersonalesMenuItem = new JMenuItem("Datos Personales");
		DatosPersonalesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewDatosPersonales panelPersonal = new ViewDatosPersonales(ana);
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelPersonal, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
				
				
			}
		});
		mnNewMenu.add(DatosPersonalesMenuItem);
		

		
		
		
//		JMenuItem SolicitudConstanciaMenuItem = new JMenuItem("Solicitud Constancia");
//		SolicitudConstanciaMenuItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panelActual.removeAll();
//				panelActual.repaint();
//				panelActual.revalidate();
//				panelActual.add(panelConstancia, BorderLayout.CENTER);	
//				panelActual.repaint();
//				panelActual.revalidate();
//				
//			}
//		});
//		mnNewMenu.add(SolicitudConstanciaMenuItem);
		
		
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registro de Constancias");
		mnNewMenu.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewsAnalista.class.getResource("/images/logo utec (2).png")));
		lblNewLabel.setBounds(576, 581, 180, 161);
		contentPane.add(lblNewLabel);
		
		
		
		
		panelActual = new JPanel();
		panelActual.setBounds(0, 0, 679, 624);
		contentPane.add(panelActual);
		panelActual.setLayout(new BorderLayout(0, 0));
	}
}
