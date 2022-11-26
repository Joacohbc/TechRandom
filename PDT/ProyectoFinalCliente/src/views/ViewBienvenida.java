package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.jgoodies.forms.layout.CellConstraints.Alignment;

import java.awt.Component;
import javax.swing.SwingConstants;

public class ViewBienvenida extends JPanel implements ViewMedida{

	/**
	 * Create the panel.
	 */
	public ViewBienvenida() {
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(new BorderLayout());
		
		JLabel txtrbienvenidosAlSistema = new JLabel();
		txtrbienvenidosAlSistema.setHorizontalAlignment(SwingConstants.CENTER);
		txtrbienvenidosAlSistema.setBackground(UIManager.getColor("Panel.background"));
		txtrbienvenidosAlSistema.setFont(new Font("Gill Sans MT Condensed", Font.ITALIC, 60));
		txtrbienvenidosAlSistema.setText("<html>¡Bienvenidos al Sistema <br> de Gestion de la UTEC!</html>");
		txtrbienvenidosAlSistema.setBounds(10, 231, 497, 142);
		add(txtrbienvenidosAlSistema, BorderLayout.CENTER);
		
	}
}
