package semana3.vc.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import semana3.vc.models.Museo;
import java.sql.ResultSet;

public class MuseoDAO {

	// Obtiene todos los registros de los Museo
	// No tiene parametros
	private static final String FIND_ALL_MUSEO = "SELECT * FROM MUSEO";

	// Obtiene el Museo en base a su nombre (es unico)
	// Parametros:
	// 1 - Nombre del museo
	private static final String FIND_BY_NOMBRE = "SELECT * FROM MUSEO WHERE NOMBRE=?";

	// Inserta un nuevo registro de un Museo
	// Parametros:
	// 1 - Nombre del museo
	// 2 - Direccion del museo
	private static final String INSERT_MUSEO = "INSERT INTO MUSEO (NOMBRE,DIRECCION) VALUES (?,?)";

	//
	// Consultas
	//

	// Retorna todo los Museos
	// Si no hay Museos creados, retorna una lista vacia
	public static List<Museo> findAll() throws SQLException {
		List<Museo> lista = new ArrayList<Museo>();
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_ALL_MUSEO);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			long id = rs.getLong("ID_MUSEO");
			String nombre = rs.getString("NOMBRE");
			String dir = rs.getString("DIRECCION");
			Museo m = new Museo(id, nombre, dir);
			lista.add(m);
		}

		return lista;
	}

	// Retorna el ResultSet de consultar todo los Museos
	public static ResultSet findAllResultSet() throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_ALL_MUSEO);
		return statement.executeQuery();
	}

	// Retorna el Museo que machee con ese nombre
	// Si no existe un Museo con ese nombre, retorna null
	public static Museo findByName(String museo) throws SQLException {

		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_BY_NOMBRE);
		statement.setString(1, museo);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			long id = rs.getLong("ID_MUSEO");
			String nombre = rs.getString("NOMBRE");
			String dir = rs.getString("DIRECCION");
			Museo m = m = new Museo(id, nombre, dir);
			return m;
		} else {
			return null;
		}
	}

	//
	// Inserts, Updates y Deletes
	//

	// Realiz un Insert de los campos del Museo
	// Retorna True si se insertor el registro y False si no se inserto ningun
	// registro
	public static boolean insert(Museo museo) throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_MUSEO);
		statement.setString(1, museo.getNombre());
		statement.setString(2, museo.getDireccion());
		return statement.executeUpdate() > 0;
	}

}
