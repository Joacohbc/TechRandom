package com.bean;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.entities.enums.Departamento;
import com.entities.enums.Genero;
import com.entities.enums.Rol;
import com.entities.enums.TipoTutor;

import validation.ValidacionesUsuario.TipoUsuarioDocumento;

@Named("enumBean")
@ApplicationScoped
public class EnumJSF {

	public EnumJSF() {
	}
	
	public List<String> getDepartamentos() {
		return Arrays.asList(Departamento.values()).stream().map(t -> t.toString()).toList();
	}
	
	public Departamento toDepartamento(String departamento) {
		return Arrays.asList(Departamento.values()).stream().filter(t -> t.toString().equals(departamento)).findFirst().orElse(null);
	}
	
	public List<String> getGeneros() {
		return Arrays.asList(Genero.values()).stream().map(t -> t.toString()).toList();
	}
	
	public Genero toGenero(String genero) {
		return Arrays.asList(Genero.values()).stream().filter(t -> t.toString().equals(genero)).findFirst().orElse(null);
	}
	
	public List<String> getTiposDocumentos() {
		return Arrays.asList(TipoUsuarioDocumento.values()).stream().map(t -> t.toString()).toList();
	}
	
	public TipoUsuarioDocumento toTipoUsuarioDocumento(String tipoUsuarioDocumento) {
		return Arrays.asList(TipoUsuarioDocumento.values()).stream().filter(t -> t.toString().equals(tipoUsuarioDocumento)).findFirst().orElse(null);
	}
	
	public List<String> getTipoTutores() {
		return Arrays.asList(TipoTutor.values()).stream().map(t -> t.toString()).toList();
	}
	
	public TipoTutor toTipoTutor(String tipoTutor) {
		return Arrays.asList(TipoTutor.values()).stream().filter(t -> t.toString().equals(tipoTutor)).findFirst().orElse(null);
	}
	
	public List<String> getRoles() {
		return Arrays.asList(Rol.values()).stream().map(t -> t.toString()).toList();
	}
	
	public Rol toRol(String rol) {
		return Arrays.asList(Rol.values()).stream().filter(t -> t.toString().equals(rol)).findFirst().orElse(null);
	}
}
