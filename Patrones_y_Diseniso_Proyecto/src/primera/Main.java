package primera;

import primera.basededatos.Conexion;

public class Main {
	public static void main(String[] args) {
		try {
			Conexion.getConnection().close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
