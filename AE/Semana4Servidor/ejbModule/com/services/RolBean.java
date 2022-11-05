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
import com.models.Funcionalidad;
import com.models.Rol;

/**
 * Session Bean implementation class RolBean
 */
@Stateless
@LocalBean
public class RolBean implements RolBeanRemote {

	@PersistenceContext
	private EntityManager em;

	public RolBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rol save(Rol entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			// Verifico que no se repita le nombre de la Funcionalidad
			if (findByName(entity.getNombre()) != null)
				throw new EntityAlreadyExistsException(
						"Ya existe una rol con el nombre: " + entity.getNombre());
			
			// Persito, commiteo y retorno la entidad
			em.persist(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de alta del rol: " + e.getMessage());
		}
	}

	@Override
	public Rol remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una rol con ese ID
			Rol entity = findById(id);
			if (entity == null)
				throw new EntityAlreadyExistsException("No existe un rol con el ID: " + id);
			
			// Remuevo, commiteo y retorno la entidad
			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de baja del rol: " + e.getMessage());
		}
	}

	@Override
	public Rol update(Rol entity) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una funcionalidad con ese ID
			if (findById(entity.getIdRol()) == null)
				throw new EntityAlreadyExistsException("No existe una funcionalidad con el ID: " + entity.getIdRol());

			
			Rol updated = em.merge(entity);
			em.flush();
			return updated;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de baja la funcionalidad: " + e.getMessage());
		}
	}

	@Override
	public Rol findById(Long id) {
		return em.find(Rol.class, id);
	}

	@Override
	public List<Rol> findAll() {
		return em.createQuery("SELECT f FROM Rol f", Rol.class).getResultList();
	}

	@Override
	public Rol findByName(String name) {
		try {
			return em.createQuery("SELECT f FROM Rol f WHERE f.nombre = ?1", Rol.class)
					.setParameter(1, name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
