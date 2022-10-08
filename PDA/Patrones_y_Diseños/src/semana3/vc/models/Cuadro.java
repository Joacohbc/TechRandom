package semana3.vc.models;

public class Cuadro {

	private Long id;
	private String autor;
	private String nombre;
	private Museo museo;

	public Cuadro() {
		this.id = null;
		this.autor = "";
		this.nombre = "";
		this.museo = null;
	}

	public Cuadro(Long id, String autor, String nombre, Museo museo) {
		super();
		this.id = id;
		this.autor = autor;
		this.nombre = nombre;
		this.museo = museo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Museo getMuseo() {
		return museo;
	}

	public void setMuseo(Museo museo) {
		this.museo = museo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "id: " + this.id + " Nombre: " + this.nombre + " Dueño: " + this.autor;
	}

}
