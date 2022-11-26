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
import views.ViewMedida;
import viewsAnalista.ViewPerfil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class ViewEstudiante extends JFrame implements ViewMedida {

	private JPanel contentPane;
	private JPanel panelActual;
	private final ViewPerfil panelPersonal;
	private final ViewContancia panelConstancia;
	private final ViewConstanciasSolicitadas registroDeSolicitud;

	/**
	 * Create the frame.
	 */

	public ViewEstudiante(Estudiante estudiante) {

		panelPersonal = new ViewPerfil(estudiante);
		panelConstancia = new ViewContancia(estudiante);
		registroDeSolicitud = new ViewConstanciasSolicitadas(estudiante);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, ANCHO_VIEW, LARGO_VIEW);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Funcionalidades");
		menuBar.add(mnNewMenu);

		JMenuItem mbtnDatosPersonalesMenuItem = new JMenuItem("Datos Personales");
		mbtnDatosPersonalesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelActual.removeAll();
				panelActual.repaint();
				panelActual.revalidate();
				panelActual.add(panelPersonal, BorderLayout.CENTER);
				panelActual.repaint();
				panelActual.revalidate();

			}
		});
		mnNewMenu.add(mbtnDatosPersonalesMenuItem);

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
	}
}
