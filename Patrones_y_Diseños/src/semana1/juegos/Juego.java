package semana1.juegos;

public abstract class Juego {

    // Atributos //

    private String nombre;

    // Constructores //
    public Juego(String nombre) {
        this.nombre = nombre;
    }

    public Juego() {
    }

    // Metodos //
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public abstract String toString();
}
