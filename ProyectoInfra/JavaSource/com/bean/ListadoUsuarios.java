package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.services.UsuarioBean;

import validation.ValidacionesUsuario;
import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidationObject;

@Named("listadoUsuariosBean")
@ViewScoped
public class ListadoUsuarios implements Serializable {

	@EJB
	private UsuarioBean bean;
	
	@Inject
	private AuthJWTBean auth;
	
	private List<Usuario> usuarios;
	private List<Usuario> usuariosSeleccionados = new ArrayList<>();
	private Usuario usuarioSeleccionado;
	
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

	public void eliminarUsuario() {
		if(!auth.esAnalista()) return;
		
	}
	
	public void editarUsuario() {
		if(!auth.esAnalista()) return;
		ValidationObject error = ValidacionesUsuario.ValidarUsuarioSinContrasenia(usuarioSeleccionado, TipoUsuarioDocumento.URUGUAYO);
		if(!error.isValid()) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, error.getErrorMessage(), null);
			return;
		}
		
		try {
			if(usuarioSeleccionado instanceof Analista) {
				bean.updateAnalista((Analista) usuarioSeleccionado);
			}
			
			if(usuarioSeleccionado instanceof Tutor) {
				bean.updateTutor((Tutor) usuarioSeleccionado);
			}
			
			if(usuarioSeleccionado instanceof Estudiante) {
				bean.updateEstudiante((Estudiante) usuarioSeleccionado);
			}
			
	        PrimeFaces.current().executeScript("PF('altaUsuarioDialog').hide()");
	        PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, "Error:", e.getMessage());
		}
	}

	public List<Usuario> getUsuariosSeleccionados() {
		return usuariosSeleccionados;
	}

	public void setUsuariosSeleccionados(List<Usuario> usuariosSeleccionados) {
		this.usuariosSeleccionados = usuariosSeleccionados;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
}
