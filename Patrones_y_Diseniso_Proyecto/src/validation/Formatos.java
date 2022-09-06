package validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public final class Formatos {

	public static final DateTimeFormatter DateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	// Retorna el texto con formato de titulo (mayuscula en cada palabra)
	public static String ToTitle(String texto) {
		String[] words = texto.trim().split(" ");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toUpperCase();
		}

		String text = words[0].charAt(0) + words[0].substring(1).toLowerCase();
		for (int i = 1; i < words.length; i++) {
			text += " " + words[i].charAt(0) + words[i].substring(1).toLowerCase();
		}

		return text;
	}

	public static String ToFormatedString(LocalDate date) throws DateTimeParseException {
		return date.format(DateFormatter);
	}

	public static LocalDate Format(LocalDate date) throws DateTimeParseException {
		return LocalDate.parse(date.format(DateFormatter), DateFormatter);
	}

	public static LocalDate Parse(String date) throws DateTimeParseException {
		return LocalDate.parse(date, DateFormatter);
	}

	public static LocalDate FromDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
