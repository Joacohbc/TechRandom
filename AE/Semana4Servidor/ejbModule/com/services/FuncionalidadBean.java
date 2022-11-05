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

/**
 * Session Bean implementation class FuncionalidadBean
 */
@Stateless
@LocalBean
public class FuncionalidadBean implements FuncionalidadBeanRemote {

	@PersistenceContext
	private EntityManager em;

	public FuncionalidadBean() {
	}

	@Override
	public Funcionalidad save(Funcionalidad entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			// Verifico que no se repita le nombre de la Funcionalidad
			if (findByName(entity.getNombre()) != null)
				throw new EntityAlreadyExistsException(
						"Ya existe una funcionalidad con el nombre: " + entity.getNombre());
			
			// Persito, commiteo y retorno la entidad
			em.persist(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de alta la funcionalidad: " + e.getMessage());
		}
	}

	@Override
	public Funcionalidad remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una funcionalidad con ese ID
			Funcionalidad entity = findById(id);
			if (entity == null)
				throw new EntityAlreadyExistsException("No existe una funcionalidad con el ID: " + id);
			
			// Remuevo, commiteo y retorno la entidad
			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de baja la funcionalidad: " + e.getMessage());
		}
	}

	@Override
	public Funcionalidad update(Funcionalidad entity) throws ServiceException, NotFoundEntityException {
		try {
			// Verifico que exista una funcionalidad con ese ID
			if (findById(entity.getIdFuncionalidad()) == null)
				throw new EntityAlreadyExistsException("No existe una funcionalidad con el ID: " + entity.getIdFuncionalidad());

			
			Funcionalidad updated = em.merge(entity);
			em.flush();
			return updated;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al dar de baja la funcionalidad: " + e.getMessage());
		}
	}

	@Override
	public Funcionalidad findById(Long id) {
		return em.find(Funcionalidad.class, id);
	}

	@Override
	public List<Funcionalidad> findAll() {
		return em.createQuery("SELECT f FROM Funcionalidad f", Funcionalidad.class).getResultList();
	}

	@Override
	public Funcionalidad findByName(String name) {
		try {
			return em.createQuery("SELECT f FROM Funcionalidad f WHERE f.nombre = ?1", Funcionalidad.class)
					.setParameter(1, name)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
}
