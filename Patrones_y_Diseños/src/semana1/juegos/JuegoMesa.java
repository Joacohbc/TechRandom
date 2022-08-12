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
            return String.format("JuegoMesa [Nombre = %sOcupacion en m2 = %dMinimo de Jugadores=%dMaximo de Jugadores=%d]", getNombre(), OcupacionM2, minJugadores, maxJugadoores);
        }
}
