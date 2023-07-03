package com.api.rest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Tutor;
import com.entities.Usuario;
import com.services.UsuarioBean;

@Path("/usuario")
@Produces({"application/json"})
public class UsuarioEndpoint {
	
	@EJB
	private UsuarioBean bean;
	
	@GET
	public List<Map<String, String>> list() {
		List<Usuario> listaFinal = new LinkedList<Usuario>();
		listaFinal.addAll(bean.findAll(Analista.class));
		listaFinal.addAll(bean.findAll(Estudiante.class));
		listaFinal.addAll(bean.findAll(Tutor.class));
		
		List<Map<String, String>> usuarios = new LinkedList<Map<String, String>>();
		for(Usuario usuario : listaFinal) {
			Map<String, String> json = new HashMap<>();
			json.put("documento", usuario.getDocumento());
			json.put("nombres", usuario.getNombres());
			json.put("apellidos", usuario.getApellidos());
			json.put("emailPersonal", usuario.getEmailPersonal());
			json.put("emailUtec", usuario.getEmailUtec());
			json.put("telefono", usuario.getTelefono());
			usuarios.add(json);
		}
		
		return usuarios;
	}
}
