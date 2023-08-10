package com.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
		if (!auth.esAnalista() && !auth.esTutor())
			return;
		this.usuarios = new ArrayList<>();
		usuarios.addAll(bean.findAll(Estudiante.class));
		usuarios.addAll(bean.findAll(Analista.class));
		usuarios.addAll(bean.findAll(Tutor.class));
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	private void updateEstado(Usuario usuario, Boolean estado) {
		if (!auth.esAnalista())
			return;

		if (usuario instanceof Analista) {
			bean.updateEstadoAnalista(usuario.getIdUsuario(), estado);
			((Analista) usuario).setEstado(estado);
		} else if (usuario instanceof Tutor) {
			bean.updateEstadoTutor(usuario.getIdUsuario(), estado);
			((Tutor) usuario).setEstado(estado);
		} else {
			bean.updateEstadoEstudiante(usuario.getIdUsuario(), estado);
			((Estudiante) usuario).setEstado(estado);
		}

	}

	public void bajaUsuario() {
		if (!auth.esAnalista())
			return;

		try {
			updateEstado(usuarioSeleccionado, false);
			usuarios.set(usuarios.indexOf(usuarioSeleccionado), usuarioSeleccionado);

			PrimeFaces.current().executeScript("PF('bajaUsuarioDialog').hide()");
			PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}

	public void bajaUsuarios() {
		if (!auth.esAnalista())
			return;

		try {
			for (Usuario usu : usuariosSeleccionados) {
				updateEstado(usu, false);
				usuarios.set(usuarios.indexOf(usu), usu);
			}
			PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}

	public String getBotonBajaMensaje() {
		if (!usuariosSeleccionados.isEmpty()) {
			int size = this.usuariosSeleccionados.size();
			return size > 1 ? size + " Usuarios Seleccionados" : "1 Usuario Seleccionado";
		}

		return "Borrar";
	}

	public void altaUsuarios() {
		if (!auth.esAnalista())
			return;

		try {

			for (Usuario usu : usuariosSeleccionados) {
				updateEstado(usu, true);
				usuarios.set(usuarios.indexOf(usu), usu);

			}
			PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}

	public String getBotonAltaMensaje() {
		if (!usuariosSeleccionados.isEmpty()) {
			int size = this.usuariosSeleccionados.size();
			return size > 1 ? size + " Usuarios Seleccionados" : "1 Usuario Seleccionado";
		}

		return "Alta";
	}

	public void altaUsuario() {
		if (!auth.esAnalista())
			return;

		try {
			updateEstado(usuarioSeleccionado, true);
			usuarios.set(usuarios.indexOf(usuarioSeleccionado), usuarioSeleccionado);

			PrimeFaces.current().executeScript("PF('altaUsuarioDialog').hide()");
			PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}

	public void editarUsuario() {
		if (!auth.esAnalista())
			return;

		ValidationObject error = ValidacionesUsuario.ValidarUsuarioSinContrasenia(usuarioSeleccionado,
				TipoUsuarioDocumento.URUGUAYO);
		if (!error.isValid()) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, error.getErrorMessage(), null);
			return;
		}

		try {
			if (usuarioSeleccionado instanceof Analista) {
				bean.updateAnalista((Analista) usuarioSeleccionado);
			} else if (usuarioSeleccionado instanceof Tutor) {
				bean.updateTutor((Tutor) usuarioSeleccionado);
			} else {
				bean.updateEstudiante((Estudiante) usuarioSeleccionado);
			}

			PrimeFaces.current().executeScript("PF('editarUsuarioDialog').hide()");
			PrimeFaces.current().ajax().update("form:listaUsuarios");
		} catch (Exception e) {
			JSFUtils.addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
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
