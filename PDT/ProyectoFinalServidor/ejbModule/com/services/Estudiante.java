package com.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.daos.EventosDao;
import com.entities.Evento;

/**
 * Session Bean implementation class Estudiante
 */
@Stateless
@LocalBean
public class Estudiante implements EstudianteRemote {

	

	@EJB
	private EventosDao dao;

    public Estudiante() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Evento> findByEstudianteId(Long id) {
    	return dao.findByEstudianteId(id);

    	
    }

        
}

