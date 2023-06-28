package com.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ingresar")
public class IngresarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IngresarServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Aqui recupero la session y obtengo el usuario que se quiere invresar y ya se valido
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Usuario.addUser(usuario);
		request.removeAttribute("usu");
		request.setAttribute("id", usuario.getId());
		request.setAttribute("nombre", usuario.getNombre());
		request.setAttribute("edad", usuario.getEdad());
		request.setAttribute("direccion", usuario.getDireccion());
		request.setAttribute("telefono", usuario.getTelefono());
		request.getRequestDispatcher("MostrarDato.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
