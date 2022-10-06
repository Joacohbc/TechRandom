package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class ConEstado
 */

@Stateful
public class ConEstado implements ConEstadoRemote {

	@EJB
	private BeanLocal beanLocal;

	/**
	 * Default constructor.
	 */
	public ConEstado() {
		// TODO Auto-generated constructor stub
	}

	private String palabra;

	@Override
	public void guardarPalabra(String palabra) {
		this.palabra = palabra;

	}

	@Override
	public String muestraPalabra() {
		return "La palabra " + palabra.toUpperCase() + " tiene " + beanLocal.CalcularLargo(palabra) + " de caracteres";
	}

}