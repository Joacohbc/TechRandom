package view.rol;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import model.dao.DAORol;
import model.entity.Funcionalidad;
import model.entity.Rol;
import validation.Mensajes;

public class RolUtils {
	
	public static void cargarTodasLasFuncionalidades(JComboBox<Funcionalidad> cmb) {
		cmb.removeAllItems();
		for(Funcionalidad f : Funcionalidad.values())
			cmb.addItem(f);
	}
	
	public static void cargarFuncionalidadesDeRol(String rol, JComboBox<Funcionalidad> cmb) throws SQLException {
		List<Funcionalidad> funcionalidadesRol 	= DAORol.findByNombre(rol).getFuncionalidades();
		cmb.removeAllItems();
		for (Funcionalidad f : funcionalidadesRol) {
			cmb.addItem(f);
		}
	}
	
	public static void cargarRol(JComboBox<Rol> cmbRoles) throws SQLException {
		List<Rol> roles = DAORol.findAll();
		cmbRoles.removeAllItems();
		for (Rol r : roles) {
			cmbRoles.addItem(r);
		}
	}
	
	public static DefaultTableModel GetTableModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		Object[] titulos = { "Id Rol","Nombre", "Descripcion" };
		modelo.setColumnIdentifiers(titulos);
		return modelo;
	}
	
	public static DefaultTableModel GetTableModelFunc() {
		DefaultTableModel modelo = new DefaultTableModel();
		Object[] titulos = { "Id Rol","Nombre", "Descripcion", "Funcionalidades" };
		modelo.setColumnIdentifiers(titulos);
		return modelo;
	}
}


