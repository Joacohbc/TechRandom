package com.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class CarritoDeCompra
 */
@Stateful
public class CarritoDeComprasBean implements CarritoDeComprasBeanRemote {
	@EJB
	private EstadisticasBean estadistica; 
    
	/**
     * Default constructor. 
     */
	private List<String> listaCompras = new ArrayList<String>();
    
	public CarritoDeComprasBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCompra(String compra) {
		listaCompras.add(compra);
		estadistica.addCantElementosCarrito(1);	
	}

	@Override
	public List<String> listarCompras() {
		return listaCompras;
	}

	@Override
	public void removeCompra(String compra) {
		if(listaCompras.contains(compra)) {
			listaCompras.remove(compra);
		}
		
	}

}
