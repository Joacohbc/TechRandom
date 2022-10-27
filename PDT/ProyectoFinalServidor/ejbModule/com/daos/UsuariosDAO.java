package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.picketbox.util.KeyStoreUtil;

import com.entities.Itr;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class UsuariosDAO
 */
@Singleton
@LocalBean
public class UsuariosDAO {

	@PersistenceContext
	private EntityManager em;

	public UsuariosDAO() {
	}

	/*
	 * Periste un usuario en la Base de datos y retorna la Entidad persistida.
	 */
	public <T extends Usuario> T insert(T usuario) throws DAOException {
		try {
			em.persist(usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta al Usuario: " + e.getMessage());
		}
	}

	/*
	 * Retorna el ID de un usuario (de la Entidad Usuario) en base a su nombre de
	 * usuario y contraseña (sin inscriptar). Si no concide con ningun usuario
	 * retornara null
	 */
	public Long getUserID(String username, String password) {
		try {
			return em.createQuery("SELECT u.idUsuario FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasena = ?2",
					Long.class)
					.setParameter(1, username)
					.setParameter(2, password)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
	 * base ID Si no concide con ningun usuario retornara null
	 */
	public <T extends Usuario> T findById(Class<T> tipoUsu, Long id) {
		return em.find(tipoUsu, id);
	}
	
	/*
	 * Retorna una Lista de Usuarios del Tipo de Usuario que se le indique.
	 * Si no hay resultados retorna una lista vacia.
	 * */
	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu) {
		return em.createQuery("Select u FROM " + tipoUsu.getName() + " u", tipoUsu).getResultList();
	}
	
	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu, EstadoUsuario estado, Itr itr) {		
		return em.createQuery("Select u FROM " + tipoUsu.getName() + " u WHERE u.estadoUsuario = ?1 AND u.itr.idItr = ?2", tipoUsu)
				.setParameter(1, estado)
				.setParameter(2, itr.getIdItr())
				.getResultList();
	}
	
	public Usuario updateUsuarioEstado(Long id, EstadoUsuario estado) throws DAOException, NotFoundEntityException{
		try {
			Usuario usu = findById(Usuario.class, id);
			if (usu == null)
				throw new NotFoundEntityException("No existe un usuario el ID: " + id);
			
			usu.setEstadoUsuario(estado);
			usu = em.merge(usu);
			em.flush();
			return usu;
		}catch (PersistenceException e) {
			throw new DAOException("Ocurrio un error al cambiar el estado del usuario:",e);
		}
	}
}
