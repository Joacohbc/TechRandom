package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.database.BDUtils;
import model.database.DatabaseManager;
import model.entity.Persona;
import validation.Formatos;

public class DAOPersona {

	private static final String FIND_ALL = "SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, to_char(fecha_nac, 'DD-MM-YYYY'), mail, id_rol FROM persona";
	private static final String FIND_BY_NOMBRE_APELLIDO = "SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, to_char(fecha_nac, 'DD-MM-YYYY'), mail, id_rol FROM persona WHERE nombre1=? AND apellido1=?";
	private static final String FIND_BY_DOCUMENTO = "SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, to_char(fecha_nac, 'DD-MM-YYYY'), mail, id_rol FROM persona WHERE documento=?";
	private static final String FIND_BY_EMAIL_AND_CLAVE = "SELECT id_persona, documento, apellido1, apellido2, nombre1, nombre2, to_char(fecha_nac, 'DD-MM-YYYY'), mail, id_rol, clave FROM persona WHERE mail = ? AND clave = ?";
	
	// Insert de todos los campos (Rol se inserta mediante su Nombre y no su ID)
	private static final String INSERT = "INSERT INTO persona values(null, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT id_rol FROM rol WHERE rol.nombre=?))";
	
	// Update de todos los campos, menos documento el cual es la condicion (Rol se inserta mediante su Nombre y no su ID)
	private static final String UPDATE = "UPDATE persona SET apellido1 = ?, apellido2 = ?, nombre1 = ?, nombre2 = ?, fecha_nac = ?, mail = ?, clave = ?, id_rol = (SELECT id_rol FROM rol WHERE rol.nombre=?) WHERE documento = ?";
	
	// Delete en base al documento
	private static final String DELETE = "DELETE FROM persona WHERE documento = ?";
	
	//
	// Consultas
	//

	public static List<Persona> findAll() throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_ALL);
			ResultSet rs = ps.executeQuery();

			List<Persona> personas = new LinkedList<Persona>();
			while (rs.next()) {
				personas.add(personaFromResultSet(rs));
			}

			return personas;

		} finally {
			BDUtils.Close(ps);
		}
	}

	public static List<Persona> findByNombreApellido(String nombre, String apellido) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_BY_NOMBRE_APELLIDO);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ResultSet rs = ps.executeQuery();

			List<Persona> personas = new LinkedList<>();
			while (rs.next()) {
				personas.add(personaFromResultSet(rs));
			}
			return personas;

		} finally {
			BDUtils.Close(ps);
		}
	}

	public static Persona findByDocumento(String documento) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_BY_DOCUMENTO);
			ps.setString(1, documento);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return personaFromResultSet(rs);
			} else {
				return null;
			}

		} finally {
			BDUtils.Close(ps);
		}
	}
	
	public static Persona findPersonAccount(String mail, String password) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(FIND_BY_EMAIL_AND_CLAVE);
			ps.setString(1, mail);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return personaFromResultSet(rs);
			} else {
				return null;
			}

		} finally {
			BDUtils.Close(ps);
		}
	}

	//
	// INSERTs, UPDATEs y DELETEs
	//

	public static int insert(Persona p) throws SQLException {

		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(INSERT);
			ps.setString(1, p.getDocumento());
			ps.setString(2, p.getApellido1());
			ps.setString(3, p.getApellido2());
			ps.setString(4, p.getNombre1());
			ps.setString(5, p.getNombre2());
			ps.setString(6, Formatos.ToFormatedString(p.getFechaNacimiento()));
			ps.setString(7, p.getMail());
			ps.setString(8, p.getClave());
			ps.setString(9, p.getRol().getNombre());

			return ps.executeUpdate();
		} finally {
			BDUtils.Close(ps);
		}
	}

	public static int update(String documento, Persona p) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(UPDATE);
			ps.setString(1, p.getApellido1());
			ps.setString(2, p.getApellido2());
			ps.setString(3, p.getNombre1());
			ps.setString(4, p.getNombre2());
			ps.setString(5, Formatos.ToFormatedString(p.getFechaNacimiento()));
			ps.setString(6, p.getMail());
			ps.setString(7, p.getClave());
			ps.setString(8, p.getRol().getNombre());
			ps.setString(9, documento);
			return ps.executeUpdate();

		} finally {
			BDUtils.Close(ps);
		}
	}
	
	public static int delete(String documento) throws SQLException {
		PreparedStatement ps = null;

		try {
			ps = DatabaseManager.getConnection().prepareStatement(DELETE);
			ps.setString(1, documento);
			return ps.executeUpdate();

		} finally {
			BDUtils.Close(ps);
		}
	}
	
	//
	// Otros metodos
	//

	// Retorna la persona desde un ResultSet, SIN LA CLAVE
	private static Persona personaFromResultSet(ResultSet rs) throws SQLException {
		Persona p = new Persona();
		p.setId(rs.getLong(1));
		p.setDocumento(rs.getString(2));
		p.setApellido1(rs.getString(3));
		p.setApellido2(rs.getString(4));
		p.setNombre1(rs.getString(5));
		p.setNombre2(rs.getString(6));
		p.setFechaNacimiento(Formatos.Parse(rs.getString(7)));
		p.setMail(rs.getString(8));
		p.setRol(DAORol.findById(rs.getLong(9)));
		return p;
	}

}
