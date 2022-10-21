package com.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Area;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class Area
 */
@Stateless
@LocalBean
public class AreaBean implements AreaBeanRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public AreaBean() {
	}

	@Override
	public Area save(Area entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			if (findByName(entity.getNombre()) != null)
				throw new EntityAlreadyExistsException("Ya existe un Area con el nombre " + entity.getNombre());

			em.persist(entity);
			em.flush();
			em.refresh(entity);
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en el alta del Area " + entity.getNombre() + ": " + e.getMessage());
		}
	}

	@Override
	public Area remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			Area entity = em.find(Area.class, id);
			if (entity == null)
				throw new NotFoundEntityException("No existe un Area con el ID:" + id);

			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en la baja del Area con el ID " + id);
		}
	}

	@Override
	public Area update(Area entity) throws ServiceException {
		try {
			if (em.find(Area.class, entity.getId()) == null)
				throw new NotFoundEntityException("No existe un Area con el ID:" + entity.getId());
				
			Area returnEntity = em.merge(entity);
			em.flush();
			return returnEntity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al modificar el Area " + entity.getNombre());
		}
	}

	@Override
	public Area findById(Long id) {
		return em.find(Area.class, id);
	}

	@Override
	public List<Area> findAll() {
		TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a", Area.class);
		return query.getResultList();
	}

	@Override
	public Area findByName(String nombre) {
		TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a WHERE a.nombre = ?1", Area.class)
				.setParameter(1, nombre).setMaxResults(1);

		// El query.getSingleResult() si no tiene resultado arroja NoResultException
		// Asi que si atrapa un NoResultException se envia un empty Optional ya que
		// es lo mismo que un no resutlado
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
