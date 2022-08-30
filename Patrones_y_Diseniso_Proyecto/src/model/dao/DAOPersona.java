package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.database.SQLExecuter;
import model.entity.Persona;

public class DAOPersona {

	private Connection conn = DatabaseManager.getConnection();

	public int InsetarPersonaOption1(Persona p) throws SQLException {
		return SQLExecuter.make(
				"INSERT INTO persona values(null, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id_rol FROM rol WHERE rol.nombre=?))")
				.add(p.getDocumento())
				.add(p.getApellido1())
				.add(p.getApellido2())
				.add(p.getNombre1())
				.add(p.getNombre2())
				.add(p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
				.add(p.getMail())
				.add(p.getClave())
				.add(p.getRol().getNombre())
				.executeUpdate();
	}

	public int InsetarPersonaOption2(Persona p) {
		PreparedStatement ps = null;
		String sql = "INSERT INTO persona values(null, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id_rol FROM rol WHERE rol.nombre=?))";

		try {
			return BDUtils.Excute(sql, p.getDocumento(), p.getApellido1(), p.getApellido2(), p.getNombre1(),
					p.getNombre2(), p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy")), p.getMail(),
					p.getClave(), p.getRol().getNombre());

		} catch (SQLException e) {
			return -1;
		} finally {
			BDUtils.Close(ps);
		}
	}

	public int InsetarPersonaOption3(Persona p) {

		PreparedStatement ps = null;
		String sql = "INSERT INTO persona values(null, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id_rol FROM rol WHERE rol.nombre=?))";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getDocumento());
			ps.setString(2, p.getApellido1());
			ps.setString(3, p.getApellido2());
			ps.setString(4, p.getNombre1());
			ps.setString(5, p.getNombre2());
			ps.setString(6, p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
			ps.setString(7, p.getMail());
			ps.setString(8, p.getClave());
			ps.setString(9, p.getRol().getNombre());

			return ps.executeUpdate();

		} catch (SQLException e) {
			return -1;
		} finally {
			BDUtils.Close(ps);
		}
	}

}
