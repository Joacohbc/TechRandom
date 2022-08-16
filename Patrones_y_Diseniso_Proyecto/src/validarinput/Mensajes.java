package validarinput;


	import javax.swing.JOptionPane;

	public class Mensajes {
		public static void MostrarError(String msg) {
			JOptionPane.showMessageDialog(null, msg,"Operacion fallida", JOptionPane.ERROR_MESSAGE);
		}
		
		public static void MostrarExito(String msg) {
			JOptionPane.showMessageDialog(null, msg, "Operacion exitosa",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	


