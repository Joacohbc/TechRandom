package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;

import beans.BeanIntances;
import components.Roles;
import viewsAnalista.ViewPerfil;

public class ViewAnalista extends JFrame {

	private JPanel contentPane;
	private ArrayList<Usuario> usuarios;

	/*
	 * Se utiliza una variable de tipo HashMap para gestionar los filtros que aplica
	 * el usuario El HashMap permite utilizar pares de datos <Key,Value> de esta
	 * manera cada vez que el usuario actualice los valores de los filtros, al tener
	 * el mismo Key se reemplaza el Value
	 */
	private Map filtros;
	private JTable tblUsuarios;
	private JSpinner spGeneracion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ViewAnalista frame = new ViewAnalista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewAnalista() {
		usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);

		filtros = new HashMap();
		setTitle("Listado de Usuarios");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {

					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);

					if (usu != null && usu.getEstadoUsuario() == EstadoUsuario.SIN_VALIDAR
							|| usu.getEstadoUsuario() == EstadoUsuario.ELIMINADO) {
						usu.setEstadoUsuario(EstadoUsuario.VALIDADO);
						BeanIntances.user().updateEstadoUsuario(id, usu.getEstadoUsuario());

						usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
						filtrarListaUsuarios(tblUsuarios, filtros);

					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
				}
			}
		});
		btnActivar.setBounds(12, 335, 105, 27);
		panel.add(btnActivar);

		spGeneracion = new JSpinner();
		spGeneracion.setModel(new SpinnerNumberModel(Integer.valueOf(1000), Integer.valueOf(1000), Integer.valueOf(9999), Integer.valueOf(1)));
		spGeneracion.setBounds(97, 125, 168, 22);
		spGeneracion.addChangeListener(new ChangeListener() {	
			@Override
			public void stateChanged(ChangeEvent e) {
				filtros.put("GENERACION", (Integer) spGeneracion.getValue());
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		panel.add(spGeneracion);
		
		JComboBox<Roles> comboTipoUsuario = new JComboBox();
		// Se utiliza el evento action performed para capturar cada vez que se cambia el
		// valor del comboBox
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoUsuario.getSelectedItem().toString();

				switch (tipo) {
				case "ESTUDIANTE": {
					filtros.put("TIPO", "Estudiante");
					spGeneracion.setEnabled(true);
					break;
				}
				case "ANALISTA": {
					filtros.put("TIPO", "Analista");
					spGeneracion.setEnabled(false);
					break;
				}
				case "TUTOR": {
					filtros.put("TIPO", "Tutor");
					spGeneracion.setEnabled(false);
					break;
				}

				}
				// se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboTipoUsuario.setBounds(97, 13, 168, 26);
		panel.add(comboTipoUsuario);

		JComboBox comboITR = new JComboBox();
		comboITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ITR", comboITR.getSelectedItem());

				// se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboITR.setBounds(97, 50, 168, 26);
		panel.add(comboITR);

		JComboBox<EstadoUsuario> comboEstado = new JComboBox();
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ESTADO", comboEstado.getSelectedItem().toString());
				filtrarListaUsuarios(tblUsuarios, filtros);
			}
		});
		comboEstado.setBounds(97, 87, 168, 26);
		panel.add(comboEstado);

		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setBounds(12, 89, 60, 17);
		panel.add(lblNewLabel_1);

		JLabel lblGeneracion = new JLabel("Generación");
		lblGeneracion.setBounds(12, 127, 82, 17);
		panel.add(lblGeneracion);
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(12, 13, 60, 17);
		panel.add(lblNewLabel_3);

		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);

					if (usu != null && usu.getEstadoUsuario() == EstadoUsuario.VALIDADO) {
						usu.setEstadoUsuario(EstadoUsuario.ELIMINADO);
						BeanIntances.user().updateEstadoUsuario(id, usu.getEstadoUsuario());

						usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
						filtrarListaUsuarios(tblUsuarios, filtros);

					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
				}
			}
		});
		btnDesactivar.setBounds(224, 335, 105, 27);
		panel.add(btnDesactivar);

		JButton btnAbrirUsuario = new JButton("Ver detalles");
		btnAbrirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tblUsuarios.getSelectedRow();
				try {
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					if (usu != null) {
						ViewPerfil view = new ViewPerfil(usu);
						JFrame frame = new JFrame();
						frame.setContentPane(view);
						frame.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario primero");
					ex.printStackTrace();
				}
			}
		});
		btnAbrirUsuario.setBounds(436, 335, 105, 27);
		panel.add(btnAbrirUsuario);

		JLabel lblNewLabel_4 = new JLabel("Generación");
		lblNewLabel_4.setBounds(12, 127, 96, 17);
		panel.add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 158, 520, 151);
		panel.add(scrollPane);

		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);
		String columns[] = { "id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		tblUsuarios.setModel(modeloJTable);

		// Cargo el listado de usuario a la tabla
		cargarUsuarios(tblUsuarios, usuarios);

		// cargo los combos con los valores para poder hacer los filtros
		this.cargarCombosFiltros(comboEstado, comboTipoUsuario, comboITR);

		JLabel lblNewLabel = new JLabel("ITR");
		lblNewLabel.setBounds(12, 55, 60, 17);
		panel.add(lblNewLabel);
		


	}

	public void cargarUsuarios(JTable lstUsuarios, ArrayList<Usuario> listaUsuarios) {
		String columns[] = { "id", "Documento", "Nombres", "Apellidos", "ITR", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if (lstUsuarios != null) {
			for (Usuario usu : listaUsuarios) {
				Long id = usu.getIdUsuario();
				String doc = usu.getDocumento();
				String nombres = usu.getNombres();
				String apellidos = usu.getApellidos();
				String idITR = usu.getItr().getNombre();
				String tipo = usu.getEstadoUsuario().toString();
				Object[] datos = { id, doc, nombres, apellidos, idITR, tipo };
				modeloJTable.addRow(datos);
			}
		}
		tblUsuarios.setModel(modeloJTable);
	}

	// Método para cargar los valores que contienen los filtros
	public void cargarCombosFiltros(JComboBox comboEstado, JComboBox comboTipoUsuario, JComboBox comboITR) {

		/*
		 * esto queda hardcoded pero falta un método que traiga ITR
		 * 
		 */
		comboEstado.addItem(EstadoUsuario.VALIDADO);
		comboEstado.addItem(EstadoUsuario.ELIMINADO);
		comboEstado.addItem(EstadoUsuario.SIN_VALIDAR);

		comboTipoUsuario.addItem(Roles.ANALISTA);
		comboTipoUsuario.addItem(Roles.ESTUDIANTE);
		comboTipoUsuario.addItem(Roles.TUTOR);

		List<Itr> itrs = BeanIntances.itr().findAll();
		for (Itr itr : itrs) {
			if (itr.getEstado())
				comboITR.addItem(itr);
		}
	}

	public void filtrarListaUsuarios(JTable lstUsuarios, Map filtros) {

		ArrayList<Usuario> filtrados = new ArrayList<Usuario>();
		if (usuarios != null && !filtros.isEmpty() && filtros.get("ITR") != null) {

			for (Usuario usu : usuarios) {
				// Cargo la info del usuario necesaria para los filtros
				Long idITR = usu.getItr().getIdItr();
				String estado = usu.getEstadoUsuario().toString();
				String tipo = usu.getClass().getSimpleName().trim();
				Integer gen = 0;

				if (filtros.get("TIPO").toString().equalsIgnoreCase(Roles.ESTUDIANTE.name())) {
					ArrayList<Estudiante> estudiantes = (ArrayList) BeanIntances.user().findAllEstudiantes();

					for (Estudiante est : estudiantes) {
						if (est.getIdUsuario() == usu.getIdUsuario()) {
							gen = est.getGeneracion();
						}

					}
				}

				// proceso que se cumplan las tres condiciones
				if (gen != 0 && filtros.get("GENERACION") != null) {
					
					if (idITR == ((Itr) filtros.get("ITR")).getIdItr()
							&& tipo.equalsIgnoreCase(filtros.get("TIPO").toString())
							&& estado.equalsIgnoreCase(filtros.get("ESTADO").toString())
							&& gen == Integer.parseInt(filtros.get("GENERACION").toString())) {

						filtrados.add(usu);
					}
				} else {
					if (idITR == ((Itr) filtros.get("ITR")).getIdItr()
							&& tipo.equalsIgnoreCase(filtros.get("TIPO").toString())
							&& estado.equalsIgnoreCase(filtros.get("ESTADO").toString())) {
						filtrados.add(usu);
					}
				}
			}
		} else {

			cargarUsuarios(tblUsuarios, usuarios);
		}

		cargarUsuarios(tblUsuarios, filtrados);
	}
}
