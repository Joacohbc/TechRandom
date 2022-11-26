package viewsITR;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entities.Itr;
import com.entities.enums.Departamento;

import org.hibernate.resource.beans.spi.BeanInstanceProducer;

import beans.BeanIntances;
import components.VTextBox;
import swingutils.Mensajes;
import validation.ValidacionesItr;
import validation.ValidationObject;
import views.ViewMedida;
import viewsAnalista.ViewListadoUsuarios;

public class ViewAltaITR  extends JPanel implements ViewMedida  {
	
	private JTable table;
	private JComboBox <Departamento> comboBoxAltaDepar;
	private JComboBox <Departamento> comboBoxModDepar;
	private List<Itr> itrs;
	private VTextBox textBoxModNombre;
	private JComboBox comboBoxEstadoITR;

	
	/**
	 * Create the panel.
	 */
	public ViewAltaITR() {
		setLayout(null);
		
		setBounds(0,0,ANCHO_VIEW,LARGO_VIEW);
		comboBoxEstadoITR = new JComboBox();
		
		comboBoxEstadoITR.setBounds(241, 440, 173, 21);
		add(comboBoxEstadoITR);
		comboBoxEstadoITR.addItem("Ver todos");
		comboBoxEstadoITR.addItem("Habilitado");
		comboBoxEstadoITR.addItem("Deshabilitado");
		addComponentListener(new ComponentAdapter() {
			
		});
		
		
		JLabel lblNewLabel_1 = new JLabel("Alta ITR");
		lblNewLabel_1.setBounds(107, 57, 56, 21);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Departamento");
		lblNewLabel_2.setBounds(36, 111, 88, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre ITR");
		lblNewLabel_3.setBounds(36, 151, 88, 13);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNewLabel_3);
		
		comboBoxAltaDepar = new JComboBox<Departamento>();
		comboBoxAltaDepar.setBounds(162, 103, 129, 21);
		add(comboBoxAltaDepar);
		
		
		VTextBox textBoxAltaNombre = new VTextBox();
		textBoxAltaNombre.setBounds(162, 143, 129, 21);
		textBoxAltaNombre.setValidationFunc(text -> ValidacionesItr.validarNombre(text));
		add(textBoxAltaNombre);
		
		
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Alta de ITR
				try {
					if(textBoxAltaNombre.isContentValid()) {
						Itr itr = new Itr();
						itr.setNombre(textBoxAltaNombre.getText());
						itr.setDepartamento((Departamento) comboBoxAltaDepar.getSelectedItem());

						ValidationObject error = ValidacionesItr.validarItr(itr);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						itr = BeanIntances.itr().save(itr);
						Mensajes.MostrarExito("Se dio de alta correctamente el Itr " + itr.getNombre());
						cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
					}
				}catch(Exception E) {
					Mensajes.MostrarError(E.getMessage());
				}
				
			}
		});
		btnInsertar.setBounds(514, 148, 120, 21);
		add(btnInsertar);
		
		JLabel lblNewLabel_4 = new JLabel("Modificar ITR");
		lblNewLabel_4.setBounds(107, 206, 88, 21);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 473, 606, 157);
		add(scrollPane);
		
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String nombreDepto = table.getModel().getValueAt(row, 1).toString();
					for (Departamento deptos : Departamento.values()) {
						if(nombreDepto == deptos.toString()) {
							comboBoxModDepar.setSelectedItem(deptos);
							break;
						}
					}
					String nombreItr = (String) table.getModel().getValueAt(row, 2);
					textBoxModNombre.setText(nombreItr);
				}
			}
		});
		scrollPane.setViewportView(table);
		String columns[] = { "id", "Departamento", "Nombre", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		

		
		// Cargo el listado de los itrs a la tabla
		cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
		
		
		JLabel lblNewLabel_5 = new JLabel("Departamento");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(48, 265, 88, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nombre ITR");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(48, 312, 88, 13);
		add(lblNewLabel_6);
		
		comboBoxModDepar = new JComboBox<Departamento>();
		comboBoxModDepar.setBounds(190, 259, 129, 21);
		add(comboBoxModDepar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Mensajes.MostrarSioNo("¿Esta seguro que quiere modificar el usuario?") == Mensajes.OPCION_NO) 
						return;
					
					if(textBoxModNombre.isContentValid()) {
						Mensajes.MostrarError(textBoxModNombre.getErrorMessage());
						return;
					}
						
					Itr itr = new Itr();
					if (table.getSelectedRow() != -1) {
						int row = table.getSelectedRow();
						itr.setIdItr(BeanIntances.itr().findByName(table.getModel().getValueAt(row, 2).toString()).getIdItr());
					}
					itr.setNombre(textBoxModNombre.getText());
					itr.setDepartamento((Departamento) comboBoxModDepar.getSelectedItem());
					itr.setEstado(false);
					
					ValidationObject error = ValidacionesItr.validarItr(itr);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					itr = BeanIntances.itr().update(itr);
					Mensajes.MostrarExito("Se modifico correctamente el Itr " + itr.getNombre());
					cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
				
				}catch(Exception E) {
					Mensajes.MostrarError(E.getMessage());
				}
			}
		});
		btnModificar.setBounds(256, 372, 100, 21);
		add(btnModificar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 188, 695, 2);
		add(separator);
		
		textBoxModNombre = new VTextBox();
		textBoxModNombre.setBounds(190, 304, 129, 21);
		textBoxModNombre.setValidationFunc(text -> ValidacionesItr.validarNombre(text));
		add(textBoxModNombre);
		
		
		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itr itr = new Itr();
					if (table.getSelectedRow() != -1) {
						int row = table.getSelectedRow();
						itr = BeanIntances.itr().findByName(table.getModel().getValueAt(row, 2).toString());
					}
					
					itr.setEstado(true);
					
					ValidationObject error = ValidacionesItr.validarItr(itr);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					itr = BeanIntances.itr().reactivar(itr.getIdItr());
					Mensajes.MostrarExito("Se modifico correctamente el Itr " + itr.getNombre());
					cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
				}catch(Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
				}
			}
		});
		btnActivar.setBounds(36, 372, 100, 21);
		add(btnActivar);
		
		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Itr itr = new Itr();
					if (table.getSelectedRow() != -1) {
						int row = table.getSelectedRow();
						itr = BeanIntances.itr().findByName(table.getModel().getValueAt(row, 2).toString());
					}
					
					itr.setEstado(false);
					
					ValidationObject error = ValidacionesItr.validarItr(itr);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					itr = BeanIntances.itr().remove(itr.getIdItr());
					Mensajes.MostrarExito("Se modifico correctamente el Itr " + itr.getNombre());
					cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
				}catch(Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
				}
			}
		});
		btnDesactivar.setBounds(146, 372, 100, 21);
		add(btnDesactivar);
		
		JLabel lblNewLabel = new JLabel("Filtro");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(107, 440, 45, 21);
		add(lblNewLabel);
		
		
		
		
		
		JLabel lblNewLabel_7 = new JLabel("Estado ITR");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(156, 443, 89, 13);
		add(lblNewLabel_7);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 421, 695, 2);
		add(separator_1);
		
		for (Departamento d : Departamento.values()) {
			comboBoxAltaDepar.addItem(d);
			comboBoxModDepar.addItem(d);
		}
		
		comboBoxEstadoITR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarItr(table,comboBoxEstadoITR.getSelectedItem().toString());
				
				
			}
		});
		
	}
	
	public void cargarItr(JTable table, String filtro) {
		//Me cargo el arreglo de itrs con consulta findAll
		List<Itr> itrs =  BeanIntances.itr().findAll();
		String columns[] = { "id", "Departamento", "Nombre", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if (table != null) {
			switch (filtro) {
			case "Habilitado": 
				for (Itr itr : itrs) {
					if(itr.getEstado()==true) {
						Long id = itr.getIdItr();
						String departamento = itr.getDepartamento().toString();
						String nombre = itr.getNombre();
						String estado = itr.getEstado().toString();
						Object[] datos = { id, departamento, nombre, estado };
						modeloJTable.addRow(datos);
					}		
				}
				break;
			case "Deshabilitado": 
				for (Itr itr : itrs) {
					if(itr.getEstado()==false) {
						Long id = itr.getIdItr();
						String departamento = itr.getDepartamento().toString();
						String nombre = itr.getNombre();
						String estado = itr.getEstado().toString();
						Object[] datos = { id, departamento, nombre, estado };
						modeloJTable.addRow(datos);
					}		
				}
				break;
			default:
				for (Itr itr : itrs) {
					Long id = itr.getIdItr();
					String departamento = itr.getDepartamento().toString();
					String nombre = itr.getNombre();
					String estado = itr.getEstado().toString();
					Object[] datos = { id, departamento, nombre, estado };
					modeloJTable.addRow(datos);
				}
			}
			
		}
		table.setModel(modeloJTable);
	}
}
