package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.entity.Persona;
import model.entity.Retorno;

public class PersonaService {

	private Connection conn = DatabaseManager.getConnection();
	
//	public Persona InsetarPersona(Persona p) {
//		
//		PreparedStatement ps = null;
//		String sql = "INSERT INTO persona values(null, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id_rol FROM rol WHERE rol.nombre=?))";
//		
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, p.getDocumento());
//			ps.setString(2, p.getApellido1());
//			ps.setString(3, p.getApellido2());
//			ps.setString(4, p.getNombre1());
//			ps.setString(5, p.getNombre2());
//			ps.setString(6, p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
//			ps.setString(7, p.getMail());
//			ps.setString(8,	p.getClave());
//			ps.setString(9, p.getRol().getNombre());
//			ps.executeLargeUpdate();
//			
//			return new Retorno("Persona dada de alta con exito", null);
//		} catch (SQLException e) {
//			return new Retorno("no se pudo dar de alta a la persona", e);
//		}finally {
//			BDUtils.Close(ps);
//		}
//	}	

}
