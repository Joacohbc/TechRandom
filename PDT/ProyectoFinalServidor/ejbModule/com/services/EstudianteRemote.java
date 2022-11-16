package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Evento;

@Remote
public interface EstudianteRemote {

	List<Evento> findByEstudianteId(Long id);
}
