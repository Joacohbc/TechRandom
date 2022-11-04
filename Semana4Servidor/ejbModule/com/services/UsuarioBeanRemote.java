package com.services;

import javax.ejb.Remote;

import com.models.Usuario;

@Remote
public interface UsuarioBeanRemote extends ServiceInterface<Usuario>{
	Usuario findByDocumento(String documento); 
	String validarUsuario(Usuario u);
//	Usuario login(String email, String password); 
}
