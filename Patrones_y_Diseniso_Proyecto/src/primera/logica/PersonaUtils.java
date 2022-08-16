package primera.logica;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import primera.Persona;
import primera.Retorno;
import primera.basededatos.BDUtils;

public class PersonaUtils {

	public static Retorno AltaPersona(Persona p) {

		Object[] values = new Object[9];
		values[0] = p.getDocumento();
		values[1] = p.getApellido1();
		values[2] = p.getApellido2();
		values[3] = p.getNombre1();
		values[4] = p.getNombre2();
		values[5] = p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		values[6] = p.getMail();
		values[7] = p.getClave();
		values[8] = p.getRol().getNombre();

		try {
			BDUtils.ExecuteDML(
					"insert into persona values(null,?, ?, ?, ?, ?, ?, ?, ?, (select id_rol from rol where rol.nombre=?))",
					values);
			return new Retorno("Persona dada de alta con exito", null);
		} catch (ClassNotFoundException e) {
			return new Retorno("no se pudo acceder a los driver de conexion", e);
		} catch (SQLException e) {
			return new Retorno("no se pudo dar de alta a la persona", e);
		}
	}
	
	public static Retorno CambioPersona(Persona p) {
	
		Object[] values = new Object[9];
		values[0] = p.getDocumento();
		values[1] = p.getApellido1();
		values[2] = p.getApellido2();
		values[3] = p.getNombre1();
		values[4] = p.getNombre2();
		values[5] = p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yy"));
		values[6] = p.getMail();
		values[7] = p.getClave();
		values[8] = p.getRol().getNombre();
		
		try {
			BDUtils.ExecuteDML(
					"Update persona SET(null,?, ?, ?, ?, ?, ?, ?, ?, ? where (select id_persona from rol where rol.nombre= ?))",
					values);
			return new Retorno("Persona dada de alta con exito", null);
		} catch (ClassNotFoundException e) {
			return new Retorno("no se pudo acceder a los driver de conexion", e);
		} catch (SQLException e) {
			return new Retorno("no se pudo dar de alta a la persona", e);
		}
		
		
		
		
	}
	
	
	
}


