package semana1.juegos;


public class JuegoSlot extends Juego {

    // Enuemrado para el tipo de Mecanica del Juego // 
    public enum Mecanica {
        PALANCA,
        BOTONES;

        @Override
        public String toString() {
            // Retonar la primera Mayuscual y el resto minuscula
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    // Atributos // 

    private Mecanica mecanica;

    // Constructores // 

    public JuegoSlot(String nombre, Mecanica mecanica) {
        super(nombre);
        this.mecanica = mecanica;
    }

    public JuegoSlot() {
    }

    // Metodos // 

    public Mecanica getMecanica() {
        return mecanica;
    }

    public void setMecanica(Mecanica mecanica) {
        this.mecanica = mecanica;
    }

    @Override
    public String toString() {
        return String.format("JuegoSlot [\n\tNombre=%s\n\tMecanica=%s\n]", getNombre(), mecanica.toString());
    }
}
