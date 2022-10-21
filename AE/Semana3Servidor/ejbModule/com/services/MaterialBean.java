package com.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Material;
import com.entities.Salon;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class MateriaBean
 */
@Stateless
@LocalBean
public class MaterialBean implements MaterialBeanRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MaterialBean() {
	}

	@Override
	public Material save(Material entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			if (findByName(entity.getNombre()) != null)
				throw new EntityAlreadyExistsException("Ya existe un Material con el nombre " + entity.getNombre());

			em.persist(entity);
			em.flush();
			em.refresh(entity);
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en el alta del Material " + entity.getNombre() + ": " + e.getMessage());
		}
	}

	@Override
	public Material remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			Material entity = em.find(Material.class, id);
			if (entity == null)
				throw new NotFoundEntityException("No existe un Material con el ID:" + id);

			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en la baja del Material con el ID " + id);
		}
	}

	@Override
	public Material update(Material entity) throws ServiceException, NotFoundEntityException {
		try {
			if (em.find(Material.class, entity.getId()) == null)
				throw new NotFoundEntityException("No existe un Material con el ID:" + entity.getId());

			Material returnEntity = em.merge(entity);
			em.flush();
			return returnEntity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al modificar el Material " + entity.getNombre());
		}
	}

	@Override
	public Material findById(Long id) {
		return em.find(Material.class, id);
	}

	@Override
	public List<Material> findAll() {
		return em.createQuery("SELECT m FROM Material m", Material.class).getResultList();
	}

	@Override
	public Material findByName(String nombre) {
		TypedQuery<Material> query = em.createQuery("SELECT m FROM Material m WHERE m.nombre = ?1", Material.class)
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
