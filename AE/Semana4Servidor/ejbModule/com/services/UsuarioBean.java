package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;
import com.models.Usuario;

/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
@LocalBean
public class UsuarioBean implements UsuarioBeanRemote {

	@PersistenceContext
	private EntityManager em;
	
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Usuario save(Usuario entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			// Verifico que no se repita le nombre de la usuario
			if (findByDocumento(entity.getDocumento()) != null)
				throw new EntityAlreadyExistsException(
						"Ya existe una Usuario con el documento: " + entity.getDocumento());
			
			// Persito, commiteo y retorno la entidad
			em.persist(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de alta del Usuario: " + e.getMessage());
		}
	}

	@Override
	public Usuario remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una Usuario con ese ID
			Usuario entity = findById(id);
			if (entity == null)
				throw new EntityAlreadyExistsException("No existe un Usuario con el ID: " + id);
			
			// Remuevo, commiteo y retorno la entidad
			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de baja del Usuario: " + e.getMessage());
		}
	}

	@Override
	public Usuario update(Usuario entity) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una usuario con ese ID
			if (findById(entity.getIdUsuario()) == null)
				throw new EntityAlreadyExistsException("No existe una usuario con el ID: " + entity.getIdUsuario());

			
			Usuario updated = em.merge(entity);
			em.flush();
			return updated;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al actualizar el usuario: " + e.getMessage());
		}
	}

	@Override
	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public List<Usuario> findAll() {
		return em.createQuery("SELECT f FROM Usuario f", Usuario.class).getResultList();
	}

	@Override
	public Usuario findByDocumento(String documento) {
		try {
			return em.createQuery("SELECT f FROM Usuario f WHERE f.documento = ?1", Usuario.class)
					.setParameter(1, documento)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public String validarUsuario(Usuario u) {
		try {
			if (u.getNombre().isBlank()) {
				return "El nombre del usuario no puede estar vacio";
			}
			if (u.getApellido().isBlank()) {
				return "El apellido del usuario no puede estar vacio";
			}
			if (u.getDocumento().isBlank()) {
				return "El documento del usuario no puede estar vacio";
			}
			if (u.getMail().isBlank()) {
				return "El e-mail del usuario no puede estar vacio";
			}
			if (u.getClave().isBlank()) {
				return "La clave del usuario no puede estar vacio";
			}
		}catch(Exception e) {
			return e.toString();
		}
		return null;
	}
	

}
