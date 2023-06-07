package beans;

import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import daos.UsuariosDAO;
import entites.Usuario;

@Named("usuario")
@RequestScoped
public class LoginBean {

	public LoginBean() {
	}
	
	@EJB
	private UsuariosDAO dao;
	
	private String nombreUsuario;
	private String contrasenia;
	private String error;
	
	public String login() {
		try {
			if(dao.getUserID(nombreUsuario, Usuario.toMD5(contrasenia)) != null ) {				
				error = null;
				return "sucess.xhtml";
			} else{
				 error = "Error en el ingreso de las credenciales";
				 return "login.xhtml";
			}
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
