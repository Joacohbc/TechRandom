package beans;

import java.util.ArrayList;

public class BuscarAlumnos {
	private static ArrayList<Alumno> alumnos;

	private static String[] nombres = new String[] { "Hugo", "Daniel", "Martin", "Pablo", "Alejandro", "Carmen",
			"Josefa", "Isabel", "Laura", "Teresa" };

	private static String[] apellidos = new String[] { "Pereyra", "Rojas", "Molina", "Castro", "Ortiz", "Silva", "Luna",
			"Cabrera", "Morales", "Vega" };

	private static String[] calles = new String[] { "Romero", "Sosa", "Alvarez", "Torres", "Ruiz", "Ramirez", "Flores",
			"Acosta", "Benitez", "Medina" };

	private static int numAleatorio(int numMax) {
		int numero = (int) (Math.random() * numMax + 1);
		return numero;
	}

	private static void initAlumnos() {
		if (alumnos != null) {
			// ya esta inicializado
			return;
		}
		// creamos lista de alumnos
		alumnos = new ArrayList<Alumno>();
		for (int i = 1; i <= 10; i++) {
			Integer ci = i;
			String nombre = nombres[i - 1];
			String apellido = apellidos[i - 1];
			String dodigoCarrera = "LTI";
			String direccion = calles[i-1] + " " + numAleatorio(10000);
			Integer anioNacimiento = 2020 - (18 + numAleatorio(15));
			String archivoFoto = i + ".jpg";
			Alumno alumno = new Alumno(ci, nombre, apellido, dodigoCarrera, direccion, anioNacimiento, archivoFoto);
			alumnos.add(alumno);
		}
		

	}

	public BuscarAlumnos() {
		super();
		initAlumnos();
	}

	
	public Alumno buscarAlumno(String nombre,String apellido,Integer ci) {
		
		for(Alumno alumno: alumnos) {
			boolean ok=true;
			//vemos criterio por nom
			if (apellido!=null && !apellido.equals("")) {
				if (!alumno.getApellido().toUpperCase().contains(apellido.toUpperCase())) {
					ok=false;
				}
			}
			if (nombre!=null && !nombre.equals("")) {
				if (!alumno.getNombre().toUpperCase().contains(nombre.toUpperCase())) {
					ok=false;
				}
			}
			if (ci!=null && ci.intValue()>0) {
				if (ci!=ci.intValue()) {
					ok=false;
				}
			}
			if (ok) {
				return alumno;
			}
		}
		//no encontramos
		//devolvemos alumno vacio
		return new Alumno();
		
	}
	
	public static void main(String [] args) {
		BuscarAlumnos buscarAlumnos=new BuscarAlumnos();
		Alumno alumno=buscarAlumnos.buscarAlumno(null, "", 1);
		System.out.println(alumno.getApellido());
	}
}
