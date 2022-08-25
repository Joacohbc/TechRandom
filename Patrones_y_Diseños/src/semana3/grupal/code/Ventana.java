package semana3.grupal.code;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Ventana {

	private JFrame frame;
	private JTextField textCedula;
	private JTextField textApellido;
	private JTextField textNombre;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JButton btnMostrarTodo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textCedula = new JTextField();
		textCedula.setBounds(132, 36, 86, 20);
		frame.getContentPane().add(textCedula);
		textCedula.setColumns(10);

		textApellido = new JTextField();
		textApellido.setBounds(132, 114, 86, 20);
		frame.getContentPane().add(textApellido);
		textApellido.setColumns(10);

		textNombre = new JTextField();
		textNombre.setBounds(132, 69, 86, 20);
		frame.getContentPane().add(textNombre);
		textNombre.setColumns(10);

		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!checkNoVacio()) {
					return;
				}

				if (empleadoExite()) {
					JOptionPane.showMessageDialog(null, "Ya existe un empleado con la cedula: " + textCedula.getText(),
							"Fallo", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Empleado emp = new Empleado();
				emp.setCedula(toTitle(textCedula));
				emp.setNombre(toTitle(textNombre));
				emp.setApellido(toTitle(textApellido));

				if (DAOEmpleados.insert(emp)) {
					JOptionPane.showMessageDialog(null, "Empelado dado de alta con exito", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo dar de alta al empleado", "Fallo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAlta.setBounds(254, 35, 89, 23);
		frame.getContentPane().add(btnAlta);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkNoVacioCedula()) {
					return;
				}

				if (!empleadoExite()) {
					JOptionPane.showMessageDialog(null, "No existe un empleado con la cedula: " + textCedula.getText(),
							"Fallo", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Empleado emp = new Empleado();
				emp.setCedula(toTitle(textCedula));
				emp.setNombre(toTitle(textNombre));
				emp.setApellido(toTitle(textApellido));

				if (DAOEmpleados.delete(emp)) {
					JOptionPane.showMessageDialog(null, "Empleado bborrado con exito", "Exito",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No se pude borrar al empleado", "Fallo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(254, 83, 89, 23);
		frame.getContentPane().add(btnEliminar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkNoVacioCedula())
					return;

				Empleado emp = DAOEmpleados.find(textCedula.getText());
				if (emp == null) {
					JOptionPane.showMessageDialog(null,
							"No se encontrar una empleado con la cedula: " + textCedula.getText(), "Fallo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				textNombre.setText(emp.getNombre());
				textApellido.setText(emp.getApellido());
			}
		});
		btnBuscar.setBounds(254, 128, 89, 23);
		frame.getContentPane().add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!checkNoVacioCedula())
					return;

				if (!empleadoExite()) {
					JOptionPane.showMessageDialog(null,
							"No se encontrar una empleado con la cedula: " + textCedula.getText(), "Fallo",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Empleado emp = new Empleado();
				emp.setCedula(toTitle(textCedula));
				emp.setNombre(toTitle(textNombre));
				emp.setApellido(toTitle(textApellido));
				DAOEmpleados.update(emp);
			}
		});
		btnModificar.setBounds(254, 176, 89, 23);
		frame.getContentPane().add(btnModificar);

		btnMostrarTodo = new JButton("Mostrar Todo");
		btnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarTodo.getIntances().mostrar();
			}
		});
		btnMostrarTodo.setBounds(61, 176, 157, 23);
		frame.getContentPane().add(btnMostrarTodo);

		JLabel lblCedula = new JLabel("cedula");
		lblCedula.setBounds(48, 39, 46, 14);
		frame.getContentPane().add(lblCedula);

		JLabel lblNombre = new JLabel("nombre");
		lblNombre.setBounds(48, 72, 46, 14);
		frame.getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(48, 117, 46, 14);
		frame.getContentPane().add(lblApellido);
	}

	private boolean checkNoVacioCedula() {
		if (textCedula.getText().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo de la cedula no puede estar vacio", "Error de entrada",
					JOptionPane.ERROR_MESSAGE);
			textCedula.grabFocus();
			return false;
		}
		return true;
	}

	// Checkea que los JTextbox no esten vacios (muestra un mensaje de error si lo
	// estan)
	private boolean checkNoVacio() {
		if (!checkNoVacioCedula())
			return false;

		if (textNombre.getText().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo del nombre no puede estar vacio", "Error de entrada",
					JOptionPane.ERROR_MESSAGE);
			textNombre.grabFocus();
			return false;
		}

		if (textApellido.getText().trim().isBlank()) {
			JOptionPane.showMessageDialog(null, "El campo del apellido no puede estar vacio", "Error de entrada",
					JOptionPane.ERROR_MESSAGE);
			textApellido.grabFocus();
			return false;
		}

		return true;
	}

	// Retorna el texto del JTextField con formato de titulo (mayuscula en cada
	// palabra)
	private String toTitle(JTextField txt) {
		String text = txt.getText().trim().toUpperCase();
		if (text.length() <= 1) {
			return text;
		}
		return text.charAt(0) +text.substring(1).toLowerCase();
	}

	// Busca un empleado por la cedula (textCedula.getText()) y
	// retorna True si lo encuentra y False si no
	private boolean empleadoExite() {
		return DAOEmpleados.find(textCedula.getText()) != null;
	}
}
