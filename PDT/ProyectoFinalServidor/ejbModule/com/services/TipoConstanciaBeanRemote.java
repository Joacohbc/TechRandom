package com.services;

import javax.ejb.Remote;

import com.entities.TipoConstancia;
import com.exceptions.InvalidEntityException;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

@Remote
public interface TipoConstanciaBeanRemote {
	TipoConstancia findById(Long id);
	TipoConstancia insert(TipoConstancia entity);
	TipoConstancia update(TipoConstancia entity) throws ServiceException, NotFoundEntityException, InvalidEntityException;
	TipoConstancia eliminarTipoConstancia(Long id) throws ServiceException, NotFoundEntityException;
	byte[] descargarPlantilla(Long id) throws ServiceException, NotFoundEntityException;


}
