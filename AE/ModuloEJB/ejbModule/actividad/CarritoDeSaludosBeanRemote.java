package actividad;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CarritoDeSaludosBeanRemote {
	
	void agregarSaludo(String saludo);

	List<String> obtenerSaludo();

}
