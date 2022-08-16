package primera.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL_ORACLE = "jdbc:oracle:thin:@10.211.4.28:1521:xe";
	private static final String USER = "developer";
	private static final String PASSWORD = "developer";

	// Usaba esto para hacer debug de los errores (por eso esta en false)
	public static boolean ShowErrors = false;

	// Metodo para obtener la conexion
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Verifico qu este el driver la conexion a Oracle
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(URL_ORACLE, USER, PASSWORD);
	}
}
