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

import com.entities.Usuario;

import beans.BeanIntances;

public class ListadoUsuarios extends JFrame {

	private JPanel contentPane;
	private JTextField txtGeneracion;
	private Map filtros;
	private ArrayList<Usuario> usuarios;
	private JList lstUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ListadoUsuarios frame = new ListadoUsuarios();
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
	public ListadoUsuarios() {
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
		this.cargarUsuarios(lstUsuarios);

		JButton btnActivar = new JButton("Activar");
		btnActivar.setBounds(12, 335, 105, 27);
		panel.add(btnActivar);

		JComboBox comboTipoUsuario = new JComboBox();
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoUsuario.getSelectedItem().toString();
				switch (tipo) {
				case "ESTUDIANTE": {
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

				filtrarListaUsuarios(lstUsuarios, filtros);
			}
		});
		comboTipoUsuario.setBounds(97, 13, 168, 26);
		panel.add(comboTipoUsuario);

		JComboBox comboITR = new JComboBox();
		comboITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ITR", comboITR.getSelectedItem());
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

		JComboBox comboEstado = new JComboBox();
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtros.put("ESTADO", comboEstado.getSelectedItem());
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

		// cargo los combos para poder hacer los filtros
		this.cargarCombosFiltros(comboEstado, comboTipoUsuario, comboITR);

	}

	public void cargarUsuarios(JList lstUsuarios) {
		DefaultListModel listModel = new DefaultListModel();
		usuarios = (ArrayList) BeanIntances.user().findAll(Usuario.class);
		listModel.addAll(usuarios);
		lstUsuarios.setModel(listModel);
	}

	public void cargarCombosFiltros(JComboBox comboEstado, JComboBox comboTipoUsuario, JComboBox comboITR) {

		/*
		 * esto queda hardcoded pero falta un método que traiga ITR
		 * 
		 */
		comboEstado.addItem("ACTIVO");
		comboEstado.addItem("SIN_VALIDAR");
		comboEstado.addItem("ELIMINADO");

		comboTipoUsuario.addItem("ANALISTA");
		comboTipoUsuario.addItem("ESTUDIANTE");
		comboTipoUsuario.addItem("TUTOR");

		comboITR.addItem(1);
		comboITR.addItem(2);
	}

	public void filtrarListaUsuarios(JList lstUsuarios, Map filtros) {
		ArrayList<Usuario> filtrados = new ArrayList<Usuario>();
		if (!filtros.isEmpty() && filtros.get("ITR") != null) {
			for (Usuario usu : usuarios) {
				Long itr = usu.getItr().getIdItr();
				String tipo = usu.getClass().getSimpleName();
				String estado = usu.getEstadoUsuario().name();
				//int generacion = BeanIntances.user().
				System.out.println("el tipo es " + tipo + " El filtro es " + filtros.get("TIPO").toString());

				// proceso que se cumplan las tres condiciones
				if (itr == Long.parseLong(filtros.get("ITR").toString())
						&& tipo.trim().equalsIgnoreCase(filtros.get("TIPO").toString().trim())
						&& estado == filtros.get("ESTADO").toString()) {
					
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
