package com.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.ItrDAO;
import com.entities.Itr;
import com.exceptions.DAOException;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

import validation.ValidacionesItr;
import validation.ValidationObject;

/**
 * Session Bean implementation class ItrBean
 */
@Stateless
@LocalBean
public class ItrBean implements ItrBeanRemote {

	@EJB
	private ItrDAO dao;

	public ItrBean() {
	}

	@Override
	public Itr save(Itr entity) throws ServiceException, EntityAlreadyExistsException {
		try {
			ServicesUtils.checkNull(entity, "Al registrar un ITR, este no puede ser nulo");
			
			if (entity.getIdItr() != null)
				throw new InvalidEntityException("Al registrar un ITR, este no puede tener un ID asignado");

			ValidationObject valid = ValidacionesItr.validarItr(entity);
			if(!valid.isValid()) 
				throw new InvalidEntityException(valid.getErrorMessage());
			
			if (dao.findByName(entity.getNombre()) != null)
				throw new InvalidEntityException("Ya existe un ITR con el nombre: " + entity.getNombre());
			
			entity.setEstado(true);
			return dao.insert(entity);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Itr remove(Long id) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al dar de baja un ITR, el ID no puede ser nulo");
			
			Itr itr = dao.findById(id);
			if (itr == null)
				throw new NotFoundEntityException("No existe un Itr el con el ID: " + id);

			itr.setEstado(false);
			return dao.update(itr);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Itr reactivar(Long id) throws ServiceException, NotFoundEntityException {
		try {
			Itr itr = dao.findById(id);
			if (itr == null)
				throw new NotFoundEntityException("No existe un Itr el con el ID: " + id);

			itr.setEstado(true);
			return dao.update(itr);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Itr update(Itr entity) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(entity, "Al actualizar un ITR, el ID no puede ser nulo");

			if (dao.findById(entity.getIdItr()) == null)
				throw new NotFoundEntityException("No existe un Itr el con el ID: " + entity.getIdItr());
			
			return dao.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Itr findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Itr> findAll() {
		return dao.findAll();
	}

	@Override
	public Itr findByName(String nombre) {
		return dao.findByName(nombre);
	}
}
