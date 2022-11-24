package viewsAnalista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.entities.Analista;
import com.entities.Estudiante;
import com.entities.Itr;
import com.entities.Tutor;
import com.entities.Usuario;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoSolicitudes;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;
import com.entities.enums.TipoTutor;
import com.toedter.calendar.JDateChooser;

import beans.BeanIntances;
import components.Roles;
import components.VTextBox;
import swingutils.Mensajes;
import validation.Formatos;
import validation.ValidacionesUsuario;
import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuarioEstudiante;
import validation.ValidacionesUsuarioTutor;
import validation.ValidationObject;
import views.ViewMedida;

public class ViewPerfil extends JPanel implements ViewMedida{

	private static final long serialVersionUID = 1L;
	
	private JDateChooser dtFechaDeNacimiento;
	private VTextBox textGeneracion;
	private VTextBox textArea;
	private VTextBox textLocalidad;
	private JComboBox<TipoTutor> cmbTipoTutor;
	private JComboBox<Genero> comboGenero;
	private JComboBox<Departamento> comboDepartamento;
	private JComboBox<Itr> comboItr;
	private JComboBox<Roles> comboRol;
	private JCheckBox chckbxUruguayo;
	private VTextBox textDocumento;
	private VTextBox textUsuario;
	private VTextBox textNombres;
	private VTextBox textApellidos;
	private VTextBox textTel;
	private VTextBox textMailUtec;
	private VTextBox textMailPersonal;
	
