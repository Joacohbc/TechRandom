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

	private void addUsuario(String mail) throws ServiceException {
		if (usuarios.contains(mail)) {
			throw new ServiceException("El Usuario con el mail " + mail + "  ya esta logueado en el Sistema");
		}
		usuarios.add(mail);
	}

	private void removeUsuario(String mail) throws ServiceException {
		if (!usuarios.contains(mail)) {
			throw new ServiceException("El Usuario con el mail " + mail + " no esta logueado en el Sistema");
		}
		usuarios.remove(mail);
	}
	
	private Usuario login(String email, String password) {
		try {
			return em.createQuery("SELECT u FROM Usuario u WHERE u.mail = ?1 AND u.clave = ?2", Usuario.class)
					.setParameter(1, email)
					.setParameter(2, password)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario iniciarSesion(String email, String password) throws ServiceException {
		Usuario usu = login(email, password);
		if(usu == null) {
			return null;
		}

		addUsuario(email);
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
