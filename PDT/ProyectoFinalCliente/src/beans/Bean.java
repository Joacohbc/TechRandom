package beans;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.services.UsuarioBeanRemote;

public class Bean {

	static {
		try {
			usuarioBean = InitialContext.doLookup("/ProyectoFinalServidor/UsuarioBean!com.services.UsuarioBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private static UsuarioBeanRemote usuarioBean;
	
	public static UsuarioBeanRemote user() {
		return usuarioBean;
	}
}
