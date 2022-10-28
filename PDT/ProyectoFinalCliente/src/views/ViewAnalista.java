package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.entities.Estudiante;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;

import beans.BeanIntances;
import components.Roles;

public class ViewAnalista extends JFrame {

	private JPanel contentPane;
	private JTextField txtGeneracion;
	private JList lstUsuarios;
	private ArrayList<Usuario> usuarios;
	
	/*
	 * Se utiliza una variable de tipo HashMap para gestionar los filtros que aplica el usuario
	 * El HashMap permite utilizar pares de datos <Key,Value> de esta manera cada vez que el usuario
	 * actualice los valores de los filtros, al tener el mismo Key se reemplaza el Value
	*/
	private Map filtros;
	
	

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

		lstUsuarios = new JList();
		lstUsuarios.setBounds(12, 158, 530, 160);
		panel.add(lstUsuarios);
		
		//Cargo la lista de usuarios al JList
		cargarUsuarios(lstUsuarios);

		JButton btnActivar = new JButton("Activar");
		btnActivar.setBounds(12, 335, 105, 27);
		panel.add(btnActivar);

		JComboBox<Roles> comboTipoUsuario = new JComboBox();
		//Se utiliza el evento action performed para capturar cada vez que se cambia el valor del comboBox
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoUsuario.getSelectedItem().toString();
				
				switch (tipo) {
				case "ESTUDIANTE" : {
					filtros.put("TIPO", "Estudiante");
					txtGeneracion.setEditable(true);
					break;
				}
				case "ANALISTA": {
					filtros.put("TIPO", "Analista");
					txtGeneracion.setEditable(false);
					break;
				}
				case "TUTOR": {
					filtros.put("TIPO", "Tutor");
					txtGeneracion.setEditable(false);
					break;
				}

				}
				//se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(lstUsuarios, filtros);
			}
		});
		comboTipoUsuario.setBounds(97, 13, 168, 26);
		panel.add(comboTipoUsuario);

		JComboBox comboITR = new JComboBox();
		comboITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ITR", comboITR.getSelectedItem());
				
				//se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(lstUsuarios, filtros);
			}
		});
		comboITR.setBounds(97, 50, 168, 26);
		panel.add(comboITR);

		txtGeneracion = new JTextField();
		txtGeneracion.setBounds(97, 125, 168, 21);
		panel.add(txtGeneracion);
		txtGeneracion.setColumns(10);
		txtGeneracion.setEditable(false);

		JComboBox<EstadoUsuario> comboEstado = new JComboBox();
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ESTADO", comboEstado.getSelectedItem());
				//se llama al método que actualiza la lista en base a los filtros seleccionados
				filtrarListaUsuarios(lstUsuarios, filtros);
			}
		});
		comboEstado.setBounds(97, 87, 168, 26);
		panel.add(comboEstado);

		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setBounds(12, 89, 60, 17);
		panel.add(lblNewLabel_1);

		JLabel lblGeneracion = new JLabel("Generación");
		lblGeneracion.setBounds(12, 127, 96, 17);
		panel.add(lblGeneracion);
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(12, 13, 60, 17);
		panel.add(lblNewLabel_3);

		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.setBounds(224, 335, 105, 27);
		panel.add(btnDesactivar);

		JButton btnAbrirUsuario = new JButton("Ver detalles");
		btnAbrirUsuario.setBounds(436, 335, 105, 27);
		panel.add(btnAbrirUsuario);

		JLabel lblNewLabel_4 = new JLabel("Generación");
		lblNewLabel_4.setBounds(12, 127, 96, 17);
		panel.add(lblNewLabel_4);

		// cargo los combos con los valores para poder hacer los filtros
		this.cargarCombosFiltros(comboEstado, comboTipoUsuario, comboITR);

	}
	//Método utilizado para cargar el JList con los usuarios
	public void cargarUsuarios(JList lstUsuarios) {
		DefaultListModel listModel = new DefaultListModel();
		usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
		listModel.addAll(usuarios);
		lstUsuarios.setModel(listModel);
	}
	
	//Método para cargar los valores que contienen los filtros
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

		comboITR.addItem(1);
		comboITR.addItem(2);
	}

	public void filtrarListaUsuarios(JList lstUsuarios, Map filtros) {
		
		ArrayList<Usuario> filtrados = new ArrayList<Usuario>();
		
		if (!filtros.isEmpty() && filtros.get("ITR") != null) {
			
			for (Usuario usu : usuarios) {
				
				//Cargo la info del usuario necesaria para los filtros
				Long idITR = usu.getItr().getIdItr();
				String estado = usu.getEstadoUsuario().name();
				String tipo = usu.getClass().getSimpleName().trim();
				
				
				if(filtros.get("TIPO").toString().equalsIgnoreCase(Roles.ESTUDIANTE.name())) {
					/*
					 * NECESITO LOS ESTUDIANTES
					 * */
				}

				
				// proceso que se cumplan las tres condiciones
				if (idITR == Long.parseLong(filtros.get("ITR").toString())
						&& tipo.equalsIgnoreCase(filtros.get("TIPO").toString())
						&& estado.equalsIgnoreCase(filtros.get("ESTADO").toString()) ) {
					
					filtrados.add(usu);
				}
			}
		} else {
			cargarUsuarios(lstUsuarios);
		}
		DefaultListModel listModel = new DefaultListModel();
		listModel.addAll(filtrados);
		lstUsuarios.setModel(listModel);
		lstUsuarios.repaint();
	}
}
