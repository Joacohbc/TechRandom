<%@page import="beans.BuscarAlumnos"%>
<%@page import="beans.Alumno"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String nombre = request.getParameter("nombre");
String apellido = request.getParameter("apellido");
Integer ci = Integer.valueOf(request.getParameter("cedula"));
Alumno ba = new BuscarAlumnos().buscarAlumno(nombre, apellido, ci);
%>


<!DOCTYPE html>
<html>
<head>
<link rel='stylesheet' type='text/css' media='screen'
	href='css/style.css'>
<body>
	<header class="header">
		<img src="img/utec.jpg">
		<h1>
			<b>Sistema de Gestión de Alumnos</b>
		</h1>
	</header>

	<h2 class="separacion">Alumno:</h2>
	
	<div class="flex-container">
	<div class="formulario">
		<div class="form_label"></div>


		<div class="label-form">
			<label for="nombre">Nombre:</label> <input type="text" id="nombre"
				name="nombre" value=<%=ba.getNombre()%> readonly><br> <br>

		</div>

		<div class="label-form">
			<label for="apellido">Apellido:</label> <input type="text"
				id="apellido" name="apellido" value=<%=ba.getApellido()%> readonly><br>
			<br>

		</div>

		<div class="label-form">

			<label for="cedula">Cédula de Identidad</label> <input type="text"
				id="cedula" name="cedula" value=<%=ba.getCi()%> readonly><br>
			<br>
		</div>




		<div class="label-form">
			<label for="codigo">Código de carrera</label> <input type="text"
				id="codigo" name="codigo" value=<%=ba.getCodigoCarrera()%> readonly><br>
			<br>
		</div>


		<div class="label-form">
			<label for="direccion">Dirección</label>
			 <input type="text"
				id="direccion" name="direccion" value=<%=ba.getDireccion()%>
				readonly><br> <br>

		</div>

		<div class="label-form">
			<label for="nacimiento">Año de nacimiento</label> <input type="text"
				id="nacimiento" name="nacimiento" value=<%=ba.getAnioNacimiento()%>
				readonly><br> <br>

		</div>

		<div class="label-form">

			<label for="foto">Archivo Foto de Alumno</label> <input type="text"
				id="foto" name="foto" value=<%=ba.getArchivoFoto()%> readonly><br>
			<br>

		</div>


</div>

	</div>



	
	
	<footer class="note">Unidad Curricular de Programación de Aplicaciones Web
		- UTEC - 2023</footer>
</html>