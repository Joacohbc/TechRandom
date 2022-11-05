package beans;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.models.Funcionalidad;
import com.services.FuncionalidadBeanRemote;
import com.services.RolBeanRemote;
import com.services.ControlSesionesSingletonRemote;
import com.services.UsuarioBeanRemote;

public class BeanIntances {

	static {
		try {
			usuarioBean = (UsuarioBeanRemote)  InitialContext.doLookup("ejb:/Semana4Servidor/UsuarioBean!com.services.UsuarioBeanRemote");
			funcionalidadBean = (FuncionalidadBeanRemote) InitialContext.doLookup("ejb:/Semana4Servidor/FuncionalidadBean!com.services.FuncionalidadBeanRemote");
			rolBean = (RolBeanRemote) InitialContext.doLookup("ejb:/Semana4Servidor/RolBean!com.services.RolBeanRemote");
			sessionBean = (ControlSesionesSingletonRemote) InitialContext.doLookup("ejb:/Semana4Servidor/ControlSesionesSingleton!com.services.ControlSesionesSingletonRemote");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	
	private static UsuarioBeanRemote usuarioBean;
	private static FuncionalidadBeanRemote funcionalidadBean;
	private static RolBeanRemote rolBean;
	private static ControlSesionesSingletonRemote sessionBean;
	
	public static ControlSesionesSingletonRemote sesion() {
		return sessionBean;
	}
	
	public static UsuarioBeanRemote usuario() {
		return usuarioBean;
	}
	
	public static FuncionalidadBeanRemote funcionalidad() {
		return funcionalidadBean;
	}

	public static RolBeanRemote rol() {
		return rolBean;
	}
}
