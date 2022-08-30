package model.entity;

import java.util.LinkedList;

public class Rol {
	
	private String nombre;
	private String descripcion;
	private LinkedList<Funcionalidad> funcionalidades;
	
	public Rol() {
		super();
	}

	public Rol(String nombre, String descripcion, LinkedList<Funcionalidad> funcionalidades) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.funcionalidades = funcionalidades;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LinkedList<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}
	public void setFuncionalidades(LinkedList<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}




	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + ", descripcion=" + descripcion + ", funcionalidades=" + funcionalidades + "]";
	}
	
	
	
	
	
	
	
}
