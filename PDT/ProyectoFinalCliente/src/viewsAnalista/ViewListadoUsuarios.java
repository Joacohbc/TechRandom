package viewsAnalista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.EstadoUsuario;

import beans.BeanIntances;
import components.Roles;
import swingutils.Mensajes;
import views.ViewMedida;

public class ViewListadoUsuarios extends JPanel implements ViewMedida {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuariosOriginal = BeanIntances.user().findAll(Usuario.class);

	private JTable tblUsuarios;
	private JSpinner spGeneracion;
	private JCheckBox chkFiltroGeneracion;
	private JCheckBox chkFiltroTipoUsuario;
	private JCheckBox chkFiltroItr;
	private JCheckBox chkFiltroEstado;
	private JComboBox<Roles> comboTipoUsuario;
	private JComboBox<Itr> comboITR;
	private JComboBox<EstadoUsuario> comboEstado;
	private JButton btnCargarUsuario;
	private JFrame fDetalles;
	
	// Es Singleton para no poder llamarlo mas de una vez
	private void detalles(Usuario usu) {
		if(fDetalles == null) {
			fDetalles = new JFrame();
			fDetalles.setAlwaysOnTop(true);
			fDetalles.setBounds(100, 100, ANCHO_VIEW, LARGO_VIEW);
			JPanel contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
			fDetalles.setContentPane(contentPane);
			fDetalles.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);			
		}
		
//		if(fDetalles.isVisible()) {
//			Mensajes.MostrarError("Ya tiene una ventana");
//			return;
//		}
		
