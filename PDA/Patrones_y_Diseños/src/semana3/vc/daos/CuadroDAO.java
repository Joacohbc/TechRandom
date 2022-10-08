package semana3.vc.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import semana3.vc.models.Cuadro;
import semana3.vc.models.Museo;

public class CuadroDAO {

	// Obtiene todos los registros de Cuadros con Museo
	// No tiene parametros
	private static final String FIND_ALL_CUADROS_WITH_MUSEO = "SELECT A.ID_CUADRO, A.AUTOR, A.NOMBRE, B.NOMBRE as MUSEO, B.DIRECCION FROM cuadro A INNER JOIN Museo B ON A.id_museo = B.id_museo";

	// Obtiene el Cuadro en base a su nombre (es unico)
	// Parametros:
	// 1 - Nombre del cuadro
	private static final String FIND_BY_NOMBRE = "SELECT A.ID_CUADRO, A.AUTOR, A.NOMBRE, B.NOMBRE as MUSEO, B.DIRECCION FROM cuadro A INNER JOIN Museo B ON A.id_museo = B.id_museo WHERE A.NOMBRE=?";

	// Inserta un nuevo registro de un Cuadro. Necesita el Museo
	// Parametros:
	// 1 - Autor del cuadro
	// 2 - Nombre del cuadro
	// 3 - Nombre del museo al cual pertenece el cuadro
	private static final String INSERT_CUADRO = "INSERT INTO CUADRO (AUTOR,NOMBRE,ID_MUSEO) VALUES (?,?,(SELECT id_museo FROM museo WHERE nombre = ?))";

	// Modifica un Cuadro existente en base al nombre del cuadro  (es unico)
	// Parametros:
	// 1 - Nuevo autor del cuadro
	// 2 - Nuevo nombre del cuadro
	// 3 - Nuevo nombre del museo al cual pertence el cuadro
	// 4 - Nombre actual del cuadro
	private static final String UPDATE_CUADRO = "UPDATE CUADRO SET AUTOR=?, NOMBRE=?, ID_MUSEO=(SELECT id_museo FROM museo WHERE nombre = ?) WHERE ID_CUADRO = (SELECT id_cuadro FROM cuadro WHERE nombre = ?)";

	//
	// Consultas de Cuadros
	//

	// Retorna todo los Cuadros (con su museo)
	// Si no hay Museos creados, retorna una lista vacia
	public static List<Cuadro> findAllWithMuseo() throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_ALL_CUADROS_WITH_MUSEO);
		ResultSet resultado = statement.executeQuery();

		List<Cuadro> listCuadros = new LinkedList<>();

		while (resultado.next()) {

			// Atributos de cuadro
			Long idCuadro = resultado.getLong("ID_CUADRO");
			String autor = resultado.getString("AUTOR");
			String nombreCuadro = resultado.getString("NOMBRE");

			// Atributos de Museo
			String nombreMuseo = resultado.getString("MUSEO");
			String dir = resultado.getString("DIRECCION");

			// Objeto museo
			Museo museo = new Museo(null, nombreMuseo, dir);

			// Objecto cuadro
			Cuadro cuadro = new Cuadro(idCuadro, autor, nombreCuadro, museo);
			listCuadros.add(cuadro);
		}

		return listCuadros;
	}

	// Retorna el ResultSet de consultar todo los Cuadros con su Museo.
	public static ResultSet findAllResultSet() throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_ALL_CUADROS_WITH_MUSEO);
		return statement.executeQuery();
	}

	// Retorna el Cuadro que machee con ese nombre (con su museo)
	// Si no existe un Cuadro con ese nombre, retorna null
	public static Cuadro findByName(String nombre) throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(FIND_BY_NOMBRE);
		statement.setString(1, nombre);

		ResultSet resultado = statement.executeQuery();

		if (resultado.next()) {
			Long idCuadro = resultado.getLong("ID_CUADRO");
			String autor = resultado.getString("AUTOR");
			String nombreCuadro = resultado.getString("NOMBRE");

			// Atributos de Museo
			String nombreMuseo = resultado.getString("MUSEO");
			String dir = resultado.getString("DIRECCION");

			// Objeto museo
			Museo museo = new Museo(null, nombreMuseo, dir);

			// Objecto cuadro
			Cuadro cuadro = new Cuadro(idCuadro, autor, nombreCuadro, museo);
			return cuadro;
		} else {
			return null;
		}

	}

	//
	// Inserts, Updates y Deletes
	//


	// Realiza un Update del Nombre, Autor, y Museo del Cuadro
	// Retorna True si se actulizo algun registro y False si no se actulizo ningun registro
	public static boolean update(String nombre, Cuadro c) throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_CUADRO);
		statement.setString(1, c.getAutor());
		statement.setString(2, c.getNombre());
		statement.setString(3, c.getMuseo().getNombre());
		statement.setString(4, nombre);
		return statement.executeUpdate() > 0;

	}

	// Realiz un Insert de los campos del Cuadro con su Museo (debe tener si o si el nombre)
	// Retorna True si se insertor el registro y False si no se inserto ningun registro
	public static boolean insert(Cuadro c) throws SQLException {
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_CUADRO);
		statement.setString(1, c.getAutor());
		statement.setString(2, c.getNombre());
		statement.setString(3, c.getMuseo().getNombre());
		return statement.executeUpdate() > 0;
	}

}
