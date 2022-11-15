package viewsEstudiante;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.mapping.Set;

import views.Login;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

public class ViewEstudiante extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEstudiante frame = new ViewEstudiante();
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
	public ViewEstudiante() {
		setTitle("Estudiante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/images/logo utec (2).png")));
		lblNewLabel_4.setBounds(620, 316, 428, 255);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 743, 410);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(new ViewsDatosPersonales());
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane_1, null);
		
	}
}
