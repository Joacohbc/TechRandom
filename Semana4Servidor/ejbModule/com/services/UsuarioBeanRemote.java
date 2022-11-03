package com.services;

import javax.ejb.Remote;

import com.models.Usuario;

@Remote
public interface UsuarioBeanRemote extends ServiceInterface<Usuario>{
	Usuario findByDocumento(String documento); 
//	Usuario login(String email, String password); 
}
