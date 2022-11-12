package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.entities.Itr;
import com.entities.enums.Departamento;

import beans.BeanIntances;
import components.VTextBox;
import swingutils.Mensajes;
import validation.ValidacionesItr;
import validation.ValidationObject;

public class viewAltaITR  extends JPanel implements ViewMedida  {
	private JTable table;
	private JComboBox <Departamento> comboBoxAltaDepar;
	private JComboBox <Departamento> comboBoxModDepar;
	private List<Itr> itrs;
	private VTextBox textBoxModNombre;

	/**
	 * Create the panel.
	 */
	public viewAltaITR() {
		setBounds(0,0,ANCHO_VIEW,LARGO_VIEW);
		addComponentListener(new ComponentAdapter() {
		});
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ITR");
		lblNewLabel.setBounds(372, 10, 38, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Alta ITR");
		lblNewLabel_1.setBounds(120, 57, 56, 21);
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
		comboBoxAltaDepar.setBounds(162, 107, 129, 17);
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
					if(textBoxAltaNombre.isValid()) {
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
		scrollPane.setBounds(36, 250, 305, 130);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "id", "Departamento", "Nombre", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);

		//Me cargo el arreglo de itrs con consulta findAll
		List<Itr> itrs = (List) BeanIntances.itr().findAll();
		// Cargo el listado de los itrs a la tabla
		cargarItr(table, itrs);
		
		
		JLabel lblNewLabel_5 = new JLabel("Departamento");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(363, 252, 88, 13);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Nombre ITR");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(363, 299, 88, 13);
		add(lblNewLabel_6);
		
		comboBoxModDepar = new JComboBox<Departamento>();
		comboBoxModDepar.setBounds(505, 250, 129, 17);
		add(comboBoxModDepar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textBoxModNombre.isValid()) {
						Itr itr = new Itr();
						itr.setNombre(textBoxModNombre.getText());
						itr.setDepartamento((Departamento) comboBoxModDepar.getSelectedItem());

						ValidationObject error = ValidacionesItr.validarItr(itr);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						itr = BeanIntances.itr().update(itr);
						Mensajes.MostrarExito("Se modifico correctamente el Itr " + itr.getNombre());	
					}
				}catch(Exception E) {
					Mensajes.MostrarError(E.getMessage());
				}
			}
		});
		btnModificar.setBounds(571, 359, 100, 21);
		add(btnModificar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 188, 695, 2);
		add(separator);
		
		textBoxModNombre = new VTextBox();
		textBoxModNombre.setBounds(505, 291, 129, 21);
		textBoxModNombre.setValidationFunc(text -> ValidacionesItr.validarNombre(text));
		add(textBoxModNombre);
		
		
		JButton btnActivar = new JButton("Activar");
		btnActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
				}catch(Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
				}
			}
		});
		btnActivar.setBounds(351, 359, 100, 21);
		add(btnActivar);
		
		JButton btnDesactivar = new JButton("Desactivar");
		btnDesactivar.setBounds(461, 359, 100, 21);
		add(btnDesactivar);
		
		
		//Se incorpora elemento para mitigar error en otro VTextBox
		VTextBox textBox = new VTextBox();
		textBox.setBounds(10, 681, 13, 13);
		add(textBox);
		textBox.setVisible(false);
		
		for (Departamento d : Departamento.values()) {
			comboBoxAltaDepar.addItem(d);
		}

	}
	
	public void cargarItr(JTable table, List<Itr> itrs) {
		String columns[] = { "id", "Departamento", "Nombre", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		if (table != null) {
			for (Itr itr : itrs) {
				Long id = itr.getIdItr();
				String departamento = itr.getDepartamento().toString();
				String nombre = itr.getNombre();
				String estado = itr.getEstado().toString();
				Object[] datos = { id, departamento, nombre, estado };
				modeloJTable.addRow(datos);
			}
		}
		table.setModel(modeloJTable);
	}
}
