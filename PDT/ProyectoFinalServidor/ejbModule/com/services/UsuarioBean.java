package com.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidUserException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	private String toMD5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		// Convierto la contraseña a HASH y guardo el HASH a Bytes
		// Guarda el HASH en un array de bytes en Hexadecimal
		byte[] bytes = md.digest(password.getBytes());

		// Paso el HASH de Hexadecimal a String
		StringBuilder passString = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			passString.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// Y retorno el HASH en String
		return passString.toString();
	}

	@Override
	public <T extends Usuario> T register(T usuario) throws ServiceException, InvalidUserException {
		try {
			usuario.setContrasena(toMD5(usuario.getContrasena()));
			usuario.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
			
			em.persist(usuario);	
			em.flush();
			em.refresh(usuario);
			return usuario;
		} catch (PersistenceException | NoSuchAlgorithmException e) {
			throw new ServiceException("Ocurrió un error al dar de alta al Usuario: " + e.getMessage());
		}
	}

	@Override
	public <T extends Usuario> T login(String nombreUsuario, String password, Class<T> tipoUsu) throws ServiceException, InvalidUserException{
		try {
			Long id = em
					.createQuery("SELECT u.idUsuario FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasena = ?2",
							Long.class)
					.setParameter(1, nombreUsuario).setParameter(2, toMD5(password)).getSingleResult();

			return em.find(tipoUsu, id);
		} catch (NoResultException e) {
			return null;
		} catch (PersistenceException | NoSuchAlgorithmException e) {
			throw new ServiceException("Ocurrió un error intentar iniciar sesion: " + e.getMessage());
		}
	}
}
