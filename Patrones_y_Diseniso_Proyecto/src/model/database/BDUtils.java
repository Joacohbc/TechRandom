package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import oracle.jdbc.internal.OracleResultSetMetaData;

public class BDUtils {

	// Mostrar mensajes de debug (predeterminadamente es false)
	public static boolean ShowErrors = false;

	private static Connection conn = DatabaseManager.getConnection();

	// Metodo para cerrar la Conexion,(para hacer mas clean el Finally)
	public static void Close(Object obj) {
		try {
			if (obj == null) {
				return;
			}

			if (obj instanceof Connection) {
				Connection conn = ((Connection) obj);
				if (!conn.isClosed())
					conn.close();
			} else if (obj instanceof PreparedStatement) {
				PreparedStatement ps = ((PreparedStatement) obj);
				if (!ps.isClosed())
					ps.close();
			} else if (obj instanceof ResultSet) {
				ResultSet rs = ((ResultSet) obj);
				if (!rs.isClosed())
					rs.close();
			} else if (obj instanceof Statement) {
				Statement s = ((Statement) obj);
				if (!s.isClosed())
					s.close();
			}

		} catch (Exception e) {
			if (ShowErrors) {
				System.out.println("> Error: al cerrar el objeto");
				e.printStackTrace();
			}
		}
	}
	
	// Ejecuta una sentencia INSERT, UPDATE o DELETE y retorna el numero de filas afectadas
	public static int Excute(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}
			return ps.executeUpdate();
		} finally {
			Close(ps);
		}
	}
	
	// Ejecuta una sentencia SELECT y retorna todos los resultados
	public static List<Map<String, Object>> QueryMap(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;

		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}

			// Obtengo los resutlados
			rs = ps.executeQuery();

			// Obtengo la meta data (nombre de la columna) de los resutaldos
			metaData = rs.getMetaData();
			
			// Esta Lista de Lista represeta una Matriz (que es basicamente una tabla)
			List<Map<String, Object>> results = new LinkedList<Map<String, Object>>();

			// La cantidad de campos pedidos en la sentencia
			int countOfColums = metaData.getColumnCount();
			while (rs.next()) {
				
				// Creo un Map que guardara los valores de la colum
				Map<String, Object> colums = new HashMap<>();

				for (int i = 1; i <= countOfColums; i++) {
					colums.put(metaData.getColumnName(i).toLowerCase(), rs.getObject(i));
				}

				results.add(colums);
			}

			return results;
		} finally {
			Close(ps);
			Close(rs);
		}
	}

	// Ejecuta una sentencia SELECT y retorna todos los resultados
	public static List<List<Object>> QueryList(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}

			// Obtengo los resutlados
			rs = ps.executeQuery();
			metaData = rs.getMetaData();
			
			// Esta Lista de Map represeta la tabla
			// -> La Lista por la cantidad de filas que se piden
			// -> El Map por la cantidad columnas (campos) de cada una de las fila
			List<List<Object>> results = new LinkedList<List<Object>>();
			
			// La cantidad de campos pedidos en la sentencia
			int countOfColums = metaData.getColumnCount();
			while (rs.next()) {

				// Creo una Lista que guardara los valores de la columnas
				List<Object> colums = new ArrayList<>();

				for (int i = 1; i <= countOfColums; i++) {
					colums.add(rs.getObject(i));
				}

				results.add(colums);
			}

			return results;
		} finally {
			Close(ps);
			Close(rs);
		}
	}

	
	// Ejecuta una sentencia SELECT y retorna solo el primer resultado
	public static Map<String, Object> QueryOneMap(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;

		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}

			// Obtengo los resutlados
			rs = ps.executeQuery();

			// Obtengo la meta data (nombre de la columna) de los resutaldos
			metaData = rs.getMetaData();

			// Creo un Map que guardara los valores de la primera fila
			Map<String, Object> colums = new HashMap<>();
			
			// La cantidad de campos pedidos en la sentencia
			int countOfRows = metaData.getColumnCount();
			if (rs.next()) {
				for (int i = 1; i <= countOfRows; i++) {
					colums.put(metaData.getColumnName(i).toLowerCase(), rs.getObject(i));
				}
			}

			return colums;
		} finally {
			Close(ps);
			Close(rs);
		}
	}

	// Ejecuta una sentencia SELECT y retorna solo el primer resultado
	public static List<Object> QueryOneList(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}

			// Obtengo los resutlados
			rs = ps.executeQuery();
			metaData = rs.getMetaData();
			
			// Creo una Lista que guardara la primera fila
			List<Object> row = new ArrayList<>();
			
			// La cantidad de campos pedidos en la sentencia
			int countOfrows = metaData.getColumnCount();
			if (rs.next()) {
				for (int i = 1; i <= countOfrows; i++) {
					row.add(rs.getObject(i));
				}
			}
			return row;
		} finally {
			Close(ps);
			Close(rs);
		}
	}

	// Ejecuta una sentencia SELECT y retorna true si hubo algun resultado (o false)
	public static boolean QueryExists(String sql, Object... values) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 1; i <= values.length; i++) {
				ps.setObject(1, values[i - 1]);
			}
			rs = ps.executeQuery();
			return rs.next();
		} finally {
			Close(ps);
			Close(rs);
		}
	}

}
