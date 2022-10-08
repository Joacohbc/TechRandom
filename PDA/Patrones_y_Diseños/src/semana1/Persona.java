package semana1;


public class Persona {

    // Estado de la persona //

    public enum EstadoPersona {
        ACTIVO, LICENCIA, INACTIVO;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    // Atributos //

    private String ci;
    private String nombre;
    private EstadoPersona estado;

    // Constructores //

    public Persona() {
    }

    public Persona(String ci, String nombre, EstadoPersona estado) {
        this.ci = ci;
        this.nombre = nombre;
        this.estado = estado;
    }

    // Metodos //

    public String getCi() {
        return ci;
    }
    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public EstadoPersona getEstado() {
        return estado;
    }
    public void setEstado(EstadoPersona estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Persona [ ci = %s, nombre = %s, estado = %s ]", ci,nombre,estado);
    }
}
