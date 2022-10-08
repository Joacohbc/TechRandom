package actividad;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.services.SinEstadoBean;

/**
 * Session Bean implementation class CarritoDeSaludosBean
 */
@Stateful
public class CarritoDeSaludosBean implements CarritoDeSaludosBeanRemote {

	@EJB
	private SinEstadoBean saludoBean;

	/**
	 * Default constructor.
	 */
	public CarritoDeSaludosBean() {
	}

	private List<String> saludos = new ArrayList<String>();

	@Override
	public void agregarSaludo(String saludo) {
		saludos.add(saludoBean.saludar(saludo));
	}

	@Override
	public List<String> obtenerSaludo() {
		return saludos;
	}

}
