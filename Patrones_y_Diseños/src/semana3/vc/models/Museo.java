package semana3.vc.models;

public class Museo {
	private Long id;
	private String nombre;
	private String direccion;

	public Museo() {

	}

	public Museo(Long id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;

	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}
	
	public String getDireccion() {
		return direccion;
		
	}
	
	public void setDireccion (String Direccion) {
		this.direccion = direccion;
	}


}
