package validation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertTo {

	public static String convertToSHA256(String password) {
		
		// Objeto para convertir la contraseña a HASH
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		
		// Creo el HAST a partir del password
		byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
		
		StringBuffer hashString = new StringBuffer();
		for (byte b : hash) {
			hashString.append(String.format("%02x", b));
		}

		return hashString.toString();
	}
}
