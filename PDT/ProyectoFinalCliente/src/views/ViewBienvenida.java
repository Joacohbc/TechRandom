package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Component;

public class ViewBienvenida extends JPanel implements ViewMedida{

	/**
	 * Create the panel.
	 */
	public ViewBienvenida() {
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(null);
		
		JTextArea txtrbienvenidosAlSistema = new JTextArea();
		txtrbienvenidosAlSistema.setAlignmentX(Component.LEFT_ALIGNMENT);
		txtrbienvenidosAlSistema.setEditable(false);
		txtrbienvenidosAlSistema.setBackground(UIManager.getColor("Panel.background"));
		txtrbienvenidosAlSistema.setFont(new Font("Gill Sans MT Condensed", Font.ITALIC, 60));
		txtrbienvenidosAlSistema.setLineWrap(true);
		txtrbienvenidosAlSistema.setText("¡Bienvenidos al Sistema de Gestion de la UTEC!");
		txtrbienvenidosAlSistema.setBounds(10, 231, 497, 142);
		add(txtrbienvenidosAlSistema);
		
	}
}
