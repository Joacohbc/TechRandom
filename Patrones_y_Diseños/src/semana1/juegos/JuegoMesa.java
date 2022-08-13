package semana1.juegos;


public class JuegoMesa extends Juego {  
    
        // Atributos // 
    
        private int ocupacionM2;
        private int minJugadores;
        private int maxJugadores;

        // Constructores // 

        public JuegoMesa(String nombre, int ocupacionM2, int minJugadores, int maxJugadores) {
            super(nombre);
            this.ocupacionM2 = ocupacionM2;
            this.minJugadores = minJugadores;
            this.maxJugadores = maxJugadores;
        }

        public JuegoMesa() {
        }

        // Metodos //     
        
        public int getOcupacionM2() {
			return ocupacionM2;
		}

		public void setOcupacionM2(int ocupacionM2) {
			this.ocupacionM2 = ocupacionM2;
		}
		
        public int getMinJugadores() {
            return minJugadores;
        }

		public void setMinJugadores(int minJugadores) {
            this.minJugadores = minJugadores;
        }

        public int getMaxJugadoores() {
            return maxJugadores;
        }

        public void setMaxJugadoores(int maxJugadores) {
            this.maxJugadores = maxJugadores;
        }

        @Override
        public String toString() {
            return String.format("JuegoMesa [ nombre = %s, ocupacionM2 = %d, minJugadores = %d, maxJugadores = %d ]", getNombre(), ocupacionM2, minJugadores, maxJugadores);
        }
}
