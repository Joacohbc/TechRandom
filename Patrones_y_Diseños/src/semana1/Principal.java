package semana1;

import semana1.Persona.EstadoPersona;
import semana1.juegos.JuegoMesa;
import semana1.juegos.JuegoSlot;

public class Principal {

	public static void main(String[] args) {

		// * Entregable 1
		System.out.println("\n---------------Entregable 1---------------\n");

		System.out.println("Inicio: " + Casino.getIntance());

		Casino casino = Casino.getIntance();
		casino.setNombre("The Vega's Big Show");
		casino.setResponsable(new Persona("12345678", "William", EstadoPersona.ACTIVO));

		System.out.println("Fin: " + Casino.getIntance());

		System.out.println("\n---------------Entregable 4---------------\n");
		try {
			System.out.println("Agrego 2 Juego de Mesa:");
			casino.addJuego(new JuegoMesa()); // 1 
			casino.addJuego(new JuegoMesa()); // 2 
			System.out.println(casino);

			System.out.println("Intento agrego un 3er Juego de Mesa:");
			casino.addJuego(new JuegoMesa()); // 3 <- Error 

		} catch (CasinoError e) {
			System.err.println("Error por intentar agregar: " + e.getMessage());
		}
		
		System.out.println("\n> Vacio los Juegos <\n");
		casino.clearJuegos();
		
		try {
			System.out.println("Agrego 8 Juego de Slot:");
			casino.addJuego(new JuegoSlot()); // 1 
			casino.addJuego(new JuegoSlot()); // 2
			casino.addJuego(new JuegoSlot()); // 3
			casino.addJuego(new JuegoSlot()); // 4
			casino.addJuego(new JuegoSlot()); // 5
			casino.addJuego(new JuegoSlot()); // 6
			casino.addJuego(new JuegoSlot()); // 7
			casino.addJuego(new JuegoSlot()); // 8
			System.out.println(casino);

			System.out.println("Intento agrego un 9no Juego de Slot:");
			casino.addJuego(new JuegoSlot()); // 9 <- Error

		} catch (CasinoError e) {
			System.err.println("Error por intentar agregar: " + e.getMessage());
		}
		
		try {
			System.out.println("Agrego otra vez los otros 2 Juego de Mesa (para que sean 10):");
			casino.addJuego(new JuegoMesa()); // 9
			casino.addJuego(new JuegoMesa()); // 10 
			System.out.println(casino);

			System.out.println("Intento agrego un 11vo juego:");
			casino.addJuego(new JuegoMesa()); // 11

		} catch (CasinoError e) {
			System.err.println("Error por intentar agregar un 11vo: " + e.getMessage());
		}
	}
}
