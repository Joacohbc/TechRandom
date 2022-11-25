package com.daos;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entities.Asistencia;

/**
 * Session Bean implementation class EstudianteDAO
 */
@Stateless
@LocalBean
public class EstudianteDAO {

	@PersistenceContext
	private EntityManager em;
	
    public EstudianteDAO() {
    }
    
    public List<Asistencia> getAsistencias(Long id) {
    	return em.createQuery("SELECT a FROM Asistencia a WHERE a.estudiante.idUsuario = ?1", Asistencia.class)
    			.setParameter(1, id)
    			.getResultList();
    }
}
