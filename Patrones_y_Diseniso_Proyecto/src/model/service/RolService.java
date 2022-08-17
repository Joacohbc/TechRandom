package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.entity.Retorno;
import model.entity.Rol;

public class RolService {
	
	private static Connection conn = DatabaseManager.getConnection();

//    public static Retorno AltaRol(Rol r) {
//
//        PreparedStatement ps = null;
//        String sql = "INSERT INTO rol VALUES(null, ?, ?))";
//        try {
//        	ps = conn.prepareStatement(sql);
//        	ps.setString(1, r.getNombre());
//        	ps.setString(2, r.getDescripcion());
//        	
//            return new Retorno("Rol dado de alta con exito", null);
//        } catch (SQLException e) {
//            return new Retorno("no se pudo dar de alta el rol", e);
//        }finally {
//			BDUtils.Close(ps);
//		}
//    }
}
