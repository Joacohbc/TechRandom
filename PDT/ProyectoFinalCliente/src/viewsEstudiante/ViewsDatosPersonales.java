package viewsEstudiante;

import javax.swing.JPanel;

import views.ViewMedida;
import javax.swing.JButton;

public class ViewsDatosPersonales extends JPanel implements ViewMedida {

	/**
	 * Create the panel.
	 */
	public ViewsDatosPersonales() {
		
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Funciona papu");
		btnNewButton.setBounds(310, 231, 103, 23);
		add(btnNewButton);

	}

}
