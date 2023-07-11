package com.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.auth.TokenManagmentBean;
import com.auth.UserDetails;
import com.entities.Usuario;
import com.entities.enums.Rol;

@SessionScoped
@Named("authBean")
public class AuthJWTBean implements Serializable {
	
	@EJB
	private TokenManagmentBean jwt;
	
	public AuthJWTBean() {}
		
	private String token;
	private UserDetails userInfo;
	private Usuario user;
	
	public void generar( Long idUsuario, Long idRol, String nombreUsuario, Rol rol, Usuario user) {
		if(token != null && userInfo != null) return;
		this.userInfo = new UserDetails(idUsuario, idRol, nombreUsuario, rol);
		this.token = jwt.generarToken(idUsuario, idRol, nombreUsuario, rol);
		this.user = user;
	}
	
	public void renovar() {
		if(token == null || userInfo == null) return;
		this.token = jwt.generarToken(userInfo.getIdUsuario(), userInfo.getIdRol(), userInfo.getNombreUsuario(), userInfo.getRol());
	}
	
	public boolean yaGenerado() {
		if(token == null || userInfo == null) return false;
		if(!jwt.isLogged(token, userInfo)) return false;
		return true;
	}
	
	public void borrar() {
		this.token = null;
		this.userInfo = null;
	}
	
	public boolean esTutor() {
		if(token == null || userInfo == null) return false;
		if(userInfo.getRol() != Rol.TUTOR) return false;
 		return jwt.isLogged(token, userInfo);
	}
	
	public boolean esAnalista() {
		if(token == null || userInfo == null) return false;
		if(userInfo.getRol() != Rol.ANALISTA) return false;
 		return jwt.isLogged(token, userInfo);
	}
	
	public boolean esEstudiante() {
		if(token == null || userInfo == null) return false;
		if(userInfo.getRol() != Rol.ESTUDIANTE) return false;
 		return jwt.isLogged(token, userInfo);
	}
	
	public Rol getRol() {
		if(token == null || userInfo == null) return null;
		if(!jwt.isLogged(token, userInfo)) return null;
		return userInfo.getRol();
	}
	
	public Long getIdUsuario() {
		if(token == null || userInfo == null) return null;
		if(!jwt.isLogged(token, userInfo)) return null;
		return userInfo.getIdUsuario();
	}
	
	public Long getIdRol() {
		if(token == null || userInfo == null) return null;
		if(!jwt.isLogged(token, userInfo)) return null;
		return userInfo.getIdRol();
	}
	
	public String getNombreUsuario() {
		if(token == null || userInfo == null) return null;
		if(!jwt.isLogged(token, userInfo)) return null;
		return userInfo.getNombreUsuario();
	}

	public Usuario getUser() {
		return user;
	}
	
	public String getNombreCompleto() {
		return user.getNombres() + " " + user.getApellidos();
	}
}
