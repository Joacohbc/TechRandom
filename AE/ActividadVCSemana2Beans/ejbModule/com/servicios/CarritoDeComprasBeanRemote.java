package com.servicios;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CarritoDeComprasBeanRemote {
	void addCompra(String compra);
	void removeCompra(String compra);
	List<String> listarCompras();
}
