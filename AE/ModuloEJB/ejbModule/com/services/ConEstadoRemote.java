package com.services;

import javax.ejb.Remote;

@Remote
public interface ConEstadoRemote {
	
	void guardarPalabra(String palabra);
	String muestraPalabra();
}
