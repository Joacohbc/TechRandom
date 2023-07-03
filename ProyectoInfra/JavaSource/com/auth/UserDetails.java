package com.auth;

import com.entities.enums.Rol;

import io.jsonwebtoken.impl.DefaultClaims;

public class UserDetails  {
	private String nombreUsuario;
	private Rol rol;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}
