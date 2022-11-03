package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.exceptions.ServiceException;
import com.models.Usuario;

@Remote
public interface ControlSesionesSingletonRemote {
	Usuario iniciarSesion(String documento, String password) throws ServiceException;
	void cerrarSesion(String documento) throws ServiceException;
	List<String> getUsuarios();
}
