package viewsEstudiante;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewsContancia extends JPanel {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewsContancia frame = new ViewsContancia();
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
	public ViewsContancia() {
		setBounds(100, 100, 649, 582);

		
		JButton btnNewButton = new JButton("Funciona papu 2");
		btnNewButton.setBounds(310, 231, 103, 23);
		add(btnNewButton);
		

	}

}