	/*
	 * Create the panel.
	 */
	public ViewPerfil(Usuario usu) {
		setBounds(0, 0, ANCHO_VIEW, LARGO_VIEW);
			
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Documento");
		lblNewLabel.setBounds(10, 118, 136, 13);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre Usuario");
		lblNewLabel_1.setBounds(12, 163, 136, 13);
		add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Nombres");
		lblNewLabel_3.setBounds(10, 188, 136, 13);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Apellidos");
		lblNewLabel_4.setBounds(10, 211, 136, 13);
		add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Fec. de Nacimiento");
		lblNewLabel_5.setBounds(10, 235, 136, 13);
		add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Telefono");
		lblNewLabel_6.setBounds(347, 118, 85, 13);
		add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("E-Mail");
		lblNewLabel_7.setBounds(347, 141, 85, 13);
		add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Localidad");
		lblNewLabel_8.setBounds(347, 189, 99, 13);
		add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Departamento");
		lblNewLabel_9.setBounds(347, 212, 99, 13);
		add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Genero");
		lblNewLabel_10.setBounds(347, 235, 99, 13);
		add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("ITR");
		lblNewLabel_11.setBounds(347, 258, 99, 13);
		add(lblNewLabel_11);

		textDocumento = new VTextBox();
		textDocumento.setBounds(138, 118, 110, 16);
		add(textDocumento);

		textUsuario = new VTextBox();
		textUsuario.setBounds(140, 163, 110, 16);
		textUsuario.setValidationFunc(text -> ValidacionesUsuario.validarNombreUsuario(text));
		add(textUsuario);

		textNombres = new VTextBox();
		textNombres.setBounds(138, 188, 110, 16);
		textNombres.setValidationFunc(text -> ValidacionesUsuario.validarNombres(text));
		add(textNombres);

		textApellidos = new VTextBox();
		textApellidos.setBounds(138, 211, 110, 16);
		textApellidos.setValidationFunc(text -> ValidacionesUsuario.validarApellido(text));
		add(textApellidos);

		textTel = new VTextBox();
		textTel.setBounds(439, 97, 110, 16);
		textTel.setBounds(439, 115, 110, 16);
		textTel.setValidationFunc(text -> ValidacionesUsuario.validarTelefono(text));
		add(textTel);

		textMailUtec = new VTextBox();
		textMailUtec.setBounds(440, 140, 110, 16);
		textMailUtec.setBounds(439, 137, 110, 16);
		textMailUtec.setValidationFunc(text -> ValidacionesUsuario.validarEmailUTEC(text));
		add(textMailUtec);
		
		JButton btnRegistrarme = new JButton("Modificar");
		btnRegistrarme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TipoUsuarioDocumento documento = chckbxUruguayo.isSelected() ? TipoUsuarioDocumento.URUGUAYO
							: TipoUsuarioDocumento.NO_URUGAUYO;

					Roles rol = ((Roles) comboRol.getSelectedItem());					
					LocalDate fecha = Formatos.ToLocalDate(dtFechaDeNacimiento.getDate());
					
					if (rol == Roles.ESTUDIANTE) {
						Estudiante estudiante = new Estudiante();
						estudiante.setIdUsuario(usu.getIdUsuario());
						estudiante.setNombres(textNombres.getText());
						estudiante.setApellidos(textApellidos.getText());
						estudiante.setNombreUsuario(textUsuario.getText());
						estudiante.setContrasena(usu.getContrasena());
						estudiante.setEmailUtec(textMailUtec.getText());
						estudiante.setEmailPersonal(textMailUtec.getText());
						estudiante.setDocumento(textDocumento.getText());
						estudiante.setTelefono(textTel.getText());
						estudiante.setFecNacimiento(fecha);
						estudiante.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
						estudiante.setGenero((Genero) comboGenero.getSelectedItem());
						estudiante.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
						estudiante.setLocalidad(textLocalidad.getText());
						estudiante.setItr((Itr) comboItr.getSelectedItem());
						estudiante.setEstado(true);
						estudiante.setGeneracion(Integer.parseInt(textGeneracion.getText()));

						ValidationObject error = ValidacionesUsuarioEstudiante.validarEstudianteSinContrasenia(estudiante, documento);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						BeanIntances.user().updateEstudiante(estudiante);
						Mensajes.MostrarExito("Se modificó correctamente el Estudiante " + estudiante.getNombres());
						return;
					}

					if (rol == Roles.TUTOR) {
						Tutor tutor = new Tutor();
						tutor.setIdUsuario(usu.getIdUsuario());
						tutor.setNombres(textNombres.getText());
						tutor.setApellidos(textApellidos.getText());
						tutor.setNombreUsuario(textUsuario.getText());
						tutor.setContrasena(usu.getContrasena());
						tutor.setEmailUtec(textMailUtec.getText());
						tutor.setEmailPersonal(textMailUtec.getText());
						tutor.setDocumento(textDocumento.getText());
						tutor.setTelefono(textTel.getText());
						tutor.setFecNacimiento(fecha);
						tutor.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
						tutor.setGenero((Genero) comboGenero.getSelectedItem());
						tutor.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
						tutor.setLocalidad(textLocalidad.getText());
						tutor.setItr((Itr) comboItr.getSelectedItem());
						tutor.setEstado(true);
						tutor.setArea(textArea.getText());
						tutor.setTipo((TipoTutor) cmbTipoTutor.getSelectedItem());

						ValidationObject error = ValidacionesUsuarioTutor.validarTutorSinContrasenia(tutor, documento);
						if (!error.isValid()) {
							Mensajes.MostrarError(error.getErrorMessage());
							return;
						}
						BeanIntances.user().updateTutor(tutor);
						Mensajes.MostrarExito("Se modificó correctamente el Tutor " + tutor.getNombres());
						return;
					}

					Analista analista = new Analista();
					analista.setIdUsuario(usu.getIdUsuario());
					analista.setNombres(textNombres.getText());
					analista.setApellidos(textApellidos.getText());
					analista.setNombreUsuario(textUsuario.getText());
					analista.setContrasena(usu.getContrasena());
					analista.setEmailUtec(textMailUtec.getText());
					analista.setEmailPersonal(textMailUtec.getText());
					analista.setDocumento(textDocumento.getText());
					analista.setTelefono(textTel.getText());
					analista.setFecNacimiento(fecha);
					analista.setDepartamento((Departamento) comboDepartamento.getSelectedItem());
					analista.setGenero((Genero) comboGenero.getSelectedItem());
					analista.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
					analista.setLocalidad(textLocalidad.getText());
					analista.setItr((Itr) comboItr.getSelectedItem());
					analista.setEstado(true);

					ValidationObject error = ValidacionesUsuario.ValidarUsuarioSinContrasenia(analista, documento);
					if (!error.isValid()) {
						Mensajes.MostrarError(error.getErrorMessage());
						return;
					}
					BeanIntances.user().updateAnalista(analista);
					Mensajes.MostrarExito("Se modificó correctamente el Analista " + analista.getNombres());
				} catch (Exception ex) {
					Mensajes.MostrarError(ex.getMessage());
				}

			}
		});
		
		
		
		btnRegistrarme.setBounds(440, 388, 110, 21);

		add(btnRegistrarme);

		JLabel lblNewLabel_12 = new JLabel("Datos personales");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(201, 39, 218, 13);
		add(lblNewLabel_12);

		comboDepartamento = new JComboBox<Departamento>();
		comboDepartamento.setBounds(439, 209, 110, 17);	
		add(comboDepartamento);
		
		comboGenero = new JComboBox<Genero>();
		comboGenero.setBounds(439, 232, 110, 17);
		add(comboGenero);
		

		dtFechaDeNacimiento = new JDateChooser();
		dtFechaDeNacimiento.setBounds(138, 229, 110, 19);
		add(dtFechaDeNacimiento);
		
		JLabel lblNewLabel_13 = new JLabel("Rol");
		lblNewLabel_13.setBounds(10, 304, 45, 13);
		add(lblNewLabel_13);

		JLabel lblNewLabel_6_1 = new JLabel("Generacion");
		lblNewLabel_6_1.setBounds(346, 304, 94, 13);
		add(lblNewLabel_6_1);

		textGeneracion = new VTextBox();
		textGeneracion.setBounds(438, 300, 111, 16);
		textGeneracion.setEnabled(false);
		textGeneracion.setValidationFunc(text -> ValidacionesUsuarioEstudiante.validarGeneracion(text));
		add(textGeneracion);

		JLabel lblNewLabel_6_2 = new JLabel("Area");
		lblNewLabel_6_2.setBounds(346, 331, 94, 13);
		add(lblNewLabel_6_2);

		JLabel lblNewLabel_7_2 = new JLabel("Tipo Tutor");
		lblNewLabel_7_2.setBounds(346, 353, 94, 13);
		add(lblNewLabel_7_2);

		cmbTipoTutor = new JComboBox<TipoTutor>();
		cmbTipoTutor.setEnabled(false);
		cmbTipoTutor.setBounds(438, 349, 111, 16);
		add(cmbTipoTutor);
		
		textArea = new VTextBox();
		textArea.setEnabled(false);
		textArea.setBounds(438, 327, 111, 16);
		textArea.setValidationFunc(text -> ValidacionesUsuarioTutor.validarArea(text));
		add(textArea);

		comboRol = new JComboBox<Roles>();
		comboRol.setBounds(136, 300, 116, 21);
		add(comboRol);

		chckbxUruguayo = new JCheckBox("Uruguayo");
		chckbxUruguayo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxUruguayo.isSelected()) {
					textDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoUruguayo(text));
				} else {
					textDocumento.setValidationFunc(text -> ValidacionesUsuario.validarDocumentoNoUruguayo(text));
				}
				textDocumento.grabFocus();
			}
		});
		chckbxUruguayo.setSelected(true);
		chckbxUruguayo.setBounds(211, 141, 87, 13);
		add(chckbxUruguayo);

		comboItr = new JComboBox<Itr>();
		comboItr.setBounds(438, 256, 111, 17);
		add(comboItr);

		textLocalidad = new VTextBox();
		textLocalidad.setBounds(439, 186, 110, 16);
		textLocalidad.setValidationFunc(text -> ValidacionesUsuario.validarLocalidad(text));
		add(textLocalidad);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 290, 546, 2);
		add(separator);
		
		JLabel lblNewLabel_7_1 = new JLabel("E-Mail Personal");
		lblNewLabel_7_1.setBounds(347, 163, 85, 13);
		add(lblNewLabel_7_1);
		
		textMailPersonal = new VTextBox();
		textMailPersonal.setText((String) null);
		textMailPersonal.setBounds(439, 159, 110, 16);
		add(textMailPersonal);
		
		//Llamo procedimiento que carga los combos de los paneles
		cargar(usu);
	}

	private void cargar(Usuario usu) {
		textDocumento.setText(usu.getDocumento());

		textUsuario.setText(usu.getNombreUsuario());
		textUsuario.setEditable(false);

		if(ValidacionesUsuario.validarDocumentoUruguayo(usu.getDocumento()).getErrorMessage() == "") {
			chckbxUruguayo.setSelected(true);
		}else {
			chckbxUruguayo.setSelected(false);
		}
		
		textNombres.setText(usu.getNombres());
		textApellidos.setText(usu.getApellidos());
		textMailPersonal.setText(usu.getEmailPersonal());
		textMailUtec.setText(usu.getEmailUtec());
		textMailUtec.setEditable(false);
		
		textTel.setText(usu.getTelefono());
		textLocalidad.setText(usu.getLocalidad());

		Date date = Date.from(usu.getFecNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
		dtFechaDeNacimiento.setDate(date);
		
		for (Departamento departamento : Departamento.values()) {
			comboDepartamento.addItem(departamento);
		}
		comboDepartamento.setSelectedItem(usu.getDepartamento());
		
		for (Genero genero : Genero.values()) {
			comboGenero.addItem(genero);
		}
		comboGenero.setSelectedItem(usu.getGenero());
			
		List<Itr> itrs = BeanIntances.itr().findAll();
		for (Itr itr : itrs) {
			comboItr.addItem(itr);
		}
		comboItr.setSelectedItem(usu.getItr());
	
		if(usu instanceof Estudiante) {
			comboRol.addItem(Roles.ESTUDIANTE);
			
			Estudiante estudiante = (Estudiante) usu;
			textGeneracion.setText(estudiante.getGeneracion().toString());
			textGeneracion.setEnabled(true);
			
			// Deshabilito cosas de Tutor
			textArea.setEnabled(false);
			cmbTipoTutor.setEnabled(false);
			return;
		}
		
		if(usu instanceof Analista) {
			comboRol.addItem(Roles.ANALISTA);
			
			// Deshabilito cosas de Estudiante y Tutor
			textArea.setEnabled(false);
			cmbTipoTutor.setEnabled(false);
			textGeneracion.setEnabled(false);
			return;
		}
		
		if(usu instanceof Tutor) {
			comboRol.addItem(Roles.TUTOR);

			Tutor tutor = (Tutor) usu;
			for (TipoTutor tipo : TipoTutor.values()) {
				cmbTipoTutor.addItem(tipo);
			}
			
			cmbTipoTutor.setSelectedItem(tutor.getTipo());
			cmbTipoTutor.setEnabled(true);

			textArea.setText(tutor.getArea());
			textArea.setEnabled(true);
			
			// Deshabilito cosas de Estudiante
			textGeneracion.setEnabled(false);
			return;
		}
		

	}
}
