package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.entities.Usuario;
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;

public class ViewCambiarContrasenia extends JPanel implements ViewMedida {
	private JPasswordField pfPassActual;
	private JPasswordField pfPassNuevo;
	private JLabel lblContraseaNueva;
	private JButton btnActualizar;

	public ViewCambiarContrasenia(Usuario usuario) {
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(null);

		pfPassActual = new JPasswordField();
		pfPassActual.setBounds(182, 14, 197, 21);
		add(pfPassActual);

		pfPassNuevo = new JPasswordField();
		pfPassNuevo.setBounds(182, 52, 197, 21);
		add(pfPassNuevo);

		JLabel lblNewLabel = new JLabel("Contraseña actual:");
		lblNewLabel.setBounds(12, 14, 137, 17);
		add(lblNewLabel);

		lblContraseaNueva = new JLabel("Contraseña nueva:");
		lblContraseaNueva.setBounds(12, 54, 137, 17);
		add(lblContraseaNueva);

		JCheckBox chkPassActual = new JCheckBox("");
		chkPassActual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pfPassActual.setEchoChar(chkPassActual.isSelected() ? '\u0000' : '*');
			}
		});
		chkPassActual.setBounds(387, 12, 24, 25);
		add(chkPassActual);

		JCheckBox chkPassNuevo = new JCheckBox("");
		chkPassNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pfPassNuevo.setEchoChar(chkPassNuevo.isSelected() ? '\u0000' : '*');
			}
		});
		chkPassNuevo.setBounds(387, 50, 24, 25);
		add(chkPassNuevo);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BeanIntances.user().updateContrasenia(usuario.getIdUsuario(),
							String.valueOf(pfPassActual.getPassword()), String.valueOf(pfPassNuevo.getPassword()));
					
					Mensajes.MostrarError("La contraseña fue actualizada con exito");
					pfPassActual.setText("");
					pfPassNuevo.setText("");
				} catch (InvalidEntityException ex) {
					Mensajes.MostrarError(ex.getMessage());
					ex.printStackTrace();
				} catch (Exception ex) {
					Mensajes.MostrarError("Ocurrio un error desconodio: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(182, 85, 197, 27);
		add(btnActualizar);
	}
}
