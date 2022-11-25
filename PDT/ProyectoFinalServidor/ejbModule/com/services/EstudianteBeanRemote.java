package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Asistencia;

@Remote
public interface EstudianteBeanRemote {
	List<Asistencia> getAsistencias(Long id);
}
