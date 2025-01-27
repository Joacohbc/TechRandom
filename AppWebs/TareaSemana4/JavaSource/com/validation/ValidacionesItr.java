package com.validation;

import com.entities.Itr;
import com.entities.enums.Departamento;

public class ValidacionesItr {

	public ValidacionesItr() {
	}

	public static ValidationObject validarItr(Itr itr) {
		
		if(itr == null)
			return new ValidationObject("La entidad no puede ser nula");
		
		ValidationObject valid = validarNombre(itr.getNombre());
		if(!valid.isValid()) 
			return valid;
		
		valid = validarDepartamento(itr.getDepartamento());
		if(!valid.isValid()) 
			return valid;

		return ValidationObject.VALID;
	}
	
	public static ValidationObject validarNombre(String nombre) {
		return Validaciones.ValidarLargo(nombre, 4, 50) ? ValidationObject.VALID 
				: new ValidationObject("El nombre del ITR no puede estar vacio y debe tener entre 4 y 50 caracteres");
	}
	
	public static ValidationObject validarDepartamento(Departamento departamento) {
		return departamento != null ? ValidationObject.VALID : new ValidationObject("El departamento es obligatorio");
	}
}
