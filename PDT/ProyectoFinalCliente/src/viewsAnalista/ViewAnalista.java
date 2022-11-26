package viewsAnalista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;

import beans.BeanIntances;
import views.ViewAsistencias;
import views.ViewBienvenida;
import views.ViewCambiarContrasenia;
import views.ViewMedida;
import viewsEstudiante.ViewEstudiante;
import viewsITR.ViewITR;


public class ViewAnalista extends JFrame implements ViewMedida {

	private JPanel contentPane;
	private JPanel panelActual;
	private ViewGenerarPlantilla panelGenerarPlantilla;
	private ViewPerfil panelPersonal;
	private ViewBienvenida panelBienvenida;
	private ViewListadoUsuarios panelListadoUsuarios;
	private ViewITR panelITR;
	private ViewListadoSolicitudConstancias panelListadoSolicitudesConstancias;
	private ViewModEstadoSolConstancia panelModEstadoSolConstancia;
	private ViewCambiarContrasenia panelCambiarContrasenia;
	private ViewListadoTipoConstancias panelListadoConstancias;
	private ViewAsistencias panelAsistencias;
	
	public static void main(String[] args) {
		new ViewAnalista(BeanIntances.user().findById(Analista.class, 1l)).setVisible(true);
	}

	public ViewAnalista(Analista ana) {
		panelPersonal = new ViewPerfil(ana);
		panelGenerarPlantilla = new ViewGenerarPlantilla();
		panelBienvenida = new ViewBienvenida();
		panelListadoUsuarios = new ViewListadoUsuarios(ana);
		panelITR = new ViewITR();
		panelListadoSolicitudesConstancias = new ViewListadoSolicitudConstancias(ana);
		panelModEstadoSolConstancia = new ViewModEstadoSolConstancia(ana);
		panelCambiarContrasenia = new ViewCambiarContrasenia(ana, this);
		panelListadoConstancias = new ViewListadoTipoConstancias();
		panelAsistencias = new ViewAsistencias();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		
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
		SolicitudConstanciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelListadoSolicitudesConstancias, BorderLayout.CENTER);	
				panelActual.repaint();
				panelActual.revalidate();
				
			}
		});
		mnConstancias.add(SolicitudConstanciaMenuItem);
		
		JMenuItem ModificarSolicitudConstanciaMenuItem = new JMenuItem("Modificar estado Solicitud Constancia");
		ModificarSolicitudConstanciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelModEstadoSolConstancia, BorderLayout.CENTER);	
				panelActual.repaint();
				panelActual.revalidate();
				
			}
		});
		mnConstancias.add(ModificarSolicitudConstanciaMenuItem);
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Generar Tipo Constancia");
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
		
		JMenuItem mntmListadoTupoConstancias = new JMenuItem("Listado Tipo Constancias");
		mntmListadoTupoConstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelListadoConstancias, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnConstancias.add(mntmListadoTupoConstancias);
		
		
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
	
		JButton btnPerfil = new JButton("");
		btnPerfil.setToolTipText("Ver y modificar infromacion principal");
		btnPerfil.setBorderPainted(false);
		btnPerfil.setContentAreaFilled(false);
		btnPerfil.setFocusPainted(false);
		btnPerfil.setOpaque(false);
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelPersonal, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		
		JButton btnCambiarContrasena = new JButton("");
		btnCambiarContrasena.setToolTipText("Cambiar contraseña");
		btnCambiarContrasena.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/protection32.png")));
		btnCambiarContrasena.setBorderPainted(false);
		btnCambiarContrasena.setContentAreaFilled(false);
		btnCambiarContrasena.setFocusPainted(false);
		btnCambiarContrasena.setOpaque(false);
		btnCambiarContrasena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelCambiarContrasenia, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		
		JButton btnRecargar = new JButton("");
		btnRecargar.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/refresh32.png")));
		btnRecargar.setToolTipText("Recarga la pestña actual");
		btnRecargar.setOpaque(false);
		btnRecargar.setFocusPainted(false);
		btnRecargar.setContentAreaFilled(false);
		btnRecargar.setBorderPainted(false);
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPersonal = new ViewPerfil(ana);
				panelGenerarPlantilla = new ViewGenerarPlantilla();
				panelBienvenida = new ViewBienvenida();
				panelListadoUsuarios = new ViewListadoUsuarios(ana);
				panelITR = new ViewITR();
				panelListadoSolicitudesConstancias = new ViewListadoSolicitudConstancias(ana);
				panelModEstadoSolConstancia = new ViewModEstadoSolConstancia(ana);
				
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelBienvenida, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		
		menuBar.add(btnRecargar);
		
		menuBar.add(btnCambiarContrasena);
		btnPerfil.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/user32.png")));
		menuBar.add(btnPerfil);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewEstudiante.class.getResource("/images/logo_utec.png")));
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