package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.exceptions.ServiceException;
import com.models.Usuario;

/**
 * Session Bean implementation class ControlSesionesSingleton
 */
@Singleton
@LocalBean
public class ControlSesionesSingleton implements ControlSesionesSingletonRemote {

	@PersistenceContext
	private EntityManager em;
	
	private List<String> usuarios = new LinkedList<String>();

	public ControlSesionesSingleton() {
	}

	private void addUsuario(String documento) throws ServiceException {
		if (usuarios.contains(documento)) {
			throw new ServiceException("El Usuario con el documento " + documento + "  ya esta logueado en el Sistema");
		}
		usuarios.add(documento);
	}

	private void removeUsuario(String documento) throws ServiceException {
		if (!usuarios.contains(documento)) {
			throw new ServiceException("El Usuario con el documento " + documento + " no esta logueado en el Sistema");
		}
		usuarios.remove(documento);
	}
	
	private Usuario login(String documento, String password) {
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.documento = ?1 AND u.clave = ?2", Usuario.class)
					.setParameter(1, documento)
					.setParameter(2, password)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario iniciarSesion(String documento, String password) throws ServiceException {
		Usuario usu = login(documento, password);
		if(usu == null) {
			return null;
		}

		addUsuario(documento);
		return usu;
	}
	
	public void cerrarSesion(String email) throws ServiceException {
		removeUsuario(email);
	}

	@Override
	public List<String> getUsuarios() {
		return usuarios;
	}
}
