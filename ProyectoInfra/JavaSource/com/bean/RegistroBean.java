package com.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidEntityException;
import com.services.UsuarioBean;



@Named("register")
@SessionScoped
public class RegistroBean implements Serializable {
		@EJB
		private UsuarioBean bean;
		
		@Inject
		private EnumJSF enumJSF;

		// Datos del usuario
		private Usuario usuario;
		
		// Datos del Departamento y Genero
		private String departamento;
		private String genero;
		
		// Datos del Tutor
		private String area;
		private String tipoTutor;
		
		// Datos del estudiante
		private Integer generacion;
		
		@PostConstruct
		public void init() {
			usuario = new Usuario();
		}
		
		public void crearAnalista() {
			Analista a = new Analista();
			a.setDocumento(usuario.getDocumento());
			a.setNombres(usuario.getNombres());
			a.setApellidos(usuario.getApellidos());
			a.setEmailPersonal(usuario.getEmailPersonal());
			a.setEmailUtec(usuario.getEmailUtec());
			a.setTelefono(usuario.getTelefono());
			a.setFecNacimiento(usuario.getFecNacimiento());
			a.setLocalidad(usuario.getLocalidad());
			a.setContrasena(usuario.getContrasena());
			a.setDepartamento(usuario.getDepartamento());
			a.setGenero(usuario.getGenero());
			
			//Harcodeado
			a.setEstado(true);
			a.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
			
			
			Itr itr = new Itr();
			itr.setIdItr(1l);
			
			//Implementar para que se seleccione el ITR.
			a.setItr(itr);
			

		}	
		
		public void crearEstudiante() {
			Estudiante e = new Estudiante();
			e.setDocumento(usuario.getDocumento());
			e.setNombres(usuario.getNombres());
			e.setApellidos(usuario.getApellidos());
			e.setEmailPersonal(usuario.getEmailPersonal());
			e.setEmailUtec(usuario.getEmailUtec());
			e.setTelefono(usuario.getTelefono());
			e.setFecNacimiento(usuario.getFecNacimiento());
			e.setLocalidad(usuario.getLocalidad());
			e.setContrasena(usuario.getContrasena());
          // Falta departamento y Genero no se como setearlo como String.
			
			// Datos de Estudiante
			e.setGeneracion(generacion);
			
		}		
		public void crearTutor() {
			Tutor t = new Tutor();
			t.setDocumento(usuario.getDocumento());
			t.setNombres(usuario.getNombres());
			t.setApellidos(usuario.getApellidos());
			t.setEmailPersonal(usuario.getEmailPersonal());
			t.setEmailUtec(usuario.getEmailUtec());
			t.setTelefono(usuario.getTelefono());
			t.setFecNacimiento(usuario.getFecNacimiento());
			t.setLocalidad(usuario.getLocalidad());
			t.setContrasena(usuario.getContrasena());
	          // Falta departamento y Genero no se como setearlo como String.
			
			
			// Datos de tutor
			t.setArea(area);
			//Setear el tipo de tutor de manera correcta 
			//t.setTipo(enumJSF.toTipoTutor(tipoTutor));
			

		}
		public UsuarioBean getBean() {
			return bean;
		}

		public void setBean(UsuarioBean bean) {
			this.bean = bean;
		}

		public EnumJSF getEnumJSF() {
			return enumJSF;
		}

		public void setEnumJSF(EnumJSF enumJSF) {
			this.enumJSF = enumJSF;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public String getDepartamento() {
			return departamento;
		}

		public void setDepartamento(String departamento) {
			this.departamento = departamento;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public String getArea() {
			return area;
		}

		public void setArea(String area) {
			this.area = area;
		}

		public String getTipoTutor() {
			return tipoTutor;
		}

		public void setTipoTutor(String tipoTutor) {
			this.tipoTutor = tipoTutor;
		}

		public Integer getGeneracion() {
			return generacion;
		}

		public void setGeneracion(Integer generacion) {
			this.generacion = generacion;
		}
		
	}
