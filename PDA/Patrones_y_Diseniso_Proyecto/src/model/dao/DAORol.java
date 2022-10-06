package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import model.database.BDUtils;
import model.database.DatabaseManager;
import model.entity.Funcionalidad;
import model.entity.Rol;

public class DAORol {

	private static final String INSERT = "INSERT INTO rol VALUES(NULL, ?, ?)";
	private static final String UPDATE = "UPDATE rol SET nombre = ?, descripcion = ? WHERE nombre = ?";
	private static final String DELETE = "DELETE FROM rol WHERE nombre = ?";
	private static final String FIND_ALL = "SELECT id_rol, nombre, descripcion FROM rol";
	private static final String FIND_BY_ID = "SELECT id_rol, nombre, descripcion FROM rol WHERE id_rol = ?";
	private static final String FIND_BY_NOMBRE = "SELECT id_rol, nombre, descripcion FROM rol WHERE nombre = ?";
	private static final String GET_FUNCIONALIDADES = " SELECT f.id_funcionalidad FROM rol r INNER JOIN rol_funcionalidad rf ON r.id_rol = rf.id_rol INNER JOIN funcionalidad f ON rf.id_funcionalidad = f.id_funcionalidad WHERE r.id_rol = ?";
	
	//
	// Consultas
	//
	
	public static Rol findById(Long id) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_BY_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return getRolFromResultSet(rs);
			}

			return null;

		} finally {
			BDUtils.Close(ps);
		}
	}

	public static Rol findByNombre(String nombre) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_BY_NOMBRE);
			ps.setString(1, nombre);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return getRolFromResultSet(rs);
			}

			return null;

		} finally {
			BDUtils.Close(ps);
		}
	}

	public static List<Rol> findAll() throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_ALL);
			ResultSet rs = ps.executeQuery();

			List<Rol> list = new ArrayList<Rol>();
			while (rs.next()) {
				list.add(getRolFromResultSet(rs));
			}

			return list;

		} finally {
			BDUtils.Close(ps);
		}
	}

	private static List<Funcionalidad> getFuncionalidades(Long id) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(GET_FUNCIONALIDADES);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			List<Funcionalidad> list = new LinkedList<>();
			while (rs.next()) {
				for (Funcionalidad f : Funcionalidad.values()) {
					if (f.getId() == rs.getInt(1)) {
						list.add(f);
					}
				}
			}

			return list;

		} finally {
			BDUtils.Close(ps);
		}
	}

	//
	// INSERTs, UPDATE y DELETEs
	//
	public static int insert(Rol r) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(INSERT);
			ps.setString(1, r.getNombre());
			ps.setString(2, r.getDescripcion());
			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}
	
	public static int update(String nombre, Rol r) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(UPDATE);
			ps.setString(1, r.getNombre());
			ps.setString(2, r.getDescripcion());
			ps.setString(3, nombre);
			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}
	
	public static int delete(String nombre) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(DELETE);
			ps.setString(1, nombre);
			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}
	
	//
	// Otros
	// 
	
	private static Rol getRolFromResultSet(ResultSet rs) throws SQLException {
		Rol r = new Rol();
		r.setId(rs.getLong(1));
		r.setNombre(rs.getNString(2));
		r.setDescripcion(rs.getString(3));
		r.setFuncionalidades(getFuncionalidades(rs.getLong(1)));
		return r;
	}
}
