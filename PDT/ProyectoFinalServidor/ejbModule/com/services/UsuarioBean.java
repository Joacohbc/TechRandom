package com.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.UsuariosDAO;
import com.entities.Itr;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.DAOException;
import com.exceptions.InvalidUserException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

	@EJB
	private UsuariosDAO dao;

	public UsuarioBean() {
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
			return dao.insert(usuario);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("No se pudo inscripar la contraseña del usuario: " + e.getMessage());
		}
	}

	@Override
	public <T extends Usuario> T login(String nombreUsuario, String password, Class<T> tipoUsu)
			throws ServiceException, InvalidUserException {
		try {
			Long id = dao.getUserID(nombreUsuario, toMD5(password));
			if (id == null)
				throw new InvalidUserException("El nombre o la contraseña del usuario son incorrectos");

			return dao.findById(tipoUsu, id);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("Ocurrió un error intentar iniciar sesion: " + e.getMessage());
		}
	}

	@Override
	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu) {
		return dao.findAll(tipoUsu);
	}

	@Override
	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu, EstadoUsuario estado, Itr itr) {
		return dao.findAll(tipoUsu, estado, itr);
	}

	@Override
	public void updateEstadoUsuario(Long id, EstadoUsuario estadoUsuario) throws ServiceException {
		try {
			dao.updateUsuarioEstado(id, estadoUsuario);
		}catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		} catch (NotFoundEntityException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	
}
