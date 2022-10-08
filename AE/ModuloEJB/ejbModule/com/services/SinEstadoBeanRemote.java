package com.services;

import javax.ejb.Remote;

@Remote
public interface SinEstadoBeanRemote {
	String saludar(String nombre);
}
