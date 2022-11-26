package viewsEstudiante;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Estudiante;

import beans.BeanIntances;
import views.ViewAsistencias;
import views.ViewBienvenida;
import views.ViewCambiarContrasenia;
import views.ViewMedida;
import viewsAnalista.ViewAnalista;
import viewsAnalista.ViewPerfil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

public class ViewEstudiante extends JFrame implements ViewMedida {

	private JPanel contentPane;
	private JPanel panelActual;
	private ViewPerfil panelPersonal;
	private ViewContancia panelConstancia;
	private ViewConstanciasSolicitadas registroDeSolicitud;
	private JButton btnRecargar;
	private JButton btnCambiarContrasena;
	private JButton btnPerfil;
	private ViewCambiarContrasenia panelCambiarContrasenia;
	private ViewBienvenida panelBienvenida;

	/**
	 * Create the frame.
	 */

	public ViewEstudiante(Estudiante estudiante) {

		panelPersonal = new ViewPerfil(estudiante);
		panelConstancia = new ViewContancia(estudiante);
		registroDeSolicitud = new ViewConstanciasSolicitadas(estudiante);

		panelCambiarContrasenia = new ViewCambiarContrasenia(estudiante, this);
		panelBienvenida = new ViewBienvenida();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 772);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Funcionalidades");
		menuBar.add(mnNewMenu);

		JMenuItem mbtnSolicitudConstanciaMenuItem = new JMenuItem("Solicitud Constancia");
		mbtnSolicitudConstanciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelConstancia, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();
			}
		});
		mnNewMenu.add(mbtnSolicitudConstanciaMenuItem);

		JMenuItem mbtnRegistroDeConstaciaMenuItem = new JMenuItem("Registro de Constancias");
		mbtnRegistroDeConstaciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(registroDeSolicitud, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();

			}
		});

		mnNewMenu.add(mbtnRegistroDeConstaciaMenuItem);

		Component horizontalGlue = Box.createHorizontalGlue();
		menuBar.add(horizontalGlue);

		btnRecargar = new JButton("");
		btnRecargar.setIcon(new ImageIcon(ViewAnalista.class.getResource("/images/refresh32.png")));
		btnRecargar.setToolTipText("Recarga la pestña actual");
		btnRecargar.setOpaque(false);
		btnRecargar.setFocusPainted(false);
		btnRecargar.setContentAreaFilled(false);
		btnRecargar.setBorderPainted(false);
		btnRecargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPersonal = new ViewPerfil(estudiante);
				panelConstancia = new ViewContancia(estudiante);
				registroDeSolicitud = new ViewConstanciasSolicitadas(estudiante);

				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelBienvenida, BorderLayout.CENTER);
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

		// Set de Panel de Bienvenida
		panelActual.removeAll();
		panelActual.repaint();
		panelActual.revalidate();
		panelActual.add(panelBienvenida, BorderLayout.CENTER);
		panelActual.repaint();
		panelActual.revalidate();

	}
}
