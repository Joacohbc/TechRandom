package com.entidades;

import java.util.Objects;

public class Compra {
	private String nombre; 
	private int precio;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Compra(String nombre, int precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre, precio);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		return Objects.equals(nombre, other.nombre) && precio == other.precio;
	} 
	
	
}
