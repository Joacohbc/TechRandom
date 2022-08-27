package semana3.grupal.code;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import java.awt.*;
import java.util.LinkedList;

// Clase que muestra el contenido de una tabla de la BD utilizando una tabla
public class MostrarTodo extends JFrame {

	// Aplicamos modelo Singleton que no se pueda crear vas de un
	// JFrame que muestra las personas.
	private static final MostrarTodo intance = new MostrarTodo();

	public static MostrarTodo getIntances() {
		return intance;
	}

	// Tabla que tendra los datos
	private final JTable table;

	// Nombre de las columnas
	private final String[] columnNames = { "Cedula", "Nombre", "Apellido" };

	// Creo el JFrame
	private MostrarTodo() {
		// Agrego Layout y agrego la tabla
		table = new JTable(newModel());

		// Se define el tamaño de la tabla
		table.setPreferredScrollableViewportSize(new Dimension(600, 100));

		// Y creo el JFrame
		setTitle("Mostrar Todo");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(new JScrollPane(table));
		pack();
	}

	private DefaultTableModel newModel() {
		DefaultTableModel modelo = new DefaultTableModel();
		// Insertamos las cabezeras de cada columna
		for (int column = 0; column < columnNames.length; column++) {
			// Agrego las columnas a la tabla
			modelo.addColumn(columnNames[column]);
		}
		return modelo;
	}

	public void mostrar() {
		DefaultTableModel modelo = newModel();

		// Obtengo al lista de de Empleados y la cargo en la BD
		LinkedList<Empleado> todosEmpleados = DAOEmpleados.findAll();
		for (Empleado empleado : todosEmpleados) {
			Object[] fila = new Object[columnNames.length];
			fila[0] = empleado.getCedula();
			fila[1] = empleado.getNombre();
			fila[2] = empleado.getApellido();
			modelo.addRow(fila);
		}

		// Le asigno el modelo que cree
		table.setModel(modelo);
		setVisible(true);
	}

}
