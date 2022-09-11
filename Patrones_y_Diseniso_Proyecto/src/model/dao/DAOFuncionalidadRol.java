package model.dao;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.database.SQLExecuter;
import model.entity.Funcionalidad;
import model.entity.Rol;
import oracle.sql.converter.JdbcCharacterConverters;

public class DAOFuncionalidadRol {

	private static final String INSERT = "insert into rol_funcionalidad values (NULL, (SELECT id_rol FROM rol WHERE nombre = ?), (SELECT id_funcionalidad FROM funcionalidad WHERE nombre = ?))";
	private static final String DELETE = "DELETE FROM rol_funcionalidad WHERE id_rol = (SELECT id_rol FROM rol WHERE nombre = ?) AND id_funcionalidad = (SELECT id_funcionalidad FROM funcionalidad WHERE nombre = ?)";

	public static int insertByName(String rol, Funcionalidad f) throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(INSERT);
			ps.setString(1, rol);
			ps.setString(2, f.getNombre());
			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}
	
	public static int deleteByName(String rol, Funcionalidad f)throws SQLException {
		PreparedStatement ps = null;
		try {
			ps = DatabaseManager.getConnection().prepareStatement(DELETE);
			ps.setString(1, rol);
			ps.setString(2, f.getNombre());
			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}
}
