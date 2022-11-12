package com.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.ConstanciaDAO;
import com.entities.AccionConstancia;
import com.entities.Constancia;
import com.entities.enums.EstadoSolicitudes;
import com.exceptions.DAOException;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class ConstanciaBean
 */
@Stateless
@LocalBean
public class ConstanciaBean implements ConstanciaBeanRemote {

	@EJB
	private ConstanciaDAO dao;

	public ConstanciaBean() {
	}

	private void validarConstancia(Constancia entity) throws InvalidEntityException {
		// TODO: Validar los campos
		// TODO: Validar que la Constancia no se repita
	}

	private void validarAccionConstancia(AccionConstancia entity) throws InvalidEntityException {
		// TODO: Validar los campos
		// TODO: Validar que la Accion Constancia no se repita
	}
	
	@Override
	public Constancia solicitar(Constancia entity) throws ServiceException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(entity, "Al solictar una Constancia, esta no puede ser nula");

			if (entity.getIdConstancia() != null)
				throw new InvalidEntityException("Al solictar una Constancia, esta no puede tener un ID asignado");

			validarConstancia(entity);

			entity.setEstado(EstadoSolicitudes.INGRESADO);
			entity.setFechaHora(LocalDateTime.now());
			return dao.insert(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Constancia update(Constancia entity)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(entity, "Al actualizar una Constancia, esta no puede ser nula");
			ServicesUtils.checkNull(entity.getIdConstancia(),
					"Al actualizar una Constancia, esta debe tener un ID asignado");

			Constancia actual = findById(entity.getIdConstancia());
			if (actual == null)
				throw new NotFoundEntityException("No existe una constancia con el ID: " + entity.getIdConstancia());

			if (actual.getEstado() == EstadoSolicitudes.FINALIZADO)
				throw new NotFoundEntityException("No se puede modificar una constancia que ya esta finalizada");

			validarConstancia(entity);

			// La Fecha y Hora de emision y el Estado de la constancia no debe cambiado
			entity.setFechaHora(actual.getFechaHora());
			entity.setEstado(actual.getEstado());
			return dao.update(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Constancia findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Constancia> findAll() {
		return dao.findAll();
	}

	private AccionConstancia addAccionConstancia(AccionConstancia entity) throws ServiceException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(entity, "Al solictar una Constancia, esta no puede ser nula");

			if (entity.getIdAccionConstancia() != null)
				throw new InvalidEntityException("Al solictar una Accion Constancia, esta no puede tener un ID asignado");

			validarAccionConstancia(entity);

			entity.setFechaHora(LocalDateTime.now());
			return dao.insert(entity);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Constancia updateEstado(Long id, EstadoSolicitudes estado, AccionConstancia accion)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(id, "Al actualizar una Constancia, esta debe tener un ID asignado");

			Constancia actual = findById(id);
			if (actual == null)
				throw new NotFoundEntityException("No existe una constancia con el ID: " + id);

			if (actual.getEstado() == EstadoSolicitudes.FINALIZADO)
				throw new NotFoundEntityException("No se puede modificar una constancia que ya esta finalizada");
			
			// Fuerzo a que la accion constancia se para la constancia con ese ID
			accion.setConstancia(actual);
			addAccionConstancia(accion);
			
			actual.setEstado(estado);
			return dao.update(actual);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public byte[] descargarConstancia(Long id) throws ServiceException, NotFoundEntityException {
		return null;
	}

	
}