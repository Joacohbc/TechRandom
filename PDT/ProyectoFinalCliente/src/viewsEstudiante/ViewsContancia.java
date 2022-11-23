package viewsEstudiante;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.daos.EventosDao;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Itr;

import beans.BeanIntances;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewsContancia extends JPanel {

	private JPanel contentPane;
	private JTable table;
	private static Estudiante estudiante;
	

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public ViewsContancia(Estudiante estudiante) {
		setBounds(100, 100, 679, 624);
		setLayout(null);
				
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 101, 623, 192);
		add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Eventos Disponibles.");
		lblNewLabel.setBounds(20, 63, 206, 14);
		add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Solicitar");
		btnNewButton.setBounds(554, 321, 89, 23);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				
			}
		});

		
		table = new JTable();
		scrollPane.setViewportView(table);
		String columns[] = { "id", "Titulo", "Fecha de Inicio", "Fecha de Fin", "Modalidad", "Localizacion" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0);
		table.setModel(modeloJTable);
		
		cargarEventos(table, estudiante);
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void cargarEventos (JTable table, Estudiante estudiante) {
		// Cargamos todos los eventos relacionados con el ID de estudiante
		List<Evento> eventos = BeanIntances.evento().findByEstudianteId(estudiante.getIdEstudiante());
		String columns[] = { "id", "Titulo", "FechaInicio", "FechaFin", "Modalidad", "Localizacion" };
		DefaultTableModel modeloJTable = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		if (table != null) {
			for (Evento evento : eventos) {
				Long id = evento.getIdEvento();
				String titulo = evento.getTitulo().toString();
				String fechaInicio = evento.getFechaInicio().toString();
				String fechaFin = evento.getFechaFin().toString();
				String modalidad = evento.getModalidad().toString();
				String localizacion = evento.getLocalizacion().toString();
				Object[] datos = { id, titulo, fechaInicio, fechaFin, modalidad, localizacion };
				modeloJTable.addRow(datos);
			}
		}
		table.setModel(modeloJTable);
	}

		

	
}
