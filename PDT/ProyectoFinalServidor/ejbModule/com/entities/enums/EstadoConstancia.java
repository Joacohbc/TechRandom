package com.entities.enums;

public enum EstadoConstancia {
	INGRESADO("Ingresado"),
	EN_PROCESO("En proceso"),
	FINALIZADO("Finalizado");
		
	private String estado;
	
	private EstadoConstancia(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return estado;
	}
}
