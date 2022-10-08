package controller;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import model.entity.Funcionalidad;
import model.entity.Persona;
import view.persona.AltaPersonaView;
import view.persona.BajaPersonaView;
import view.persona.ConsultaPersonaView;
import view.persona.ModificacionPersonaView;

public class PersonaController extends JTabbedPane {
	
	private AltaPersonaView altaPersonaView = new AltaPersonaView();
	private BajaPersonaView bajaPersonaView = new BajaPersonaView();
	private ModificacionPersonaView modificacionPersonaView= new ModificacionPersonaView();
	private ConsultaPersonaView consultaPersonaView= new ConsultaPersonaView();
	
	public PersonaController(Persona usuario) {
		super(JTabbedPane.TOP);
		
		if (usuario.getRol().getFuncionalidades().contains(Funcionalidad.ALTA_PERSONA)) {
			addTab("Alta", new ImageIcon(GestionController.class.getResource("/controller/image/personas/create.png")),
					altaPersonaView, null);
		}

		if (usuario.getRol().getFuncionalidades().contains(Funcionalidad.BORRAR_PERSONA)) {
			addTab("Baja", new ImageIcon(GestionController.class.getResource("/controller/image/personas/remove.png")),
					bajaPersonaView, null);
		}

		if (usuario.getRol().getFuncionalidades().contains(Funcionalidad.MODIFICAR_PERSONA)) {
			addTab("Modificacion",
					new ImageIcon(GestionController.class.getResource("/controller/image/personas/modif.png")),
					modificacionPersonaView, null);
		}

		if (usuario.getRol().getFuncionalidades().contains(Funcionalidad.CONSULTAR_PERSONAS)) {
			addTab("Consulta",
					new ImageIcon(GestionController.class.getResource("/controller/image/personas/buscar.png")),
					consultaPersonaView, null);
		}
	}


}
