package com.services;

import java.util.List;
import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.entities.Material;

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
}
