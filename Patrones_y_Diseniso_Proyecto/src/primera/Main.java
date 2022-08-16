package primera;

import primera.basededatos.Conexion;

public class Main {
	public static void main(String[] args) {
		try {
			Conexion.getConnection().close();
			System.out.println("Conecatda");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
