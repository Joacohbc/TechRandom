package model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.entity.Funcionalidad;
import model.entity.Retorno;

public class FuncionalidadService {
    
	private static Connection conn = DatabaseManager.getConnection();

//    public static Retorno AltaDescripcion(Funcionalidad f) {
//      
//        PreparedStatement ps = null;
//        String sql = "INSERT INTO funcionalidad VALUES(null, ?, ?))";
//        try {
//        	ps = conn.prepareStatement(sql);
//        	ps.setString(1, f.getNombre());
//        	ps.setString(2, f.getDescripcion());
//        	
//            return new Retorno("Funcionalidad dado de alta con exito", null);
//        } catch (SQLException e) {
//            return new Retorno("no se pudo dar de alta la Funcionalidad", e);
//        }finally {
//			BDUtils.Close(ps);
//		}
//    }

}
