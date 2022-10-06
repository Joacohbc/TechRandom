package view.persona;

import javax.swing.table.DefaultTableModel;

import model.entity.Persona;
import validation.Formatos;

public class PersonaUtils {

	public static DefaultTableModel GetTableModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		Object[] titulos = { "Id Persona", "Documento", "1er Apellido", "2do Apellido", "1er Nombre", "2do Nombre",
				"Nacimiento", "Email", "Rol" };
		modelo.setColumnIdentifiers(titulos);
		return modelo;
	}

	public static Object[] ToTableRow(Persona p) {
		return new Object[] { p.getId(), p.getDocumento(), p.getApellido1(), p.getApellido2(), p.getNombre1(),
				p.getNombre2(), Formatos.ToFormatedString(p.getFechaNacimiento()), p.getMail(),
				p.getRol().getNombre() };
	}
}
