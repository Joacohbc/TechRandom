package validation;

import java.time.LocalDate;

public class ValidarCampos {

	// Valido que un string no este vacio,sino muestro un error
	public static boolean ValidarString(String campo, String s) {
		if (s.isBlank()) {
			Mensajes.MostrarError("El campo " + campo + " no puede estar vacio");
			return false;
		}
		return true;
	}

	// Valido que un integer valido, sino muestro un error
	public static boolean ValidarInt(String campo, String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			Mensajes.MostrarError("El campo " + campo + " no es un numero valido");
			return false;
		}
	}

	// Valido que un integer valido, sino muestro un error
	public static boolean ValidarDouble(String campo, String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			Mensajes.MostrarError("El campo " + campo + " no es un numero valido");
			return false;
		}
	}

	// Valido que una fecha valida (yyyy-dd-mm), sino muestro un error
	public static boolean ValidarFecha(String campo, String s) {
		try {

			LocalDate d = LocalDate.parse(s);
			if (d.isAfter(LocalDate.now())) {
				throw new Exception("la fecha ingresda debe ser anterior a la de hoy");
			}
			return true;
		} catch (Exception e) {
			Mensajes.MostrarError("El campo " + campo + " no es una fecha valida: " + e.getMessage());
			return false;
		}
	}
}
