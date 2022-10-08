package model.entity;

public enum Funcionalidad {
	ALTA_PERSONA(1,"Alta de personas", "Permite al usuario ingresar nuevas personas"), 
	BORRAR_PERSONA(2, "Eliminacion de personas", "Permite al usuario eliminar personas"),
	MODIFICAR_PERSONA(3,"Modificacion de personas", "Permite al usuario modificar personas (incluyendo contraseñas)"), 
	CONSULTAR_PERSONAS(4, "Consutar de personas", "Permite al usuario consultar personas"),
	ALTA_ROL(5,"Alta de roles", "Permite al usuario ingresar nuevos roles"), 
	BORRAR_ROL(6, "ELiminacion de roles", "Permite al usuario eliminar roles"),
	MODIFICAR_ROL(7, "Modificacion de roles", "Permite al usuario modificar roles"),
	CONSULTAR_ROLES(8, "Consulta de roles", "Permite al usuario consultar los roles"),
	CONSULTAR_FUNCIONALIDADES(9, "Consulta de funcionalidades", "Permite al usuario consultar las funcionalidades");

	private int id;
	private String nombre;
	private String descripcion;
	
	private Funcionalidad(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String toString() {
		return nombre;
	}
}
