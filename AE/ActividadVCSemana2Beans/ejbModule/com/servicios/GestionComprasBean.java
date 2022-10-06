package com.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class GestionCompra
 */
@Stateless
public class GestionComprasBean implements GestionComprasBeanRemote {

	@EJB
	private EstadisticasBean estadistica;

	public GestionComprasBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String comprar(List<String> compras) {

		String ticket = "Ticket de Compra:\n";
		for (String compra : compras) {
			ticket += "- " + compra + "\n";
		}
		ticket += "Se realizo la compra de " + compras.size() + " productos/s";
		estadistica.addCantidadCompras(compras.size());
		return ticket;
	}

	@Override
	public String mostrarEstadistica() {
		return "Estadisticas" + "\n- Carrito: " + estadistica.getCantElementosCarrito() + "\n- Compras totales: "
				+ estadistica.getCantidadCompras();
	}

}
