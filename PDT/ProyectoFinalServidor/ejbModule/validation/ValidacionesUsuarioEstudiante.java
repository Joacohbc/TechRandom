package validation;

import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Estudiante;

/**
 * Session Bean implementation class ValidacionesUsuarioEstudiante
 */
@Stateless
@LocalBean
public class ValidacionesUsuarioEstudiante {

	/**
	 * Default constructor.
	 */
	public ValidacionesUsuarioEstudiante() {
		// TODO Auto-generated constructor stub
	}

	public static ValidationObject ValidarGeneracion(String generacion) {

		if (Validaciones.ValidarSoloNumeros(generacion, false)) {

			if (generacion.length() == 4) {

				return ValidationObject.VALID;

			} else {

				return new ValidationObject("La generacion solo puede tener un maximo de 4 digitos");
			}

		} else {

			return new ValidationObject("La generacion solo puede contener numeros positivos");
		}

	}


}
