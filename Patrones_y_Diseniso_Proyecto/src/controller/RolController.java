package controller;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import model.entity.Persona;
import view.persona.AltaPersonaView;
import view.persona.BajaPersonaView;
import view.persona.ConsultaPersonaView;
import view.persona.ModificacionPersonaView;
import view.rol.AltaRol;
import view.rol.BajaRol;
import view.rol.ConsultaRol;
import view.rol.ModificacionRol;

public class RolController extends JTabbedPane {

		private JScrollPane scrollPane;

		/**
		 * Create the panel.
		 */

		public RolController(Persona usuario) {
			// Quiero que las Tabs esten arriba
			super(JTabbedPane.TOP);
			
//			if (permisosPersona.isAlta()) {
				JPanel pAltaRol = new AltaRol();
				addTab("Alta", new ImageIcon(GestionController.class.getResource("/controller/image/personas/create.png")),
						pAltaRol, null);
				pAltaRol.setLayout(new BorderLayout(0, 0));
				scrollPane = new JScrollPane();
				pAltaRol.add(scrollPane, BorderLayout.CENTER);
//			}

//			if (permisosPersona.isBaja()) {
				JPanel pBajaRol = new BajaRol();
				addTab("Baja", new ImageIcon(GestionController.class.getResource("/controller/image/personas/remove.png")),
						pBajaRol, null);
//			}

//			if (permisosPersona.isModificacion()) {
				JPanel pModificacionRol = new ModificacionRol();
				addTab("Modificacion",
						new ImageIcon(GestionController.class.getResource("/controller/image/personas/modif.png")),
						pModificacionRol, null);
//			}

//			if (permisosPersona.isConsulta()) {
				JPanel pConsultasRol = new ConsultaRol();
				addTab("Consulta",
						new ImageIcon(GestionController.class.getResource("/controller/image/personas/buscar.png")),
						pConsultasRol, null);
//			}
		}
	}


