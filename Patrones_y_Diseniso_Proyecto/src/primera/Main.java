package primera;

import java.time.LocalDate;

import primera.basededatos.Conexion;
import primera.logica.PersonaUtils;

public class Main {
	public static void main(String[] args) {
		try {
			Conexion.getConnection().close();
			System.out.println("Conectada");
			Persona p = new Persona();
			p.setDocumento("87654321");
			p.setApellido1("Soberal");
			p.setNombre1("Santiago");
			p.setMail("ss@gmail.com");
			p.setFechaNacimiento(LocalDate.of(1997, 01, 01));
			p.setClave("String");
			p.setRol(new Rol("Administrador", "", null));
			System.out.println(PersonaUtils.AltaPersona(p));
		
	
		} catch (Exception e) {

		}
	}
}
