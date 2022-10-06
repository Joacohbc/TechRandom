package semana2;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import actividad.CarritoDeSaludosBeanRemote;

public class ClienteCarrito {
	public static void main(String[] args) throws NamingException {
		
		CarritoDeSaludosBeanRemote bean = (CarritoDeSaludosBeanRemote) InitialContext
				.doLookup("ModuloEJB/CarritoDeSaludosBean!actividad.CarritoDeSaludosBeanRemote");
	
		bean.agregarSaludo("A");
		bean.agregarSaludo("B");
		bean.agregarSaludo("C");
		System.out.println(bean.obtenerSaludo());
	}
}
