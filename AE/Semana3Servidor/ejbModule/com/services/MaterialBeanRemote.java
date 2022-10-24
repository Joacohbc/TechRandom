package com.services;

import javax.ejb.Remote;

import com.entities.Material;

@Remote
public interface MaterialBeanRemote extends ServiceInterface<Material> {
	Material findByName(String nombre);
}
