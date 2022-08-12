package semana1;

import semana1.Persona.EstadoPersona;

public class Main{
    
    public static void main(String[] args) {

        System.out.println("Inicio: "+ Casino.getIntance());

        Casino casino = Casino.getIntance();
        casino.setNombre("The Vega's Big Show");
        casino.setResponsable(new Persona("12345678", "Juan Martin Perez", EstadoPersona.ACTIVO));
        
        System.out.println("Fin:" + Casino.getIntance());

        // TODO: Agregar Juegos al casino y probar lo que dice la letra
    }
}
