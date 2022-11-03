package com.services;

import javax.ejb.Remote;

import com.models.Usuario;

@Remote
public interface UsuarioBeanRemote extends ServiceInterface<Usuario>{
	Usuario findByMail(String email); 
//	Usuario login(String email, String password); 
}
