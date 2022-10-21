package com.services;

import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Area;
import com.entities.Material;
import com.entities.Salon;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class SalonBean
 */
@Stateless
@LocalBean
public class SalonBean implements SalonBeanRemote {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public SalonBean() {
	}

	@Override
	public Salon save(Salon entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			if (findByName(entity.getNombre()) != null)
				throw new EntityAlreadyExistsException("Ya existe un Salon con el nombre " + entity.getNombre());

			em.persist(entity);
			em.flush();
			em.refresh(entity);
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en el alta del Salon " + entity.getNombre() + ": " + e.getMessage());
		}
	}

	@Override
	public Salon remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			Salon entity = em.find(Salon.class, id);
			if (entity == null)
				throw new NotFoundEntityException("No existe un Salon con el ID:" + id);

			em.remove(entity);
			em.flush();
			return entity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error en la baja del Salon con el ID " + id);
		}
	}

	@Override
	public Salon update(Salon entity) throws ServiceException, NotFoundEntityException {
		try {
			if (em.find(Salon.class, entity.getId()) == null)
				throw new NotFoundEntityException("No existe un Salon con el ID:" + entity.getId());

			Salon returnEntity = em.merge(entity);
			em.flush();
			return returnEntity;
		} catch (PersistenceException e) {
			throw new ServiceException("Error al modificar el Salon " + entity.getNombre());
		}
	}

	@Override
	public Salon findById(Long id) {
		return em.find(Salon.class, id);
	}

	@Override
	public List<Salon> findAll() {
		TypedQuery<Salon> query = em.createQuery("SELECT s FROM Salon s", Salon.class);
		return query.getResultList();
	}

	@Override
	public List<Salon> findByArea(String area) {
		return em.createQuery("SELECT s FROM Salon s WHERE s.area.nombre = ?1", Salon.class).setParameter(1, area)
				.getResultList();
	}
	
	@Override
	public Salon findByName(String nombre) {
		TypedQuery<Salon> query = em.createQuery("SELECT s FROM Salon s WHERE s.nombre = ?1", Salon.class)
				.setParameter(1, nombre).setMaxResults(1);

		// El query.getSingleResult() si n otiene resultado arroja NoResultException
		// Asi que si atrapa un NoResultException se envia un empty Optional ya que
		// es lo mismo que un no resutlado
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void addMaterial(Long idMaterial, Long idSalon) throws ServiceException, NotFoundEntityException {
		try {
			Salon salon = findById(idSalon);
			if (salon == null)
				throw new NotFoundEntityException("No existe un Salon con el ID:" + idMaterial);

			Material material = em.find(Material.class, idMaterial);
			if (material == null)
				throw new NotFoundEntityException("No existe un Material con el ID:" + idSalon);

			salon.getMateriales().add(material);
			em.merge(material);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiceException("Error al agregar el Material");
		}
	}

	@Override
	public void removeMaterial(Long idMaterial, Long idSalon) throws ServiceException, NotFoundEntityException {
		try {
			Salon salon = findById(idSalon);
			if (salon == null)
				throw new NotFoundEntityException("No existe un Salon con el ID:" + idMaterial);

			Material material = em.find(Material.class, idMaterial);
			if (material == null)
				throw new NotFoundEntityException("No existe un Material con el ID:" + idSalon);

			salon.getMateriales().remove(Integer.parseInt(idMaterial.toString()));
			em.merge(salon);
			em.flush();
		} catch (PersistenceException e) {
			throw new ServiceException("Error al agregar el Material");
		}
	}

}
