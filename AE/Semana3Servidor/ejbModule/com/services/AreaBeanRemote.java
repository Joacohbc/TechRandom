package com.services;

import java.util.Optional;

import javax.ejb.Remote;

import com.entities.Area;
import com.entities.Salon;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

@Remote
public interface AreaBeanRemote extends ServiceInterface<Area> {
	Area findByName(String nombre);

//	void addSalon(Long idArea, Long idSalon) throws ServiceException, NotFoundEntityException;
//
//	void removeSalon(Long idArea, Long idSalon) throws ServiceException, NotFoundEntityException;
}
