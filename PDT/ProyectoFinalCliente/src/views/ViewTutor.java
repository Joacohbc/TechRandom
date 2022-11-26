package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Tutor;

import viewsAnalista.ViewAnalista;
import viewsAnalista.ViewGenerarPlantilla;
import viewsAnalista.ViewListadoSolicitudConstancias;
import viewsAnalista.ViewListadoUsuarios;
import viewsAnalista.ViewModEstadoSolConstancia;
import viewsAnalista.ViewPerfil;
import viewsEstudiante.ViewEstudiante;
import viewsITR.ViewAltaITR;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ViewTutor extends JFrame implements ViewMedida {

	private JPanel contentPane;
	
	private ViewAsistencias panelAsistencias;
	private JButton btnRecargar;
	private JButton btnCambiarContrasena;
	private JButton btnPerfil;
	private ViewCambiarContrasenia panelCambiarContrasenia;
	private ViewPerfil panelPersonal;
	private JPanel panelActual;
	
	/**
	 * Create the frame.
	 */
	public ViewTutor(Tutor tutor) {
		setTitle("Tutor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 615);
		panelAsistencias = new ViewAsistencias();
		panelCambiarContrasenia = new ViewCambiarContrasenia(tutor, this);
		panelPersonal = new ViewPerfil(tutor);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAccion = new JMenu("Listado Asistencias");
		mnAccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelAsistencias, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		menuBar.add(mnAccion);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);
		
		JButton btnRecargar = new JButton("");
		btnRecargar.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/refresh32.png")));
		btnRecargar.setToolTipText("Recarga la pestña actual");
		btnRecargar.setOpaque(false);
		btnRecargar.setFocusPainted(false);
		btnRecargar.setContentAreaFilled(false);
		btnRecargar.setBorderPainted(false);
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAsistencias = new ViewAsistencias();
				panelPersonal = new ViewPerfil(tutor);
				
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelAsistencias, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		
		menuBar.add(btnRecargar);
		btnCambiarContrasena = new JButton("");
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
		menuBar.add(btnCambiarContrasena);
		
		btnPerfil = new JButton("");
		btnPerfil.setToolTipText("Ver y modificar infromacion principal");
		btnPerfil.setBorderPainted(false);
		btnPerfil.setContentAreaFilled(false);
		btnPerfil.setFocusPainted(false);
		btnPerfil.setOpaque(false);
		btnPerfil.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/user32.png")));
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
		panelActual.add(panelAsistencias, BorderLayout.CENTER);
		panelActual.repaint();
		panelActual.revalidate();
		

	}
}
