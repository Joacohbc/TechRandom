package model.dao;

import java.sql.PreparedStatement;

import model.database.DatabaseManager;
import model.entity.Funcionalidad;

public class DAOFuncionalidad {

	public static void insert(Funcionalidad f) {
		try {
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO funcionalidad VALUES(?,?,? )");
			ps.setInt(1, f.getId());
			ps.setString(2, f.getNombre());
			ps.setString(3, f.getDescripcion());
			ps.executeUpdate();
			ps.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
