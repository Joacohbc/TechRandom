package ejemplo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.ConEstadoRemote;
import com.services.SinEstadoBeanRemote;
import com.services.SingletonBeanRemote;

public class Cliente1 {
	
	public static void main(String[] args) {

		try {
			
			// Invoco un Bean remoto con estado
			ConEstadoRemote conEstadoRemote = (ConEstadoRemote) InitialContext
					.doLookup("ModuloEJB/ConEstado!com.services.ConEstadoRemote");
			
			// Le asigno un valor a su estado
			conEstadoRemote.guardarPalabra("Hola Mundo!");
			
			// Invoco
			SinEstadoBeanRemote sinEstadoRemote = (SinEstadoBeanRemote) InitialContext
					.doLookup("ModuloEJB/SinEstadoBean!com.services.SinEstadoBeanRemote");

			System.out.println(sinEstadoRemote.saludar("TechRandom"));
			
			SingletonBeanRemote singletonBeanRemote = (SingletonBeanRemote) InitialContext
					.doLookup("ModuloEJB/SingletonBean!com.services.SingletonBeanRemote");
			
			System.out.println(singletonBeanRemote.verValorCompartido());
			System.out.println(conEstadoRemote.muestraPalabra());
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
