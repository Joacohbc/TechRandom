package viewsEstudiante;

import javax.swing.JPanel;

import views.ViewMedida;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ViewsDatosPersonales extends JPanel implements ViewMedida {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ViewsDatosPersonales() {
		
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(null);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(75, 440, 103, 23);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(120, 71, 144, 31);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Aplicar Cambios ");
		btnNewButton_1.setBounds(215, 440, 115, 23);
		add(btnNewButton_1);

	}
}
