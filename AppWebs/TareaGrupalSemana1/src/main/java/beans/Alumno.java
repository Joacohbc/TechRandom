package beans;

public class Alumno {
	Integer ci;
	String nombre;
	String apellido;
	String codigoCarrera;
	String direccion;
	Integer anioNacimiento;
	String archivoFoto;
	
	public Alumno() {
		super();
	}

	public Alumno(Integer ci, String nombre, String apellido, String codigoCarrera, String direccion,
			Integer anioNacimiento, String archivoFoto) {
		super();
		this.ci = ci;
		this.nombre = nombre;
		this.apellido = apellido;
		this.codigoCarrera = codigoCarrera;
		this.direccion = direccion;
		this.anioNacimiento = anioNacimiento;
		this.archivoFoto = archivoFoto;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCodigoCarrera() {
		return codigoCarrera;
	}

	public void setCodigoCarrera(String dodigoCarrera) {
		this.codigoCarrera = dodigoCarrera;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(Integer anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public String getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(String archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alumno [ci=");
		builder.append(ci);
		builder.append(",\n nombre=");
		builder.append(nombre);
		builder.append(",\n apellido=");
		builder.append(apellido);
		builder.append(",\n codigoCarrera=");
		builder.append(codigoCarrera);
		builder.append(",\n direccion=");
		builder.append(direccion);
		builder.append(",\n anioNacimiento=");
		builder.append(anioNacimiento);
		builder.append(",\n archivoFoto=");
		builder.append(archivoFoto);
		builder.append("]");
		return builder.toString();
	}

}
