package ejemplo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Area;
import com.entities.Salon;
import com.entities.TipoSalon;
import com.exceptions.EntityAlreadyExistsException;
import com.exceptions.ServiceException;
import com.services.AreaBeanRemote;
import com.services.MaterialBeanRemote;
import com.services.SalonBeanRemote;

public class Principal {

	private static Area newArea(AreaBeanRemote areaBean, String nombre) throws ServiceException {
		Area area = areaBean.save(new Area(nombre));
		System.out.println("Se dio de alta el Area de " + area.getNombre() + " con el ID: " + area.getId());
		return area;
	}

	private static void removeArea(AreaBeanRemote areaBean, Long id) throws ServiceException {
		Area area = areaBean.remove(id);
		System.out.println("Se dio de baja el Area de " + area.getNombre() + " con el ID: " + area.getId());
	}

	private static Salon newSalon(SalonBeanRemote salonBean, Salon newSalon) throws ServiceException {
		Salon salon = salonBean.save(newSalon);
		System.out.println("Se dio de alta el Salon de " + salon.getNombre() + " con el ID: " + salon.getId()
				+ " para el Area: " + salon.getArea().getNombre());
		return salon;
	}

	private static Salon removeSalon(SalonBeanRemote salonBean, Long id) throws ServiceException {
		Salon salon = salonBean.remove(id);
		System.out.println("Se dio de baja el Salon de " + salon.getNombre() + " con el ID: " + salon.getId()
				+ " para el Area: " + salon.getArea().getNombre());
		return salon;
	}

	private static void list(String title, List<? extends Object> list) {
		System.out.println();
		System.out.println(title);
		for (Object object : list) {
			System.out.println("- " + object.toString());
		}
		System.out.println();
	}

	public static void main(String[] args) {

		// Invocamos los Beans de Carrito y Compra
		AreaBeanRemote areaBean;
		SalonBeanRemote salonBean;
		MaterialBeanRemote materialBean;
		try {
			areaBean = (AreaBeanRemote) InitialContext.doLookup("Semana3Servidor/AreaBean!com.services.AreaBeanRemote");
			salonBean = (SalonBeanRemote) InitialContext
					.doLookup("Semana3Servidor/SalonBean!com.services.SalonBeanRemote");
			materialBean = (MaterialBeanRemote) InitialContext
					.doLookup("Semana3Servidor/MaterialBean!com.services.MaterialBeanRemote");
		} catch (NamingException e) {
			e.printStackTrace();
			System.exit(1);
			return;
		}

//		System.exit(0);

		try {
			
			System.out.println("> ALTAS <");
			Area lti = newArea(areaBean, "LTI");
			Area bio = newArea(areaBean, "Biomédicas");
			Area iagro = newArea(areaBean, "IAgro");
			Area aguaReno = newArea(areaBean, "Aguas renovables");

			list("Areas disponibles:", areaBean.findAll());

			Salon rob = new Salon();
			rob.setNombre("Robótica");
			rob.setCapMax(20);
			rob.setPracticas(false);
			rob.setTipo(TipoSalon.COMUN);
			rob.setArea(areaBean.findByName("LTI"));
			rob = newSalon(salonBean, rob);

			Salon aula3 = new Salon();
			aula3.setNombre("Aula de clases 3");
			aula3.setCapMax(35);
			aula3.setPracticas(true);
			aula3.setTipo(TipoSalon.COMUN);
			aula3.setArea(areaBean.findByName("LTI"));
			aula3 = newSalon(salonBean, aula3);

			Salon servidores = new Salon();
			servidores.setNombre("Sala de servidores");
			servidores.setCapMax(10);
			servidores.setPracticas(true);
			servidores.setTipo(TipoSalon.COMUN);
			servidores.setArea(areaBean.findByName("LTI"));
			servidores = newSalon(salonBean, servidores);

			list("Salones diponibles en LTI:", salonBean.findByArea("LTI"));

			Salon lab1 = new Salon();
			lab1.setNombre("Labortorio 1");
			lab1.setCapMax(20);
			lab1.setPracticas(true);
			lab1.setTipo(TipoSalon.LABORATORIO);
			lab1.setArea(areaBean.findByName("Biomédicas"));
			lab1 = newSalon(salonBean, lab1);

			Salon aula2 = new Salon();
			aula2.setNombre("Aula de clases 2");
			aula2.setCapMax(30);
			aula2.setPracticas(false);
			aula2.setTipo(TipoSalon.COMUN);
			aula2.setArea(areaBean.findByName("Biomédicas"));
			aula2 = newSalon(salonBean, aula2);

			Salon deposito = new Salon();
			deposito.setNombre("Depósito de materiales");
			deposito.setCapMax(50);
			deposito.setPracticas(false);
			deposito.setTipo(TipoSalon.COMUN);
			deposito.setArea(areaBean.findByName("Biomédicas"));
			deposito = newSalon(salonBean, deposito);

			list("Salones diponibles en Biomédicas:", salonBean.findByArea("Biomédicas"));

			Salon lab2 = new Salon();
			lab2.setNombre("Labortorio 2");
			lab2.setCapMax(20);
			lab2.setPracticas(true);
			lab2.setTipo(TipoSalon.LABORATORIO);
			lab2.setArea(areaBean.findByName("IAgro"));
			lab2 = newSalon(salonBean, lab2);

			Salon aula1 = new Salon();
			aula1.setNombre("Aula de clases 1");
			aula1.setCapMax(30);
			aula1.setPracticas(false);
			aula1.setTipo(TipoSalon.COMUN);
			aula1.setArea(areaBean.findByName("IAgro"));
			aula1 = newSalon(salonBean, aula1);

			Salon galpon = new Salon();
			galpon.setNombre("Galpón de materiales");
			galpon.setCapMax(50);
			galpon.setPracticas(false);
			galpon.setTipo(TipoSalon.COMUN);
			galpon.setArea(areaBean.findByName("IAgro"));
			galpon = newSalon(salonBean, galpon);

			list("Salones diponibles en IAgro:", salonBean.findByArea("IAgro"));
			
			System.out.println("> BAJAS <");
			for (Salon salon : salonBean.findAll()) {
				removeSalon(salonBean, salon.getId());
			}
			
			for (Area area : areaBean.findAll()) {
				removeArea(areaBean, area.getId());
			}
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());

		} catch (EntityAlreadyExistsException e) {
			System.out.println(e.getMessage());
		}

	}

}
