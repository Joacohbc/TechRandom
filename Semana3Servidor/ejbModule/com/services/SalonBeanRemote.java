package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Salon;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

@Remote
public interface SalonBeanRemote extends ServiceInterface<Salon> {
	List<Salon> findByArea(String area);

	Salon findByName(String nombre);

	void addMaterial(Long idSalon, Long idMaterial) throws ServiceException, NotFoundEntityException;

	void removeMaterial(Long idSalon, Long idMaterial) throws ServiceException, NotFoundEntityException;
}
