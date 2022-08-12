package semana1.juegos;


public class JuegoMesa extends Juego {  
    
        // Atributos // 
    
        private int OcupacionM2;
        private int minJugadores;
        private int maxJugadoores;

        // Constructores // 

        public JuegoMesa(String nombre, int ocupacionM2, int minJugadores, int maxJugadoores) {
            super(nombre);
            OcupacionM2 = ocupacionM2;
            this.minJugadores = minJugadores;
            this.maxJugadoores = maxJugadoores;
        }

        public JuegoMesa() {
        }

        // Metodos //     

        public int getMinJugadores() {
            return minJugadores;
        }

        public void setMinJugadores(int minJugadores) {
            this.minJugadores = minJugadores;
        }

        public int getMaxJugadoores() {
            return maxJugadoores;
        }

        public void setMaxJugadoores(int maxJugadoores) {
            this.maxJugadoores = maxJugadoores;
        }

        @Override
        public String toString() {
            return String.format("JuegoMesa [\n\tNombre = %s\n\tOcupacion en m2 = %d\n\tMinimo de Jugadores=%d\n\tMaximo de Jugadores=%d\n]", getNombre(), OcupacionM2, minJugadores, maxJugadoores);
        }
}
