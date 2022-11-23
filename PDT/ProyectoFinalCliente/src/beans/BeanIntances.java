package beans;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.services.ConstanciaBeanRemote;
import com.services.EventoBeanRemote;
import com.services.ItrBeanRemote;
import com.services.TipoConstanciaBeanRemote;
import com.services.UsuarioBeanRemote;

public class BeanIntances {

	static {
		try {
			usuarioBean = InitialContext.doLookup("/ProyectoFinalServidor/UsuarioBean!com.services.UsuarioBeanRemote");
			itrBean = InitialContext.doLookup("/ProyectoFinalServidor/ItrBean!com.services.ItrBeanRemote");
			contanciaBean = InitialContext.doLookup("/ProyectoFinalServidor/ConstanciaBean!com.services.ConstanciaBeanRemote");
			EventoBean = InitialContext.doLookup("/ProyectoFinalServidor/EventoBean!com.services.EventoBeanRemote");
			tipoConstancia = InitialContext.doLookup("/ProyectoFinalServidor/TipoConstanciaBean!com.services.TipoConstanciaBeanRemote");
			
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static UsuarioBeanRemote usuarioBean;
	private static ItrBeanRemote itrBean;
	private static ConstanciaBeanRemote contanciaBean;
	private static EventoBeanRemote EventoBean;
	private static TipoConstanciaBeanRemote tipoConstancia;
	
	public static UsuarioBeanRemote user() {
		return usuarioBean;
	}

	public static ItrBeanRemote itr() {
		return itrBean;
	}
	
	public static ConstanciaBeanRemote constancia() {
		return contanciaBean;
	}
	
	public static EventoBeanRemote evento() {
		return EventoBean;
	}
	public static TipoConstanciaBeanRemote tipoConstancia() {
		return tipoConstancia;
	}

}
