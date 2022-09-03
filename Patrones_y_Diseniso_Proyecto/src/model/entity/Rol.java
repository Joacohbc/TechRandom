package model.entity;

import java.util.LinkedList;
import java.util.List;

public class Rol {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private List<Funcionalidad> funcionalidades;
	
	public Rol() {
		super();
	}

	public Rol(Long  id,String nombre, String descripcion, List<Funcionalidad> funcionalidades) {
		super();
		this.id = id;
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
	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}
	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	
	
}
