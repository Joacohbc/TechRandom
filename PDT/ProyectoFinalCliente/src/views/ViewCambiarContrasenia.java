package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.entities.Usuario;
import com.exceptions.InvalidEntityException;

import beans.BeanIntances;
import swingutils.Mensajes;
import javax.swing.ImageIcon;

public class ViewCambiarContrasenia extends JPanel implements ViewMedida {
	private JPasswordField pfPassActual;
	private JPasswordField pfPassNuevo;
	private JLabel lblContraseaNueva;
	private JButton btnActualizar;
	private JButton btnPassActual_2;
	private JButton btnPassNuevo;
	
	public ViewCambiarContrasenia(Usuario usuario, JFrame padre) {
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Contraseña actual:");
		lblNewLabel.setBounds(12, 14, 137, 17);
		add(lblNewLabel);

		lblContraseaNueva = new JLabel("Contraseña nueva:");
		lblContraseaNueva.setBounds(12, 54, 137, 17);
		add(lblContraseaNueva);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(Mensajes.MostrarSioNo("¿Esta seguro que quiere cambiar la contraseña?") == Mensajes.OPCION_NO) 
						return;
					
					BeanIntances.user().updateContrasenia(usuario.getIdUsuario(),
							String.valueOf(pfPassActual.getPassword()), String.valueOf(pfPassNuevo.getPassword()));
					
					Mensajes.MostrarExito("La contraseña fue actualizada con exito");
					pfPassActual.setText("");
					pfPassNuevo.setText("");
					
					padre.dispose();
					new Login().setVisible(true);
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
		
		JButton btnPassActual = new JButton("");
		btnPassActual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pfPassActual.getEchoChar() == '*') {					
					pfPassActual.setEchoChar('\u0000');
					btnPassActual.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/hidden.png")));
				} else{
					pfPassActual.setEchoChar('*');
					btnPassActual.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/eye.png")));
				}
			}
		});
		btnPassActual.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/hidden.png")));
		btnPassActual.setBorderPainted(false);
		btnPassActual.setContentAreaFilled(false);
		btnPassActual.setFocusPainted(false);
		btnPassActual.setOpaque(false);
		btnPassActual.setBounds(380, 10, 27, 25);
		add(btnPassActual);
		
		btnPassNuevo = new JButton("");
		btnPassNuevo.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/hidden.png")));
		btnPassNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pfPassNuevo.getEchoChar() == '*') {					
					pfPassNuevo.setEchoChar('\u0000');
					btnPassNuevo.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/hidden.png")));
				} else{
					pfPassNuevo.setEchoChar('*');
					btnPassNuevo.setIcon(new ImageIcon(ViewCambiarContrasenia.class.getResource("/images/eye.png")));
				}
			}
		});
		btnPassNuevo.setOpaque(false);
		btnPassNuevo.setFocusPainted(false);
		btnPassNuevo.setContentAreaFilled(false);
		btnPassNuevo.setBorderPainted(false);
		btnPassNuevo.setBounds(380, 46, 27, 25);
		add(btnPassNuevo);
		
		btnPassActual_2 = new JButton("");
		btnPassActual_2.setOpaque(false);
		btnPassActual_2.setFocusPainted(false);
		btnPassActual_2.setContentAreaFilled(false);
		btnPassActual_2.setBorderPainted(false);
		btnPassActual_2.setBounds(391, 49, 50, 25);
		add(btnPassActual_2);
		
				pfPassActual = new JPasswordField();
				pfPassActual.setBounds(182, 10, 197, 25);
				add(pfPassActual);
				
						pfPassNuevo = new JPasswordField();
						pfPassNuevo.setBounds(182, 46, 197, 25);
						add(pfPassNuevo);
	}
}
