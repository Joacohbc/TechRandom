package viewsAnalista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.entities.AccionConstancia;
import com.entities.Analista;
import com.entities.Constancia;
import com.entities.enums.EstadoSolicitudes;

import beans.BeanIntances;
import swingutils.Mensajes;
import validation.ValidacionesConstancia;
import validation.ValidationObject;
import views.ViewMedida;

public class ViewModEstadoSolConstancia extends JPanel implements ViewMedida {
	private JTable table;
	private JComboBox comboBoxEstadosCons;
	private EstadoSolicitudes filtro;
	private List<Constancia> constancias;
	private JComboBox comboBoxEstado;

	/**
	 * Create the panel.
	 */
	public ViewModEstadoSolConstancia(Analista ana) {
		setLayout(null);
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 179, 541, 325);
		add(scrollPane);
		
		table = new JTable();
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					String nombreEstado = table.getModel().getValueAt(row, 1).toString();
					for (EstadoSolicitudes estados : EstadoSolicitudes.values()) {
						if(nombreEstado == estados.toString()) {
							comboBoxEstadosCons.setSelectedItem(estados);
							//constancias = BeanIntances.constancia().findAll();
							cargarConstancias(table,constancias,(EstadoSolicitudes)comboBoxEstado.getSelectedItem());
							break;
						}
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		String columns[] = { "Id", "Fecha de Solicitud", "Detalle", "Tipo De constancia",  "Estado"};
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		
		
		
		JLabel lblNewLabel = new JLabel("Estado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(68, 560, 66, 13);
		add(lblNewLabel);
		
		comboBoxEstadosCons = new JComboBox();
		comboBoxEstadosCons.addItem(EstadoSolicitudes.EN_PROCESO);
		comboBoxEstadosCons.addItem(EstadoSolicitudes.FINALIZADO);
		comboBoxEstadosCons.addItem(EstadoSolicitudes.INGRESADO);
		comboBoxEstadosCons.setBounds(126, 556, 146, 21);
		add(comboBoxEstadosCons);
		
		JButton btnModEstCons = new JButton("Modificar Estado Constancia");
		btnModEstCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Constancia cons = new Constancia();
					if (table.getSelectedRow() != -1) {
						int row = table.getSelectedRow();
						cons = BeanIntances.constancia().findById(Long.valueOf(table.getModel().getValueAt(row, 0).toString()));
					}
					cons.setEstado((EstadoSolicitudes) comboBoxEstadosCons.getSelectedItem());
					
					
					ValidationObject error = ValidacionesConstancia.validarConstancia(cons);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					AccionConstancia acc = new AccionConstancia();
					acc.setAnalista(ana);
					acc.setConstancia(cons);
					acc.setDetalle("DETALLE UPODATE");
					
					cons = BeanIntances.constancia().updateEstado(cons.getIdConstancia(), cons.getEstado(), acc);
					Mensajes.MostrarExito("Se modifico correctamente el estado de la constancia ");
					//constancias = BeanIntances.constancia().findAll();
					cargarConstancias(table,constancias,(EstadoSolicitudes)comboBoxEstado.getSelectedItem());
							
					
				}catch(Exception E) {
					Mensajes.MostrarError(E.getMessage());
				}
			}
		});
		btnModEstCons.setBounds(400, 556, 206, 21);
		add(btnModEstCons);
		
		JLabel lblNewLabel_1 = new JLabel("Filtro por estado");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(68, 52, 122, 13);
		add(lblNewLabel_1);
		
		comboBoxEstado = new JComboBox();
		comboBoxEstado.addItem(null);
		comboBoxEstado.addItem(EstadoSolicitudes.EN_PROCESO);
		comboBoxEstado.addItem(EstadoSolicitudes.FINALIZADO);
		comboBoxEstado.addItem(EstadoSolicitudes.INGRESADO);
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxEstado.getSelectedItem() instanceof EstadoSolicitudes) {
					//constancias = BeanIntances.constancia().findAll();
					filtro = (EstadoSolicitudes) comboBoxEstado.getSelectedItem();
					cargarConstancias(table, constancias, filtro);
					return;
				}
				cargarConstancias(table, constancias, null);
			}
		});
		comboBoxEstado.setBounds(210, 90, 175, 21);
		add(comboBoxEstado);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(68, 94, 45, 13);
		add(lblNewLabel_2);
		cargarConstancias(table,constancias,filtro);
	}
	
	public void cargarConstancias(JTable tabla, List<Constancia> constancias, EstadoSolicitudes filtro) {
		constancias = BeanIntances.constancia().findAll();
		String columns[] = { "Id","Fecha de Solicitus", "Detalle", "Tipo De Constancia", "Estado" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		if (constancias == null) {
			return;
		}
		for (Constancia unaC : constancias) {
			if (filtro == null) {
				Long id = unaC.getIdConstancia();
				String evento = unaC.getEvento().getTitulo().toString();
				String fechaSolicitud = unaC.getFechaHora().toString();
				String detalle = unaC.getDetalle().toString();
				String tipoConstancia = unaC.getTipoConstancia().getTipo().toString();
				String estado = unaC.getEstado().toString();
				Object[] datos = {id,evento ,fechaSolicitud, detalle, tipoConstancia, estado};
				modeloJTable.addRow(datos);
			} else {
				if (unaC.getEstado() == filtro) {
					Long id = unaC.getIdConstancia();
					String evento = unaC.getEvento().getTitulo().toString();
					String fechaSolicitud = unaC.getFechaHora().toString();
					String detalle = unaC.getDetalle().toString();
					String tipoConstancia = unaC.getTipoConstancia().getTipo().toString();
					String estado = unaC.getEstado().toString();
					Object[] datos = {id,evento ,fechaSolicitud, detalle, tipoConstancia, estado};
					modeloJTable.addRow(datos);
				}
			}

		}
		tabla.setModel(modeloJTable);
	}
}
