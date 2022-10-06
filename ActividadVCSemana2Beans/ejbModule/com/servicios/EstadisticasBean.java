package com.servicios;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class EstadisticasSingleto
 */
@Singleton
@LocalBean
public class EstadisticasBean {

	public EstadisticasBean() {
		// TODO Auto-generated constructor stub
	}

	int cantidadCompras;
	int cantElementosCarrito;

	public void addCantidadCompras(int compras) {
		cantidadCompras += Math.abs(compras);
	}

	public void addCantElementosCarrito(int productos) {
		cantElementosCarrito +=  Math.abs(productos);
	}

	public int getCantidadCompras() {
		return cantidadCompras;
	}

	public int getCantElementosCarrito() {
		return cantElementosCarrito;
	}

}
