package com.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Rol;
import com.services.UsuarioBean;

@Named("listadoUsuario")
@SessionScoped
public class ListadoUsuarioJSF implements Serializable {

	@EJB
	private UsuarioBean bean;
	
	@Inject
	private EnumJSF enumBean;
	
	private List<String> roles;
	private List<Usuario> usuarios = new LinkedList<Usuario>();
	private Usuario usuarioSeleccionado;
	
	public void onRoleUpdate() {
		List<Usuario> listaFinal = new LinkedList<Usuario>();
		for(String r : roles) {
			Rol rol = enumBean.toRol(r);
			
			if(rol == Rol.ANALISTA) {
				listaFinal.addAll(bean.findAll(Analista.class));
			} else if(rol == Rol.ESTUDIANTE) {
				listaFinal.addAll(bean.findAll(Estudiante.class));
			} else {
				listaFinal.addAll(bean.findAll(Tutor.class));
			}
		}
		usuarios = listaFinal;
	}
	
	public void deleteUsuario() {
		bean.updateEstadoUsuario(usuarioSeleccionado.getIdUsuario(), EstadoUsuario.ELIMINADO);
		usuarioSeleccionado.setEstadoUsuario(EstadoUsuario.ELIMINADO);
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
}
