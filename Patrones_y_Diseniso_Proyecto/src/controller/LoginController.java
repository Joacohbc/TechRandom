package controller;

import java.sql.SQLException;

import model.dao.DAOPersona;
import model.entity.Persona;
import validation.Mensajes;
import view.login.LoginView;

public class LoginController {
	
	private LoginView panel;
	
	public LoginController(LoginView panel) {
		super();
		this.panel = panel;
	}

	public void Loguearse() {
		Persona usuario;
		try {
			usuario = DAOPersona.findPersonAccount(panel.getTxtMail().getText(), new String(panel.getPassClave().getPassword()));
			if(usuario != null) {
				GestionController.getInstance(usuario).setVisible(true);
				panel.setVisible(false);
			}else {
				Mensajes.MostrarError("El usuario con esa credenciales no existe");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
