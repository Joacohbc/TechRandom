package com.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.entities.AccionConstancia;
import com.entities.Constancia;
import com.exceptions.DAOException;
import com.exceptions.NotFoundEntityException;

/**
 * Session Bean implementation class Constancia
 */
@Stateless
@LocalBean
public class ConstanciaDAO {

	@PersistenceContext
	private EntityManager em;

	public ConstanciaDAO() {
	}

	private Long getNextId() {
		BigDecimal id = (BigDecimal) em.createNativeQuery("SELECT seq_estudiantes.nextval FROM dual").getSingleResult();
		return id.longValue();
	}

	public Constancia insert(Constancia entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta la Constancia: " + e.getMessage());
		}
	}

	public AccionConstancia insert(AccionConstancia entidad) throws DAOException {
		try {
			em.persist(entidad);
			em.flush();
			return entidad;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta una Alta de Constancia: " + e.getMessage());
		}
	}
	
	public Constancia findById(Long id) {
		return em.find(Constancia.class, id);
	}

	public List<Constancia> findAll() {
		return em.createQuery("Select c FROM Constancia c", Constancia.class).getResultList();
	}

	public Constancia update(Constancia entidad) throws DAOException, NotFoundEntityException {
		try {
			entidad = em.merge(entidad);
			em.flush();
			return entidad;
		} catch (Exception e) {
			throw new DAOException("Ocurrio un error al hacer el update de la Constancia ", e);
		}
	}

}
