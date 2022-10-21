package com.services;

import javax.ejb.Remote;

import com.entities.Material;
import com.exceptions.NotFoundEntityException;
import com.exceptions.ServiceException;

@Remote
public interface MaterialBeanRemote extends ServiceInterface<Material> {
	Material findByName(String nombre);
}
