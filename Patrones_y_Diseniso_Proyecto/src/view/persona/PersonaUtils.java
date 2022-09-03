package view.persona;

import javax.swing.table.DefaultTableModel;

import validation.Formatos;

public class PersonaUtils {

	public static DefaultTableModel GetTableModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		Object[] titulos = { "Id Persona", "1er Apellido", "2do Apellido", "1er Nombre", "2do Nombre", "Nacimineto",
				"Email", "Rol" };
		modelo.setColumnIdentifiers(titulos);
		return modelo;
	}
}
