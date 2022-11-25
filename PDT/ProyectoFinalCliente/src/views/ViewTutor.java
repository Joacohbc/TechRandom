package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.Tutor;

public class ViewTutor extends JFrame {

	private JPanel contentPane;
	private final ViewAsistencias panelAsistencias;


	/**
	 * Create the frame.
	 */
	public ViewTutor(Tutor tutor) {
		setTitle("Tutor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 535);
		panelAsistencias = new ViewAsistencias(tutor);
		setContentPane(panelAsistencias);

	}

}
