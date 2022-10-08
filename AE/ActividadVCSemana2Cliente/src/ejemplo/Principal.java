package ejemplo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.servicios.CarritoDeComprasBeanRemote;
import com.servicios.GestionComprasBeanRemote;

public class Principal {

	private static void listar(List<String> carrito) {
		String productos = "Carrito de Productos:\n";
		for (String compra : carrito) {
			productos += "- " + compra + "\n";
		}
		productos += "Cantidad de productos: " + carrito.size();
		System.out.println(productos);
	}

	public static void main(String[] args) throws NamingException {

		// Invocamos los Beans de Carrito y Compra
		CarritoDeComprasBeanRemote carritoBean = (CarritoDeComprasBeanRemote) InitialContext
				.doLookup("/ActividadVCSemana2Beans/CarritoDeComprasBean!com.servicios.CarritoDeComprasBeanRemote");

		GestionComprasBeanRemote gestionBean = (GestionComprasBeanRemote) InitialContext
				.doLookup("/ActividadVCSemana2Beans/GestionComprasBean!com.servicios.GestionComprasBeanRemote");

		// Problema 1
		// Nos tira un error al tratar user Clases de Java noramles en metodos de los
		// EJBs

//		carritoBean.addCompra(new Compra("iPad Pro",550));
//		carritoBean.addCompra(new Compra("iPhone 14 Pro",1550));
//		carritoBean.addCompra(new Compra("Macb¿Book Pro",3000));
//		carritoBean.addCompra(new Compra("Mabook Air",2200));

		// Agrego la compra la Carrito
		carritoBean.addCompra("iPad Pro");
		carritoBean.addCompra("iPhone 14 Pro");
		carritoBean.addCompra("MacBook Pro");
		carritoBean.addCompra("MacBook M1 Pro");

		// Listamos el carrito
		listar(carritoBean.listarCompras());
		System.out.println();

		// Mostramos las estadisticas
		System.out.println(gestionBean.mostrarEstadistica());
		System.out.println();

		// Generamos la compra
		System.out.println(gestionBean.comprar(carritoBean.listarCompras()));
		System.out.println();

		// Borramos un elemento del carrito
		carritoBean.removeCompra("iPad Pro");
		listar(carritoBean.listarCompras());
		System.out.println();

		// Problema 2
		// Si pedimos los atributos mediante su acceso public no retorna los vaores
		// Solo funciona con Getters
		System.out.println(gestionBean.mostrarEstadistica());
	}

}