		fDetalles.getContentPane().removeAll();
		fDetalles.getContentPane().add(new ViewPerfil(usu), BorderLayout.CENTER);
		fDetalles.setVisible(true);
	}
	
	public ViewListadoUsuarios(Analista ana) {

		setBounds(100, 100, ANCHO_VIEW, LARGO_VIEW);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int fila = tblUsuarios.getSelectedRow();
					
					if(fila == -1) {
						Mensajes.MostrarError("Seleccione un usuario primero");
						return;
					}
					
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					if (usu == null) {
						Mensajes.MostrarError("No eexiste un usuario con el ID: " + id);
						return;
					}
					
					BeanIntances.user().updateEstadoUsuario(id, EstadoUsuario.VALIDADO);
					btnCargarUsuario.doClick();
					filtrarListaUsuarios();
				} catch (Exception ex) {
					Mensajes.MostrarError("Error inesperado: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnActivar.setBounds(12, 335, 105, 27);
		panel.add(btnActivar);

		chkFiltroGeneracion = new JCheckBox("Habilitado");
		chkFiltroGeneracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spGeneracion.setEnabled(chkFiltroGeneracion.isSelected());
				filtrarListaUsuarios();
			}
		});
		chkFiltroGeneracion.setBounds(273, 123, 114, 25);
		panel.add(chkFiltroGeneracion);

		spGeneracion = new JSpinner();
		spGeneracion.setModel(new SpinnerNumberModel(Integer.valueOf(2015), Integer.valueOf(1000),
				Integer.valueOf(9999), Integer.valueOf(1)));
		spGeneracion.setBounds(97, 125, 168, 22);
		spGeneracion.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				filtrarListaUsuarios();
			}
		});
		panel.add(spGeneracion);

		comboTipoUsuario = new JComboBox<Roles>();
		// Se utiliza el evento action performed para capturar cada vez que se cambia el
		// valor del comboBox
		comboTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoUsuario.getSelectedItem().toString();

				switch (tipo) {
				case "ESTUDIANTE": {
					spGeneracion.setEnabled(true);
					chkFiltroGeneracion.setEnabled(true);
					break;
				}
				case "ANALISTA": {
					spGeneracion.setEnabled(false);
					chkFiltroGeneracion.setEnabled(false);
					break;
				}
				case "TUTOR": {
					spGeneracion.setEnabled(false);
					chkFiltroGeneracion.setEnabled(false);
					break;
				}

				}
				// se llama al mÃ©todo que actualiza la lista en base a los filtros
				// seleccionados
				filtrarListaUsuarios();
			}
		});
		comboTipoUsuario.setBounds(97, 13, 168, 26);
		panel.add(comboTipoUsuario);

		comboITR = new JComboBox<Itr>();
		comboITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarListaUsuarios();
			}
		});
		comboITR.setBounds(97, 50, 168, 26);
		panel.add(comboITR);

		comboEstado = new JComboBox<EstadoUsuario>();
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filtrarListaUsuarios();
			}
		});
		comboEstado.setBounds(97, 87, 168, 26);
		panel.add(comboEstado);

		JLabel lblNewLabel_1 = new JLabel("Estado");
		lblNewLabel_1.setBounds(12, 89, 60, 17);
		panel.add(lblNewLabel_1);

		JLabel lblGeneracion = new JLabel("GeneraciÃ³n");
		lblGeneracion.setBounds(12, 127, 82, 17);
		panel.add(lblGeneracion);
		JLabel lblNewLabel_3 = new JLabel("Usuario");
		lblNewLabel_3.setBounds(12, 13, 60, 17);
		panel.add(lblNewLabel_3);

		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int fila = tblUsuarios.getSelectedRow();
					
					if(fila == -1) {
						Mensajes.MostrarError("Seleccione un usuario primero");
						return;
					}
					
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					if (usu == null) {
						Mensajes.MostrarError("No eexiste un usuario con el ID: " + id);
						return;
					}
					
					if(usu.getIdUsuario() == ana.getIdAnalista()) {
						Mensajes.MostrarError("No se puede dar de baja a si mismo");
						return;
					}
					
					BeanIntances.user().updateEstadoUsuario(id, EstadoUsuario.ELIMINADO);
					btnCargarUsuario.doClick();
					filtrarListaUsuarios();

				} catch (Exception ex) {
					Mensajes.MostrarError("Error inesperado: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnDesactivar.setBounds(129, 335, 105, 27);
		panel.add(btnDesactivar);

		JButton btnAbrirUsuario = new JButton("Ver detalles");
		btnAbrirUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int fila = tblUsuarios.getSelectedRow();
					
					if(fila == -1) {
						Mensajes.MostrarError("Seleccione un usuario primero");
						return;
					}
					
					Long id = Long.parseLong(tblUsuarios.getModel().getValueAt(fila, 0).toString());
					Usuario usu = BeanIntances.user().findById(Usuario.class, id);
					if (usu == null) {
						Mensajes.MostrarError("No eexiste un usuario con el ID: " + id);
						return;
					}
					
					detalles(usu);
				} catch (Exception ex) {
					Mensajes.MostrarError("Error inesperado: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnAbrirUsuario.setBounds(436, 335, 105, 27);
		panel.add(btnAbrirUsuario);

		JLabel lblNewLabel_4 = new JLabel("GeneraciÃ³n");
		lblNewLabel_4.setBounds(12, 127, 96, 17);
		panel.add(lblNewLabel_4);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 158, 520, 151);
		panel.add(scrollPane);

		tblUsuarios = new JTable();
		scrollPane.setViewportView(tblUsuarios);

		JLabel lblNewLabel = new JLabel("ITR");
		lblNewLabel.setBounds(12, 55, 60, 17);
		panel.add(lblNewLabel);

		chkFiltroTipoUsuario = new JCheckBox("Habilitado");
		chkFiltroTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboTipoUsuario.setEnabled(chkFiltroTipoUsuario.isSelected());
				if((Roles) comboTipoUsuario.getSelectedItem() == Roles.ESTUDIANTE) {
					chkFiltroGeneracion.setEnabled(chkFiltroTipoUsuario.isSelected());
					spGeneracion.setEnabled(chkFiltroTipoUsuario.isSelected());
				}
				filtrarListaUsuarios();
			}
		});
		chkFiltroTipoUsuario.setSelected(true);
		chkFiltroTipoUsuario.setBounds(273, 14, 114, 25);
		panel.add(chkFiltroTipoUsuario);

		chkFiltroItr = new JCheckBox("Habilitado");
		chkFiltroItr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboITR.setEnabled(chkFiltroItr.isSelected());
				filtrarListaUsuarios();
			}
		});
		chkFiltroItr.setSelected(true);
		chkFiltroItr.setBounds(273, 51, 114, 25);
		panel.add(chkFiltroItr);

		chkFiltroEstado = new JCheckBox("Habilitado");
		chkFiltroEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboEstado.setEnabled(chkFiltroEstado.isSelected());
				filtrarListaUsuarios();
			}
		});
		chkFiltroEstado.setSelected(true);
		chkFiltroEstado.setBounds(273, 88, 114, 25);
		panel.add(chkFiltroEstado);
		
		btnCargarUsuario = new JButton("Recargar Usuarios");
		btnCargarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuariosOriginal = BeanIntances.user().findAll(Usuario.class);
				filtrarListaUsuarios();
			}
		});
		btnCargarUsuario.setBounds(246, 335, 178, 27);
		panel.add(btnCargarUsuario);

		cargarCombosFiltros();
	}

	public void cargarUsuarios(List<Usuario> listaUsuarios) {
		String columns[] = { "Id", "Documento", "Nombres", "Apellidos", "ITR", "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (tblUsuarios != null) {
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

	// MÃ©todo para cargar los valores que contienen los filtros
	public void cargarCombosFiltros() {
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

	public void filtrarListaUsuarios() {
		
		if(comboEstado.getItemCount() == 0 || comboITR.getItemCount() == 0  || comboTipoUsuario.getItemCount() == 0) {
			return;
		}
		
		List<Usuario> usuarios = new ArrayList<>(usuariosOriginal);
		List<Usuario> copy = new ArrayList<>(usuarios);
		
		if (chkFiltroTipoUsuario.isSelected()) {
			Roles rol = (Roles) comboTipoUsuario.getSelectedItem();
			
			if (rol != Roles.ESTUDIANTE) {
				for (Usuario usuario : usuarios) {
					if(usuario instanceof Estudiante) {
						copy.remove(usuario);
					}
				}
			}

			if (rol != Roles.TUTOR) {
				for (Usuario usuario : usuarios) {
					if (usuario instanceof Tutor) {
						copy.remove(usuario);
					}
				}
			}

			if (rol != Roles.ANALISTA) {
				for (Usuario usuario : usuarios) {
					if (usuario instanceof Analista) {
						copy.remove(usuario);
					}
				}
			}
			
			if (chkFiltroGeneracion.isSelected()) {
				for (Usuario usuario : usuarios) {
					if(!(usuario instanceof Estudiante)) 
						continue;
					
					Estudiante est = (Estudiante) usuario;
					Integer gen = (Integer) spGeneracion.getValue();
					if (!est.getGeneracion().equals(gen)) {
						copy.remove(usuario);
					}
				}
			}
			
			usuarios = new ArrayList<>(copy);
		}

		if (chkFiltroItr.isSelected()) {
			for (Usuario usuario : usuarios) {
				Itr itr = (Itr) comboITR.getSelectedItem();
				if (usuario.getItr().getIdItr() != itr.getIdItr()) {
					copy.remove(usuario);
				}
			}
			usuarios = new ArrayList<>(copy);

		}

		if (chkFiltroEstado.isSelected()) {
			for (Usuario usuario : usuarios) {
				if (usuario.getEstadoUsuario() != (EstadoUsuario) comboEstado.getSelectedItem()) {
					copy.remove(usuario);
				}
			}
			usuarios = new ArrayList<>(copy);
		}
	
		cargarUsuarios(usuarios);
	}
}