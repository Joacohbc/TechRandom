package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	
	private static final String URL_ORACLE = "jdbc:oracle:thin:@192.168.0.250:1521:xe";
	private static final String USER = "utec";
	private static final String PASSWORD = "utec";

	// Mostrar mensajes de debug (predeterminadamente es false)
	public static boolean ShowErrors = false;

	// Un bloque estatico que donde se ejecuta el codigo estaticamente
	private static Connection conn;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);

		} catch (ClassNotFoundException e) {
			System.err.println("ERROR: No se pudo encontrar el Driver para la conexion");
			e.printStackTrace();
			System.exit(1);

		} catch (SQLException e) {
			System.err.println("ERROR: No se pudo realizar la conexion correctamente");
			e.printStackTrace();
			System.exit(1);
		}
	}

	// Metodo para obtener la conexion
	public static Connection getConnection() {
		return conn;
	}
}
