package viewsAnalista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Analista;

import beans.BeanIntances;
import views.ViewMedida;
import views.ViewDatosPersonales;

public class ViewCentralAnalista extends JFrame implements ViewMedida{

	private JPanel contentPane;
	PanelAnalista2 panel2;
	PanelAnalista1 panel1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnSecondaryMenu;
	private JMenuItem mntmNewMenuItem_2;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Analista p = BeanIntances.user().findById(Analista.class, 3L);
					ViewCentralAnalista frame = new ViewCentralAnalista(p);
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
	public ViewCentralAnalista(Analista analista) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, ANCHO_VIEW, LARGO_VIEW);
		this.setTitle("Pasar el nombre del usuario por parametro en el constructor");
		contentPane = new JPanel();
		contentPane = new viewAltaITR();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		JMenu mnMainMenu = new JMenu("Gestión de Usuarios");
		menuBar.add(mnMainMenu);
		
		mnSecondaryMenu = new JMenu("Gestión ITRs");
		menuBar.add(mnSecondaryMenu);
		
		//para hacer que se alienea algunos a la derecha o izquierda
		//todo lo que se pone antes aparece a la izquierda 
		//todo lo que se pone despues aparece a la derecha 
		menuBar.add(Box.createHorizontalGlue());

		setJMenuBar(menuBar);

		mntmNewMenuItem = new JMenuItem("Listado de Usuarios");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1 = new PanelAnalista1();
				contentPane.removeAll();
				contentPane.repaint();
				contentPane.revalidate();
				contentPane.add(panel1, BorderLayout.CENTER);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});

		mnMainMenu.add(mntmNewMenuItem);

		mntmNewMenuItem_1 = new JMenuItem("panel 2");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel2 = new PanelAnalista2();
				contentPane.removeAll();
				contentPane.repaint();
				contentPane.revalidate();
				contentPane.add(panel2);
				contentPane.add(panel2, BorderLayout.CENTER);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});

		mnMainMenu.add(mntmNewMenuItem_1);
		

		
		mntmNewMenuItem_2 = new JMenuItem("Mantenimiento");
		mnSecondaryMenu.add(mntmNewMenuItem_2);
		
		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewDatosPersonales vista = new ViewDatosPersonales(analista);
				vista.setVisible(true);
			}
			
		});
		label.setIcon(new ImageIcon(ViewCentralAnalista.class.getResource("/images/usuario (3).png")));
		menuBar.add(label);
	}

}
