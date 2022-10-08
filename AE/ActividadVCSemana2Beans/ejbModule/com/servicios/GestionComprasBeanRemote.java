package com.servicios;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface GestionComprasBeanRemote {
	String comprar(List<String> compras);
	String mostrarEstadistica();
}
