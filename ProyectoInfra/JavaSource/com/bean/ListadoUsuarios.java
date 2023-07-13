package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.Genero;
import com.services.UsuarioBean;

@Named("listadoUsuariosBean")
@ViewScoped
public class ListadoUsuarios implements Serializable {

	@EJB
	private UsuarioBean bean;
	
	@Inject
	private AuthJWTBean auth;
	
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltrados = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		if(!auth.esAnalista() && !auth.esTutor()) return;
		this.usuarios = new ArrayList<>();
		usuarios.addAll(bean.findAll(Estudiante.class));
		usuarios.addAll(bean.findAll(Analista.class));
		usuarios.addAll(bean.findAll(Tutor.class));
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
}
