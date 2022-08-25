package semana3.grupal.code;

import java.sql.Connection;
import java.sql.DriverManager;;

public class BDManager {

	private static final String URL_ORACLE = "jdbc:oracle:thin:@192.168.0.250:1521:xe";
	private static final String USER = "utec";
	private static final String PASSWORD = "utec";

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
