package com.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import com.daos.ConstanciaDAO;
import com.entities.AccionConstancia;
import com.entities.Constancia;
import com.entities.enums.EstadoSolicitudes;
import com.exceptions.DAOException;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

import validation.ValidacionesConstancia;
import validation.ValidationObject;

/**
 * Session Bean implementation class ConstanciaBean
 */
@Stateless
@LocalBean
public class ConstanciaBean implements ConstanciaBeanRemote {

	@EJB
	private ConstanciaDAO dao;

	@EJB
	private AccionConstanciaBean acBean;

	public ConstanciaBean() {
	}

	@Override
	public Constancia solicitar(Constancia entity) throws ServiceException, InvalidEntityException {
		try {
			ServicesUtils.checkNull(entity, "Al solictar una Constancia, esta no puede ser nula");

			if (entity.getIdConstancia() != null)
				throw new InvalidEntityException("Al solictar una Constancia, esta no puede tener un ID asignado");

			entity.setEstado(EstadoSolicitudes.INGRESADO);
			entity.setFechaHora(LocalDateTime.now());

			ValidationObject valid = ValidacionesConstancia.validarConstancia(entity);
			if (!valid.isValid())
				throw new InvalidEntityException(valid.getErrorMessage());

			if (dao.findUnique(entity) != null)
				throw new InvalidEntityException("Ya existe una Contancia con esos atributos");

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
				throw new InvalidEntityException("No se puede modificar una constancia que ya esta finalizada");

			// La Fecha y Hora de emision y el Estado de la constancia no debe cambiado
			entity.setFechaHora(actual.getFechaHora());
			entity.setEstado(actual.getEstado());

			ValidationObject valid = ValidacionesConstancia.validarConstancia(entity);
			if (!valid.isValid())
				throw new InvalidEntityException(valid.getErrorMessage());
			
			// La fecha no se verifica ya que la fecha no cambia
			if (entity.getEstudiante().getIdEstudiante() == actual.getEstudiante().getIdEstudiante()
					&& entity.getEvento().getIdEvento() == actual.getEvento().getIdEvento()
					&& entity.getTipoConstancia().getIdTipoConstancia() == actual.getTipoConstancia().getIdTipoConstancia()) {
				
				if (dao.findUnique(entity) != null) 
					throw new InvalidEntityException("Ya existe una Contancia con esos atributos, mismo Estudiante, Evento, Fecha y Tipo de Constancia");
			}

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

			// Agrego la accion constancia a la Constancia
			acBean.addAccionConstancia(accion, actual);

			// Cambio el estado de la constancia
			actual.setEstado(estado);

			return dao.update(actual);

			// Se cacha ServiceException porque se utiliza el acBean.addAccionConstancia()
		} catch (DAOException | ServiceException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public byte[] descargarConstancia(Long id) throws ServiceException, NotFoundEntityException {
		return null;
	}

	@Override
	public Constancia eliminarConstancia(Long id) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al registra una Constancia el ID no puede ser nulo");
			
			Constancia actual = dao.findById(id);
			if(actual == null)
				throw new NotFoundEntityException("No existe una Constancia con el ID: " + id);
			
			if(!actual.getAccionConstancias().isEmpty()) {
				throw new ServiceException("No puede dar de baja una Constancia que ya se le aplicaron acciones");
			}
			
			dao.remove(actual);
			return actual;
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Constancia> sacarConstanciaByIdEstudiante(Long id){
		return dao.sacarConstanciaByIdEstudiante(id);
	}
	
	
}