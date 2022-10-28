package validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

import validation.Validaciones.ValidacionesFecha;

public class ValidacionesUsuario {

	public ValidacionesUsuario() {
	}

	public static ValidationObject validarDocumentoUruguayo(String documento) {
		return Validaciones.ValidarCedulaUruguaya(documento) ? ValidationObject.VALID
				: new ValidationObject("La cedula uruguaya debe contener los puntos, guiones y el digito verificador");
	}

	public static ValidationObject validarDocumentoNoUruguayo(String documento) {
		return Validaciones.ValidarLargo(documento, 20) ? ValidationObject.VALID
				: new ValidationObject("El documento debe contener un maximo de 20 caracteres");
	}

	public static ValidationObject validarNombreUsuario(String nombreUsuario) {
		if (!Pattern.matches("[a-z]+(\\.)[a-z]+", nombreUsuario) && !Pattern.matches("[a-z]+(\\.)[a-z]+(\\.)[a-z]+", nombreUsuario)) {
			return new ValidationObject("El nombre de usuario es invalido, debe tener el formato \"nombre.apellido\"");
		}

		if (!Validaciones.ValidarLargo(nombreUsuario, 3, 64)) {
			return new ValidationObject("El nombre de usuario debe tener un minimo de 3 caracteres y un maximo de 64");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarNombres(String nombre) {
		if (!Validaciones.ValidarSoloLetras(nombre, true)) {
			return new ValidationObject("Los nombres solo deben contener letras y/o espacios");
		}

		if (!Validaciones.ValidarLargo(nombre, 100)) {
			return new ValidationObject("Los nombres deben tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarApellido(String apellido) {
		if (!Validaciones.ValidarSoloLetras(apellido, true)) {
			return new ValidationObject("Los apellidos solo deben contener letras y/o espacios");
		}

		if (!Validaciones.ValidarLargo(apellido, 100)) {
			return new ValidationObject("Los apellidos deben tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

	public static ValidationObject validarFechaNacimiento(LocalDate fecNacimiento) {
		return Validaciones.ValidarFechaMax(fecNacimiento, LocalDate.now(), ValidacionesFecha.NO_ESTRICTAMENTE)
				? ValidationObject.VALID
				: new ValidationObject("La fecha de nacimiento debe ser menor a la fecha actual");
	}

	public static ValidationObject ValidarTelefono(String telefono) {
		if (!Pattern.matches("[1-9+-]", telefono)) {
			return new ValidationObject("El telefono solo debe contener numeros, + o -");
		}

		if (!Validaciones.ValidarLargo(telefono, 20)) {
			return new ValidationObject("El telefono debe tener un maximo de 20 caracteres");
		}

		return ValidationObject.VALID;
	}
	
	public static ValidationObject validarEmailUTEC(String email) {
		if(!Validaciones.ValidarMail(email)) {
			return new ValidationObject("El email tiene un formato invalido");
		}
		
		String[] partes = email.split("@");
		
		if(!partes[1].contains("utec.edu.uy")) {
			return new ValidationObject("El email de UTEC debe pertener al dominio utec.edu.uy");
		}
		
		return ValidationObject.VALID;
	}
	
	public static  ValidationObject validarLocalidad(String localidad) {
		if (!Validaciones.ValidarLargo(localidad, 100)) {
			return new ValidationObject("La localidad debe tener un maximo de 100 caracteres");
		}

		return ValidationObject.VALID;
	}

}
