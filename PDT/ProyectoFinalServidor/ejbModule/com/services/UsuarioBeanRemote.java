package com.services;

import java.util.List;

import javax.ejb.Remote;

import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;
import com.exceptions.InvalidUserException;
import com.exceptions.ServiceException;

@Remote
public interface UsuarioBeanRemote {
//	Analista register(Analista analiasta) throws ServiceException, EntityAlreadyExistsException;
//	Estudiante register(Estudiante estudiante) throws ServiceException, EntityAlreadyExistsException;
//	Tutor register(Tutor tutor) throws ServiceException, EntityAlreadyExistsException;

	<T extends Usuario> T register(T usuario) throws ServiceException, InvalidUserException;

	<T extends Usuario> T login(String nombreUsuario, String password, Class<T> tipoUsu) throws ServiceException, InvalidUserException;
	
	<T extends Usuario> List<T> findAll(Class<T> tipoUsu);
	
	<T extends Usuario> List<T> findAll(Class<T> tipoUsu, EstadoUsuario estado, Itr itr);
	
	void updateEstadoUsuario(Long id, EstadoUsuario estadoUsuario) throws ServiceException;
	
	<T extends Usuario> T findById(Class<T> tipoUsu,Long id) throws ServiceException, InvalidUserException;
	
	Estudiante findEstudiantebyId(Long idUsuario) throws ServiceException, InvalidUserException;
	
}
