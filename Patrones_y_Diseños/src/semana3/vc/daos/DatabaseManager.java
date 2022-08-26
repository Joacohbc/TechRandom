package semana3.vc.daos;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	
	private static final String URL_ORACLE = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "UTEC";
	private static final String PASSWORD = "UTEC";

	private static Connection conn;

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
		} catch (Exception e) {
			System.err.println("Error al conectarse a la BD:");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}
