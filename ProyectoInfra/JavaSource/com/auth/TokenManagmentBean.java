package com.auth;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import com.entities.enums.Rol;

/**
 * Session Bean implementation class TokenManagmentBean
 */
@Singleton
@LocalBean
public class TokenManagmentBean {

    public TokenManagmentBean() {}
    
	@EJB
	private JWTUtils jwt;
	
	public String generarToken(Long idUsuario, Long idRol, String nombreUsuario, Rol rol) {
		UserDetails userInfo = new UserDetails();
		userInfo.setIdUsuario(idUsuario);
		userInfo.setIdRol(idRol);
		userInfo.setNombreUsuario(nombreUsuario);
		userInfo.setRol(rol);
		return jwt.generateToken(userInfo);
	}
	
	public boolean isLogged(String token, UserDetails details) {
		return jwt.validateToken(token, details);
	}
	
	public boolean isLogged(String token, Long idUsuario, Long idRol, String nombreUsuario, Rol rol) {
		UserDetails userInfo = new UserDetails();
		userInfo.setIdUsuario(idUsuario);
		userInfo.setIdRol(idRol);
		userInfo.setNombreUsuario(nombreUsuario);
		userInfo.setRol(rol);
		return isLogged(token, userInfo);
	}
}
