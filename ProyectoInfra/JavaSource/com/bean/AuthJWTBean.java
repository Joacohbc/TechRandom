package com.bean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.auth.TokenManagmentBean;
import com.auth.UserDetails;
import com.entities.enums.Rol;

@SessionScoped
@Named("authBean")
public class AuthJWTBean {
	
	@EJB
	private TokenManagmentBean jwt;
	
	public AuthJWTBean() {}
		
	private String token;
	private UserDetails userInfo;

	public void generarToken( Long idUsuario, Long idRol, String nombreUsuario, Rol rol) {
		if(token != null && userInfo != null) return;
		this.userInfo = new UserDetails(idUsuario, idRol, nombreUsuario, rol);
		this.token = jwt.generarToken(idUsuario, idRol, nombreUsuario, rol);
	}
	
	public boolean esTutor() {
		if(userInfo.getRol() != Rol.TUTOR) return false;
 		return jwt.isLogged(token, userInfo);
	}
	
	public boolean esAnalista() {
		if(userInfo.getRol() != Rol.ANALISTA) return false;
 		return jwt.isLogged(token, userInfo);
	}
	
	public boolean esEstudiante() {
		if(userInfo.getRol() != Rol.ESTUDIANTE) return false;
 		return jwt.isLogged(token, userInfo);
	}
}
