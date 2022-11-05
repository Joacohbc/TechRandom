package com.services;

import javax.ejb.Remote;

import com.models.Funcionalidad;
import com.models.Rol;

@Remote
public interface RolBeanRemote extends ServiceInterface<Rol> {
	Rol findByName(String name);
}
