package viewsAnalista;

import java.awt.BorderLayout;
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

import views.ViewBienvenida;
import viewsEstudiante.ViewEstudiante;
import viewsITR.ViewAltaITR;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;


public class ViewAnalista extends JFrame {

	private JPanel contentPane;
	private JPanel panelActual;
	private final ViewGenerarPlantilla panelGenerarPlantilla;
	private final ViewPerfil panelPersonal;
	private final ViewBienvenida panelBienvenida;
	private final ViewListadoUsuarios panelListadoUsuarios;
	private final ViewAltaITR panelITR;
	private final ViewListadoSolicitudConstancias panelSolicitudConstancias;

	/**
	 * Create the frame.
	 */

	public ViewAnalista(Analista ana) {
		panelPersonal = new ViewPerfil(ana);
		panelGenerarPlantilla = new ViewGenerarPlantilla();
		panelBienvenida = new ViewBienvenida();
		panelListadoUsuarios = new ViewListadoUsuarios(ana);
		panelITR = new ViewAltaITR();
		panelSolicitudConstancias = new ViewListadoSolicitudConstancias();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 784);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listado y mantenimiento");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelListadoUsuarios, BorderLayout.CENTER);	
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnUsuarios.add(mntmNewMenuItem_1);
		
		
		
		//MenuBar Constancias
		JMenu mnConstancias = new JMenu("Constancias");
		menuBar.add(mnConstancias);
		
		
		JMenuItem SolicitudConstanciaMenuItem = new JMenuItem("Listado Constancias");
		/*SolicitudConstanciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelConstancia, BorderLayout.CENTER);	
				panelActual.repaint();
				panelActual.revalidate();
				
			}
		});*/
		mnConstancias.add(SolicitudConstanciaMenuItem);
		
		
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Generar Plantilla PDF");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelGenerarPlantilla, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnConstancias.add(mntmNewMenuItem);
		
		JMenuItem mntmListadoSolicitudesConstancia = new JMenuItem("Listado Solicitudes");
		mntmListadoSolicitudesConstancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelSolicitudConstancias, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnConstancias.add(mntmListadoSolicitudesConstancia);
		
		JMenu mnITR = new JMenu("Personalizar Datos ITR");
		menuBar.add(mnITR);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Mantenimiento ITR");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelITR, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnITR.add(mntmNewMenuItem_3);
		
		
		
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
		
		JButton btnNewButton = new JButton("Perfil");
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelPersonal, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		btnNewButton.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/usuario (3).png")));
		menuBar.add(btnNewButton);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewEstudiante.class.getResource("/images/logo utec (2).png")));
		lblNewLabel.setBounds(576, 581, 180, 161);
		contentPane.add(lblNewLabel);
		
		
		
		
		panelActual = new JPanel();
		panelActual.setBounds(0, 0, 679, 624);
		contentPane.add(panelActual);
		panelActual.setLayout(new BorderLayout(0, 0));
		
		//Set de Panel de Bienvenida
		panelActual.removeAll();
		panelActual.repaint();
		panelActual.revalidate();
		panelActual.add(panelBienvenida, BorderLayout.CENTER);
		panelActual.repaint();
		panelActual.revalidate();
		
	}
}