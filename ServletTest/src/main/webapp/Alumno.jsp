<%@page import="beans.BuscarAlumnos"%>
<%@page import="beans.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		Integer ci = Integer.valueOf(request.getParameter("cedula"));
		Alumno ba = new BuscarAlumnos().buscarAlumno(nombre, apellido, ci);
	%>
	
	<h1>Datos del Alumno:</h1>
	<p>Nombre: <%=ba.getNombre()%></p>
	<p>Apellido: <%=ba.getApellido()%></p>
</body>
</html>