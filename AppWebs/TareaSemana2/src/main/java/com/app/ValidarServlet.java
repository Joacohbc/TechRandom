package com.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/validar")
public class ValidarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nombre");
		String edad = request.getParameter("edad");
		String dir = request.getParameter("direccion");
		String tel = request.getParameter("tel");
		
		// Nosotros decidimos validar todos los campos, ademas del nombre
		String error = Usuario.validarUsuario(nom, edad, dir, tel);
		if(error != Usuario.OK) {
			request.setAttribute("mensajeError", error);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
		Usuario usu = new Usuario();
		usu.setNombre(nom);
		usu.setEdad(Integer.valueOf(edad));
		usu.setDireccion(dir);
		usu.setTelefono(tel);
			
		// Creo una session ya que estoy haciendo un redirect y por tanto todo lo que yo ponga dentro 
		// del request con setAtributte se va a perder
		//
		// Si creo una session se va mantener atravez de las peticiones
		HttpSession session = request.getSession();
		session.setAttribute("usuario", usu);
		response.sendRedirect(request.getContextPath() + "/ingresar");
	}

}
