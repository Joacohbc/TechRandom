package com.services;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;

import com.daos.UsuariosDAO;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.DAOException;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;
import com.validation.ValidacionesUsuario;
import com.validation.ValidacionesUsuarioEstudiante;
import com.validation.ValidacionesUsuarioTutor;
import com.validation.ValidationObject;
import com.validation.ValidacionesUsuario.TipoUsuarioDocumento;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

	@EJB
	private UsuariosDAO dao;

	@EJB
	private MailBean mail;
	
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
	public <T extends Usuario> T register(T usuario, TipoUsuarioDocumento tipoDocumento)
			throws ServiceException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(usuario, "Al registrar un Usuario, este no puede ser nulo");

			if (usuario.getIdUsuario() != null)
				throw new InvalidEntityException("Al registrar un Usuario, este no puede tener un ID asignado");

			if (usuario instanceof Estudiante) {
				Estudiante est = (Estudiante) usuario;
				ValidationObject error = ValidacionesUsuarioEstudiante.validarEstudiante(est, tipoDocumento);
				if (!error.isValid())
					throw new InvalidEntityException(error.getErrorMessage());

			} else if (usuario instanceof Tutor) {
				Tutor tut = (Tutor) usuario;
				ValidationObject error = ValidacionesUsuarioTutor.validarTutor(tut, tipoDocumento);
				if (!error.isValid())
					throw new InvalidEntityException(error.getErrorMessage());

			} else {
				Analista ana = (Analista) usuario;
				ValidationObject error = ValidacionesUsuario.ValidarUsuario(ana, tipoDocumento);
				if (!error.isValid())
					throw new InvalidEntityException(error.getErrorMessage());
			}

			if (dao.findByDocumento(Usuario.class, usuario.getDocumento()) != null) {
				throw new InvalidEntityException("Ya existe un Usuario con el Documento: " + usuario.getDocumento());
			}

			if (dao.findByEmailUtec(Usuario.class, usuario.getEmailUtec()) != null) {
				throw new InvalidEntityException("Ya existe un Usuario con el Email Utec: " + usuario.getEmailUtec());
			}

			if (dao.findByEmailPersonal(Usuario.class, usuario.getEmailPersonal()) != null) {
				throw new InvalidEntityException("Ya existe un Usuario con el Email Personal: " + usuario.getEmailPersonal());
			}
			
			if (dao.findByNombreUsuario(Usuario.class, usuario.getNombreUsuario()) != null) {
				throw new InvalidEntityException(
						"Ya existe un Usuario con el Nombre de Usuario: " + usuario.getNombreUsuario());
			}

			usuario.setContrasena(toMD5(usuario.getContrasena()));
			usuario.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
			
			// El nombre de usuario se obtiene a partir de email
			String nombreUsu = usuario.getEmailUtec().split("@")[0];
			usuario.setNombreUsuario(nombreUsu);
			
			return dao.insert(usuario);

		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException("No se pudo inscripar la contraseña del usuario: " + e.getMessage());
		}
	}

	@Override
	public <T extends Usuario> T login(String nombreUsuario, String password, Class<T> tipoUsu)
			throws ServiceException, InvalidEntityException {
		try {
			Long id = dao.getUserID(nombreUsuario, toMD5(password));
			if (id == null)
				throw new InvalidEntityException("El nombre o la contraseña del usuario son incorrectos");

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
	public void updateEstadoUsuario(Long id, EstadoUsuario estadoUsuario)
			throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al registrar un Usuario, el ID no puede ser nulo");

			Usuario usu = findById(Usuario.class, id);
			if (usu == null)
				throw new NotFoundEntityException("No existe un usuario el ID: " + id);

			usu.setEstadoUsuario(estadoUsuario);
			dao.update(usu);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <T extends Usuario> T findById(Class<T> tipoUsu, Long id) throws ServiceException {
		return dao.findById(tipoUsu, id);
	}

	@Override
	public List<Estudiante> findAllEstudiantes() {
		return dao.findAllEstudiante();
	}

	private void validarUpdate(Usuario newUsu, Usuario actual) throws DAOException, InvalidEntityException {

		if (!newUsu.getDocumento().equals(actual.getDocumento())) {
			if (dao.findByDocumento(Usuario.class, newUsu.getDocumento()) != null)
				throw new InvalidEntityException("Ya existe un Usuario con el Documento: " + newUsu.getDocumento());
		}

		if (!newUsu.getEmailUtec().equals(actual.getEmailUtec())) {
			if (dao.findByEmailUtec(Usuario.class, newUsu.getEmailUtec()) != null)
				throw new InvalidEntityException("Ya existe un Usuario con el Email Institucional: " + newUsu.getEmailUtec());
		}

		if (!newUsu.getEmailPersonal().equals(actual.getEmailPersonal())) {
			if (dao.findByEmailPersonal(Usuario.class, newUsu.getEmailPersonal()) != null)
				throw new InvalidEntityException("Ya existe un Usuario con el Email Personal: " + newUsu.getEmailPersonal());
		}

		if (!newUsu.getNombreUsuario().equals(actual.getNombreUsuario())) {
			if (dao.findByNombreUsuario(Usuario.class, newUsu.getNombreUsuario()) != null)
				throw new InvalidEntityException(
						"Ya existe un Usuario con el Nombre de Usuario: " + newUsu.getNombreUsuario());
		}
	}

	@Override
	public void updateEstudiante(Estudiante estudiante)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {

			ServicesUtils.checkNull(estudiante, "Al actualizar un usuario este no puede ser nulo");

			Estudiante estActual = dao.findById(Estudiante.class, estudiante.getIdUsuario());
			if (estActual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + estudiante.getIdUsuario());

			estudiante.setIdEstudiante(estActual.getIdEstudiante());
			validarUpdate(estudiante, estActual);
			dao.update(estudiante);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateAnalista(Analista analista)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(analista, "Al actualizar un usuario este no puede ser nulo");

			Analista anaActual = dao.findById(Analista.class, analista.getIdUsuario());
			if (anaActual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + analista.getIdUsuario());

			analista.setIdAnalista(anaActual.getIdAnalista());
			validarUpdate(analista, anaActual);
			dao.update(analista);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} 
	}

	@Override
	public void updateTutor(Tutor tutor) throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(tutor, "Al actualizar un usuario este no puede ser nulo");

			Tutor tutActual = dao.findById(Tutor.class, tutor.getIdUsuario());
			if (tutActual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: "+tutor.getIdUsuario());

			tutor.setIdTutor(tutActual.getIdTutor());
			validarUpdate(tutor, tutActual);
			dao.update(tutor);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateEstadoEstudiante(Long id, boolean estado) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al actualizar un Usuario, su ID no puede ser nulo");

			Estudiante actual = dao.findById(Estudiante.class, id);
			if (actual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + id);
			
			actual.setEstado(estado);
			dao.update(actual);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateEstadoAnalista(Long id, boolean estado) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al actualizar un Usuario, su ID no puede ser nulo");

			Analista actual = dao.findById(Analista.class, id);
			if (actual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + id);
			
			actual.setEstado(estado);
			dao.update(actual);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateEstadoTutor(Long id, boolean estado) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al actualizar un Usuario, su ID no puede ser nulo");

			Tutor actual = dao.findById(Tutor.class, id);
			if (actual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + id);
			
			actual.setEstado(estado);
			dao.update(actual);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public <T extends Usuario> T findByDocumento(Class<T> tipoUsu, String documento) {
		try {
			return dao.findByDocumento(tipoUsu, documento);
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void updateContrasenia(Long id, String antigua, String nueva)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(id, "Al actualizar un Usuario, su ID no puede ser nulo");

			Usuario actual = dao.findById(Usuario.class, id);
			if (actual == null)
				throw new NotFoundEntityException("No existe un usuario con el ID: " + id);
				
			if(!actual.getContrasena().equals(toMD5(antigua))) {
				throw new InvalidEntityException("La contraseña antigua ingresada no es igual a la actual");
			}
			
			ValidationObject valid = ValidacionesUsuario.validarContrasena(nueva);
			if(!valid.isValid())
				throw new InvalidEntityException(valid.getErrorMessage());
			
			actual.setContrasena(toMD5(nueva));
			
			mail.enviarConGMail(actual.getEmailUtec(), "Cambio de Contraseña", "Se modifico la contraseña de su Usuario");
			mail.enviarConGMail(actual.getEmailPersonal(), "Cambio de Contraseña", "Se modifico la contraseña de su Usuario");
			dao.update(actual);
		} catch (DAOException e) {
			throw new ServiceException(e);
		} catch (NoSuchAlgorithmException | MessagingException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void olvideContrasenia(String nombreUsuario) throws ServiceException, NotFoundEntityException{
		
		Usuario usuario = dao.findByNombreUsuario(Usuario.class, nombreUsuario);
		if(usuario == null) 
			throw new NotFoundEntityException("No existe un usuario con el Nombre de Usuario: "+ nombreUsuario);
		
		try {
			String password =  toMD5(nombreUsuario).toUpperCase() + System.currentTimeMillis() + toMD5(usuario.getContrasena()).toLowerCase();
			
			usuario.setContrasena(toMD5(password.trim()));
			dao.update(usuario);

			mail.enviarConGMail(usuario.getEmailUtec(), "Contraseña Temporal para el usuario " +usuario.getNombreUsuario(), password.trim());
			mail.enviarConGMail(usuario.getEmailPersonal(), "Contraseña Temporal para el usuario " + usuario.getNombreUsuario(), password.trim());
			
		}catch (DAOException | NoSuchAlgorithmException e) {
			throw new ServiceException(e);
		} catch (MessagingException e) {
			throw new ServiceException("No se pudo enviar el correo con al nueva contraseña, intentelo mas tarde");
		}
		
	}

}
