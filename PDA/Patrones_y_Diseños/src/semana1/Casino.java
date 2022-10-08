package semana1;

import java.util.LinkedList;

import semana1.juegos.Juego;
import semana1.juegos.JuegoMesa;
import semana1.juegos.JuegoSlot;

public class Casino {

    // Atributos //
    private String nombre;
    private Persona responsable;
    private LinkedList<Juego> juegos;

    // Instancia unica de la clase //

    private static final Casino CasinoObj = new Casino();

    public static Casino getIntance() {
        return CasinoObj;
    }

    // Constructores //

    private Casino() {
        juegos = new LinkedList<>();
    }

    // Metodos //

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    private void checkMaxJuegos() throws CasinoError {
        if (juegos.size() >= 10) {
            throw new CasinoError("La lista de juegos puede contener un maximo de 10 juegos");
        }
    }

    public void addJuego(JuegoSlot slot) throws CasinoError {

        // Checkeo el maximo de juego del Casino
        checkMaxJuegos();

        int contador = 0;
        for (Juego j : juegos) {
            if (j instanceof JuegoSlot) {
                contador++;
            }

            if (contador >= 8) {
                throw new CasinoError("La lista de juegos puede contener un maximo de 8 juegos de slot");
            }
        }

        juegos.add(slot);
    }

    public void addJuego(JuegoMesa mesa) throws CasinoError {

        // Checkeo el maximo de juego del Casino
        checkMaxJuegos();

        int contador = 0;
        for (Juego j : juegos) {
            if (j instanceof JuegoMesa) {
                contador++;
            }

            if (contador >= 2) {
                throw new CasinoError("La lista de juegos puede contener un maximo de 2 juegos de mesa");
            }
        }

        juegos.add(mesa);
    }

    public void clearJuegos() {
    	juegos.clear();
    }

    @Override
    public String toString() {
    	if(!juegos.isEmpty()) {
        	String s = "";
        	for (Juego juego : juegos) {
    			s += "\n\t\t" + juego.toString() + ",";
    		}
            return String.format("Casino [\n\tnombre = %s,\n\tresponsable = %s,\n\tjuegos = [%s\n\t]\n]", nombre,responsable,s);
    	}else {
            return String.format("Casino [\n\tnombre = %s,\n\tresponsable = %s,\n\tjuegos = []\n]", nombre,responsable);
    	}

    }
}
