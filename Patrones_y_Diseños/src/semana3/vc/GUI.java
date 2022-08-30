package semana3.vc;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import semana3.vc.daos.CuadroDAO;
import semana3.vc.daos.DatabaseManager;
import semana3.vc.daos.MuseoDAO;
import semana3.vc.models.Cuadro;
import semana3.vc.models.Museo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtAltaNombre;
	private JTextField txtAltaAutor;
	private JComboBox<String> cmbAltaMuseo;
	private JTextField txtModifAutor;
	private JTextField txtModifNombre;
	private JComboBox<String> cmbBuscarCuadro;
	private JButton btnCancelarModif;
	private JButton btnCargarCuadros;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setTitle("TechRandom - MMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pModificacion = new JPanel();
		pModificacion.setEnabled(false);
		pModificacion
				.setBorder(new TitledBorder(null, "Modificacion", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pModificacion.setBounds(32, 273, 307, 168);
		contentPane.add(pModificacion);
		pModificacion.setLayout(null);

		JComboBox<String> cmbModifMuseo = new JComboBox<String>();
		cmbModifMuseo.setEnabled(false);
		cmbModifMuseo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarMuseos(cmbModifMuseo);
			}
		});
		cmbModifMuseo.setBounds(83, 87, 196, 21);
		pModificacion.add(cmbModifMuseo);

		JLabel lblNewLabel_2_1 = new JLabel("Museo:");
		lblNewLabel_2_1.setBounds(12, 87, 60, 17);
		pModificacion.add(lblNewLabel_2_1);

		JLabel lblNewLabel_1_1 = new JLabel("Autor:");
		lblNewLabel_1_1.setBounds(12, 58, 69, 17);
		pModificacion.add(lblNewLabel_1_1);

		txtModifAutor = new JTextField();
		txtModifAutor.setEnabled(false);
		txtModifAutor.setColumns(10);
		txtModifAutor.setBounds(83, 56, 196, 21);
		pModificacion.add(txtModifAutor);

		txtModifNombre = new JTextField();
		txtModifNombre.setEnabled(false);
		txtModifNombre.setColumns(10);
		txtModifNombre.setBounds(83, 25, 196, 21);
		pModificacion.add(txtModifNombre);

		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setBounds(12, 27, 69, 17);
		pModificacion.add(lblNewLabel_3);

		//
		// Boton de Modificar Cuadro
		//
		JButton btnModif = new JButton("Modificar");
		btnModif.setEnabled(false);
		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cuadro c = new Cuadro();
				c.setNombre(txtModifNombre.getText());
				c.setAutor(txtModifAutor.getText());

				Museo m = new Museo();
				m.setNombre(String.valueOf(cmbModifMuseo.getSelectedItem()));
				c.setMuseo(m);

				try {
					if (CuadroDAO.update(String.valueOf(cmbBuscarCuadro.getSelectedItem()), c)) {
						JOptionPane.showMessageDialog(null, "Modificacion del Cuadro realizada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar la modificacion del Cuadro", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
					
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al modificar el el Cuadro", "Error con la BD",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
					return;
				}

				cargarCuadros(cmbBuscarCuadro);
				btnCancelarModif.doClick();
			}
		});
		btnModif.setBounds(11, 120, 119, 27);
		pModificacion.add(btnModif);

		//
		// Boton de Cancelar Modificacion
		//
		btnCancelarModif = new JButton("Cancelar");
		btnCancelarModif.setEnabled(false);
		btnCancelarModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Deshabilito todo los componentes del panel
				pModificacion.setEnabled(false);
				for (Component comp : pModificacion.getComponents()) {
					comp.setEnabled(false);
				}

				// Vacio los campos
				txtModifNombre.setText("");
				txtModifAutor.setText("");
				cmbModifMuseo.removeAllItems();

				// Y habilito nuevamente los campos
				// para volver a actualizar
				btnCargarCuadros.setEnabled(true);
				cmbBuscarCuadro.setEnabled(true);
			}
		});
		btnCancelarModif.setBounds(160, 120, 119, 27);
		pModificacion.add(btnCancelarModif);

		JPanel pAlta = new JPanel();
		pAlta.setBorder(new TitledBorder(null, "Alta", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		pAlta.setBounds(32, 12, 307, 176);
		contentPane.add(pAlta);
		pAlta.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(12, 26, 69, 17);
		pAlta.add(lblNewLabel);

		txtAltaNombre = new JTextField();
		txtAltaNombre.setBounds(83, 24, 196, 21);
		pAlta.add(txtAltaNombre);
		txtAltaNombre.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Autor:");
		lblNewLabel_1.setBounds(12, 57, 69, 17);
		pAlta.add(lblNewLabel_1);

		txtAltaAutor = new JTextField();
		txtAltaAutor.setColumns(10);
		txtAltaAutor.setBounds(83, 55, 196, 21);
		pAlta.add(txtAltaAutor);

		JLabel lblNewLabel_2 = new JLabel("Museo:");
		lblNewLabel_2.setBounds(12, 86, 60, 17);
		pAlta.add(lblNewLabel_2);

		cmbAltaMuseo = new JComboBox<String>();
		cmbAltaMuseo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarMuseos(cmbAltaMuseo);
			}
		});
		cmbAltaMuseo.setBounds(83, 86, 196, 21);
		pAlta.add(cmbAltaMuseo);

		///
		// Boton de Alta de Cuadro
		//
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nombre = txtAltaNombre.getText();
				String autor = txtAltaAutor.getText();
				String museo = String.valueOf(cmbAltaMuseo.getSelectedItem());

				Museo m = new Museo();
				m.setNombre(museo);

				Cuadro c = new Cuadro(null, autor, nombre, m);

				try {
					if (CuadroDAO.insert(c)) {
						JOptionPane.showMessageDialog(null, "Insercion del Cuadro realizada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar la insercion del Cuadro", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al insertar el Cuadro", "Error con la BD",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		btnAlta.setBounds(12, 125, 268, 27);
		pAlta.add(btnAlta);

		JButton btnConsultarMuseo = new JButton("Museos");
		btnConsultarMuseo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Realizo la consulta
					ResultSet rs = MuseoDAO.findAllResultSet();

					// Obtengo la meta data de la consulta
					ResultSetMetaData data = rs.getMetaData();

					// Creo un nuevo modelo (haciendolo no editable)
					DefaultTableModel model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false; // Hago todas las celdas no editables
						}
					};

					int colums = data.getColumnCount();

					// Agrego las columnas de la consulta en la JTable
					for (int i = 1; i <= colums; i++) {
						model.addColumn(data.getColumnName(i));
					}

					while (rs.next()) {
						List<Object> museo = new LinkedList<>();

						// Cargo los datos de cada fila en el lista
						for (int i = 1; i <= colums; i++) {
							museo.add(rs.getObject(i));
						}

						// Y la agrego lista a como fila en la JTable
						model.addRow(museo.toArray());
					}

					// Envuelvo la tabla en un JScrollPane
					JScrollPane panel = new JScrollPane(new JTable(model));

					// Y la muestro
					JOptionPane.showMessageDialog(null, panel, "Lista de personas", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al cargar los Cuadros de la Base de datos",
							"Error con la BD", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}

			}
		});
		btnConsultarMuseo.setBounds(32, 451, 142, 27);
		contentPane.add(btnConsultarMuseo);

		JButton btnCuadros = new JButton("Cuadros");
		btnCuadros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Realizo la consulta
					ResultSet rs = CuadroDAO.findAllResultSet();

					// Obtengo la meta data de la consulta
					ResultSetMetaData data = rs.getMetaData();

					// Creo un nuevo modelo (haciendolo no editable)
					DefaultTableModel model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false; // Hago todas las celdas no editables
						}
					};

					int colums = data.getColumnCount();

					// Agrego las columnas de la consulta en la JTable
					for (int i = 1; i <= colums; i++) {
						model.addColumn(data.getColumnName(i));
					}

					while (rs.next()) {
						List<Object> cuadro = new LinkedList<>();

						// Cargo los datos de cada fila en el lista
						for (int i = 1; i <= colums; i++) {
							cuadro.add(rs.getObject(i));
						}

						// Y la agrego lista a como fila en la JTable
						model.addRow(cuadro.toArray());
					}

					// Envuelvo la tabla en un JScrollPane
					JScrollPane panel = new JScrollPane(new JTable(model));

					// Y la muestro
					JOptionPane.showMessageDialog(null, panel, "Lista de personas", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al cargar los Museos de la Base de datos",
							"Error con la BD", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		btnCuadros.setBounds(197, 451, 142, 27);
		contentPane.add(btnCuadros);

		cmbBuscarCuadro = new JComboBox<String>();
		cmbBuscarCuadro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarCuadros(cmbBuscarCuadro);
			}
		});
		cmbBuscarCuadro.setBounds(104, 201, 235, 21);
		contentPane.add(cmbBuscarCuadro);

		JLabel lblNewLabel_2_1_1 = new JLabel("Cuadro:");
		lblNewLabel_2_1_1.setBounds(33, 201, 60, 17);
		contentPane.add(lblNewLabel_2_1_1);

		//
		// Boton de Carga Cuadros
		//
		btnCargarCuadros = new JButton("Cargar");
		btnCargarCuadros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Cuadro c = CuadroDAO.findByName(String.valueOf(cmbBuscarCuadro.getSelectedItem()));

					if (c == null) {
						JOptionPane.showMessageDialog(null, "No existe un cuadro con ese nombre", "Error de ingreso",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Habilito todos los campos de modificacion
					pModificacion.setEnabled(true);
					for (Component comp : pModificacion.getComponents()) {
						comp.setEnabled(true);
					}

					// Cargo los campos
					txtModifNombre.setText(c.getNombre());
					txtModifAutor.setText(c.getAutor());

					// Actualizo y cargo el Museo del cuadro en el ComboBox
					cargarMuseos(cmbModifMuseo);
					cmbModifMuseo.setSelectedItem(c.getMuseo().getNombre());

					// Deshabilito los campos para realizar actualizaciones
					btnCargarCuadros.setEnabled(false);
					cmbBuscarCuadro.setEnabled(false);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al cargar del Cuadro de la Base de datos",
							"Error con la BD", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}

			}
		});
		btnCargarCuadros.setBounds(32, 234, 306, 27);
		contentPane.add(btnCargarCuadros);

		// Para que este cargos los Museos y los Cuadros desde el inicio
		cargarCuadros(cmbBuscarCuadro);
		cargarMuseos(cmbAltaMuseo);
	}

	// Carga un ComboBox con el todos los nombres de los Museos
	private void cargarMuseos(JComboBox<String> cmbBox) {
		try {
			List<Museo> museos = MuseoDAO.findAll();

			// Elimino todos los elementos del ComboBox
			// (para no volver a agregar los que ya existen y repetirlos)
			cmbBox.removeAllItems();

			if (museos.isEmpty()) {
				cmbBox.addItem("No hay museos en la base de datos");
				return;
			}

			for (Museo museo : museos) {
				cmbBox.addItem(museo.getNombre());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar los Museos de la Base de datos", "Error con la BD",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	// Carga un ComboBox con el todos los nombres de los Cuadros
	private void cargarCuadros(JComboBox<String> cmbBox) {
		try {
			List<Cuadro> cuadros = CuadroDAO.findAllWithMuseo();

			// Elimino todos los elementos del ComboBox
			// (para no volver a agregar los que ya existen y repetirlos)
			cmbBox.removeAllItems();

			if (cuadros.isEmpty()) {
				cmbBox.addItem("No hay cuadros en la base de datos");
				return;
			}

			for (Cuadro cuadro : cuadros) {
				cmbBox.addItem(cuadro.getNombre());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar los Cuadros de la Base de datos", "Error con la BD",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
