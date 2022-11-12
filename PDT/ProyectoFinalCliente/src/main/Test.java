package main;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.entities.AccionConstancia;
import com.entities.Analista;
import com.entities.Constancia;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Itr;
import com.entities.TipoConstancia;
import com.entities.TipoEvento;
import com.entities.enums.Departamento;
import com.entities.enums.EstadoSolicitudes;
import com.entities.enums.EstadoUsuario;
import com.entities.enums.Genero;
import com.jgoodies.common.bean.Bean;

import beans.BeanIntances;
import components.VTextBox;
import validation.ValidacionesUsuario.TipoUsuarioDocumento;
import validation.ValidacionesUsuario.TipoUsuarioEmail;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VTextBox textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

//		System.out.println(BeanIntances.constancia().findAll());
//		
//		Constancia c = BeanIntances.constancia().findById(31l);
//		System.out.println(c);
//		
//		c.setEstado(EstadoSolicitudes.FINALIZADO);
//		BeanIntances.constancia().update(c);
//		
//		System.exit(1);
//				
//		TipoEvento tp = new TipoEvento();
//		tp.setIdTipoEvento(1l);
//		tp.setTipo("Prueba Presencial");
//		tp.setEstado(true);
//		
//		Evento evento = new Evento();
//		evento.setIdEvento(1l);
//		evento.setFechaInicio(LocalDate.of(2022, 11, 9));
//		evento.setFechaFin(LocalDate.of(2022, 11, 9));
//		evento.setTitulo("Prueba de DBC");
//		evento.setEstado(true);		
//		evento.setTipoEvento(tp);
//		
//		TipoConstancia tpc = new TipoConstancia();
//		tpc.setIdTipoConstancia(1l);
//		tpc.setTipo("Constancia de Prueba");
//		tpc.setPlantilla(new byte[] {1});
//		
//		Constancia constancia = new Constancia();
//		constancia.setDetalle("3er Try");
//		constancia.setEstudiante(BeanIntances.user().findById(Estudiante.class, 1l));
//		constancia.setEvento(evento);
//		constancia.setTipoConstancia(tpc);
//		
//		BeanIntances.constancia().solicitar(constancia);
		
//		System.out.println(BeanIntances.constancia().findAll());
		
		AccionConstancia ac = new AccionConstancia();
		ac.setAnalista(BeanIntances.user().findById(Analista.class, 2l));
		ac.setConstancia(BeanIntances.constancia().findById(6l));
		ac.setDetalle("Constancai dada a Joaquin");
		ac.setFechaHora(LocalDateTime.now());
		
		BeanIntances.constancia().updateEstado(6l, EstadoSolicitudes.FINALIZADO, ac);
//		
//		Itr itr = new Itr();
//		itr.setIdItr(1l);
//		itr.setNombre("ITRCS");
//		itr.setDepartamento(Departamento.DURAZNO);
//		itr.setEstado(true);
//
//		BeanIntances.itr().update(itr);
//		System.out.println(BeanIntances.itr().findAll());
//		System.exit(0);
//
//		try {
//			BeanIntances.itr().save(itr);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		System.exit(1);

//		Estudiante e = new Estudiante();
//		e.setNombres("Joaquin");
//		e.setApellidos("Genova Guerendiain");
//		e.setNombreUsuario("joaquin.genova");
//		e.setContrasena("12345678");
//		e.setEmail("joaquin.genova@estudiantes.utec.edu.uy");
//		e.setDocumento("5.362.666-3");
//		e.setTelefono("095112514");
//		e.setFecNacimiento(LocalDate.of(2003, 9, 8));
//		e.setDepartamento(Departamento.MONTEVIDEO);
//		e.setEstado(true);
//		e.setGenero(Genero.MASCULINO);
//		e.setGeneracion(2022);
//		e.setEstadoUsuario(EstadoUsuario.SIN_VALIDAR);
//		e.setLocalidad("Villa Española");
//		e.setItr(itr);

		// System.out.println(ValidacionesUsuario.ValidarUsuario(e).getErrorMessage());

//		System.out.println(BeanIntances.user().register(e, TipoUsuarioDocumento.URUGUAYO, TipoUsuarioEmail.UTEC));
//		Estudiante e1 = BeanIntances.user().login("joaquin.genova", "1234", Estudiante.class);
//		System.out.println(e1);

//		System.out.println(BeanIntances.user().findAll(Estudiante.class));
//		System.out.println(BeanIntances.user().findAll(Tutor.class));

		// BeanIntances.user().updateEstadoUsuario(6l, EstadoUsuario.SIN_VALIDAR);
		/*
		 * for (Estudiante e : BeanIntances.user().findAll(Estudiante.class,
		 * EstadoUsuario.SIN_VALIDAR, itr)) { System.out.println(e); }
		 */

		// Tutor t = new Tutor();
//		t.setNombres("William");
//		t.setApellidos("Machado");
//		t.setNombreUsuario("william.machado");
//		t.setContrasena("1234");
//		t.setEmail("william.machado@utec.edu.uy");
//		t.setDocumento("45634524");
//		t.setTelefono("096412341");
//		t.setFecNacimiento(LocalDate.of(1980, 10, 3));
//		t.setDepartamento(Departamento.MONTEVIDEO);
//		t.setGenero(Genero.MASCULINO);
//		t.setArea("Ingles");
//		t.setTipo(TipoTutor.TUTOR);
//		t.setEstado(true);
//		t.setLocalidad("Otro lugar que no se cual es");
//		t.setItr(itr);
//		
//		System.out.println(BeanIntances.user().register(t));
//		System.out.println(BeanIntances.user().login("william.machado", "1234", Tutor.class));

		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try { Test frame
		 * = new Test(); frame.setVisible(true); } catch (Exception e) {
		 * e.printStackTrace(); } } });
		 */
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new VTextBox();
		textField.setBounds(12, 12, 114, 21);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
