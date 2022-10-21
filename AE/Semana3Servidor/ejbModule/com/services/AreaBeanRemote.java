package com.services;

import javax.ejb.Remote;

import com.entities.Area;

@Remote
public interface AreaBeanRemote extends ServiceInterface<Area> {
	Area findByName(String nombre);
}
