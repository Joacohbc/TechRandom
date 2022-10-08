package view;

import javax.swing.JPanel;

public abstract class ViewPanel extends JPanel {
	public int HEIGHT = 500;
	public int WIDTH = 500;
	
	public ViewPanel() {
		// Le indico que no quiero usar ningun Layaout
		super();
		setLayout(null);
		setBounds(0, 0, WIDTH, HEIGHT);
	}
}
