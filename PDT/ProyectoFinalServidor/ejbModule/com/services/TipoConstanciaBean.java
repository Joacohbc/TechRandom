package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.daos.TipoConstanciaDAO;
import com.entities.Constancia;
import com.entities.TipoConstancia;
import com.exceptions.DAOException;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

/**
 * Session Bean implementation class TipoConstanciaBean
 */
@Stateless
public class TipoConstanciaBean implements TipoConstanciaBeanRemote {

	/**
	 * Default constructor.
	 */
	public TipoConstanciaBean() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	private TipoConstanciaDAO dao;

	@Override
	public TipoConstancia findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public TipoConstancia insert(TipoConstancia entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoConstancia update(TipoConstancia entity)
			throws ServiceException, NotFoundEntityException, InvalidEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoConstancia eliminarTipoConstancia(Long id) throws ServiceException, NotFoundEntityException {
		try {
			ServicesUtils.checkNull(id, "Al registra un TipoConstancia el ID no puede ser nulo");
		
			TipoConstancia actual = dao.findById(id);
			if(actual == null)
				throw new NotFoundEntityException("No existe un TipoConstancia con el ID: " + id);
		
			dao.remove(actual);
			return actual;
		}catch(DAOException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public byte[] descargarPlantilla(Long id) throws ServiceException, NotFoundEntityException {
		// TODO Auto-generated method stub
		return null;
	}

}
