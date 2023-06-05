package com.app;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private Long id;
	private String nombre;
	private Integer edad;
	private String direccion;
	private String telefono;


	private static List<Usuario> usuarios = new LinkedList<Usuario>();
	public static void addUser(Usuario u) {
		// En vez de una variable como contador, usamos el size() de un array
		// que cumple la misma funcion
		u.setId((long) usuarios.size() + 1);
		usuarios.add(u);
	}
	
	public static final String OK = "Ok";

	public static String validarUsuario(String nombre, String edad, String direccion, String telefono) {
		if(nombre.isBlank()) return "El Nombre no puede estar vacio";
		
		if(edad.isBlank()) return "La direccion no puede estar vacio";
	    try {
	    	if(Integer.valueOf(edad) <= 0) return "Edad invalida, debe ser mayor a 0";
	    } catch (NumberFormatException e) {
	    	return "La edad debe ser un numero";
		}
	    
		if(direccion.isBlank()) return "La direccion no puede estar vacio";
		if(telefono.isBlank()) return "El Telefono no puede estar vacio";
		return OK;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
