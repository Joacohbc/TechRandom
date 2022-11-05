package com.services;

import javax.ejb.Remote;

import com.models.Funcionalidad;

@Remote
public interface FuncionalidadBeanRemote extends ServiceInterface<Funcionalidad> {
	Funcionalidad findByName(String name);
}
