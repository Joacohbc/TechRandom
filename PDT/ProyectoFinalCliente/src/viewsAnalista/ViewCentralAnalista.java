package viewsAnalista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewCentralAnalista extends JFrame{

	private JPanel contentPane;
	PanelAnalista2 panel2;
	PanelAnalista1 panel1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnSecondaryMenu;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem itemConIcono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCentralAnalista frame = new ViewCentralAnalista();
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
	public ViewCentralAnalista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setTitle("Pasar el nombre del usuario por parametro en el constructor");
		
		JMenuBar menuBar = new JMenuBar();
		
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
	    layout.setVgap(10);
		menuBar.setLayout(layout);

		setJMenuBar(menuBar);

		JMenu mnMainMenu = new JMenu("Main menu");
		menuBar.add(mnMainMenu,BorderLayout.LINE_START);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		mntmNewMenuItem = new JMenuItem("Panel 1");
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
		
		mnSecondaryMenu = new JMenu("Secondary menu");
		menuBar.add(mnSecondaryMenu,BorderLayout.CENTER);
		
		mntmNewMenuItem_2 = new JMenuItem("New menu item");
		mnSecondaryMenu.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnSecondaryMenu.add(mntmNewMenuItem_3);
		
		itemConIcono = new JMenuItem("");
		itemConIcono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "HACER LO DEL PROFILE");
			}
		});
		itemConIcono.setBorder(null);
		itemConIcono.setBorderPainted(false);
		itemConIcono.setIcon(new ImageIcon(ViewCentralAnalista.class.getResource("/images/usuario (3).png")));
		menuBar.add(itemConIcono,BorderLayout.LINE_END);
	}

}
