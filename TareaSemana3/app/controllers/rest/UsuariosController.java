package controllers.rest;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import daos.UsuariosDAO;
import entites.Departamento;
import entites.Genero;
import entites.Usuario;

@RequestScoped
@Path("/usuario")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class UsuariosController {
	
	@EJB
	private UsuariosDAO dao;	
		
	@GET
	public List<Usuario> listarUsuarios() {
		return dao.findAll();
	}
}
