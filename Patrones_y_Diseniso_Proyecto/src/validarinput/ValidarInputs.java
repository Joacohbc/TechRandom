package validarinput;

import java.awt.event.KeyEvent;

public class ValidarInputs {
	
	private static boolean esEspacioOBorrar(KeyEvent e) {
		return e.getKeyChar() == KeyEvent.VK_SPACE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE;
	}
	
	// Validar si el caracter ingresado es un espacio, borrar o letra,sique no permita el ingreso y muestre un error
	public static void ValidarSoloLetras(KeyEvent e) {
		if (!Character.isLetter(e.getKeyChar())  && !esEspacioOBorrar(e)) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar letras");
			return;
		}
	}
	
	// Validar si el caracter ingresado es un numero o borrar, sino 
	// que no permita el ingreso y muestre un error
	public static void ValidarSoloNumeros(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar numeros");
			return;
		}
	}
	
	// Validar si el caracter ingresado es un numero, un punto (para double) o borrar, sino 
	// que no permita el ingreso y muestre un error
	public static void ValidarSoloNumerosYComa(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_PERIOD && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar numeros");
			return;
		}
	}
	
	// Validar si el caracter ingresado es una  letra, numero, espacio o borrar, sino 
	// que no permita el ingreso y muestre un error
	public static void ValidarNumerosYLetras(KeyEvent e) {
		if (!Character.isLetterOrDigit(e.getKeyChar()) && !esEspacioOBorrar(e)) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar letras y numeros, no simbolos");
			return;
		}
	}
	
	// Validar si el caracter ingresado es un numero, un guion (para fecha) o borrar, sino 
	// que no permita el ingreso y muestre un error
	public static void ValidarFechas(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '-' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se puede ingresar numeros y \"-\"");
			return;
		}
	}
}
	
	
