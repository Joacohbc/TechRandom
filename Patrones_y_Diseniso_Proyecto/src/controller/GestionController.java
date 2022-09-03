package controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.entity.Funcionalidad;
import model.entity.Persona;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;

public class GestionController extends JFrame {

	private JPanel contentPane;
	
	// Hecha como "Singleton" ya que solo existe un solo GestionController
	private static GestionController instance = null;

	public static GestionController getInstance(Persona usuario) {
		if (instance == null) {
			instance = new GestionController(usuario);
		}
		return instance;
	}

	public static GestionController getInstance() {
		return instance;
	}

	private GestionController(Persona usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tpSecciones = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tpSecciones, BorderLayout.CENTER);

		if (usuario.getRol().getFuncionalidades().contains(Funcionalidad.ALTA_PERSONA)
				|| usuario.getRol().getFuncionalidades().contains(Funcionalidad.BORRAR_PERSONA)
				|| usuario.getRol().getFuncionalidades().contains(Funcionalidad.MODIFICAR_PERSONA)
				|| usuario.getRol().getFuncionalidades().contains(Funcionalidad.CONSULTAR_PERSONAS)) {
			
			JTabbedPane tpPersonas = new PersonaController(usuario);
			tpSecciones.addTab("Personas",
					new ImageIcon(GestionController.class.getResource("/controller/image/personas/management.png")),
					tpPersonas, null);
		}
				
		JTabbedPane pRoles = new RolController(usuario);
		tpSecciones.addTab("Roles", null, pRoles, null);

		JPanel pFuncionalidad = new JPanel();
		tpSecciones.addTab("Funcionalidad", null, pFuncionalidad, null);
	}

}
