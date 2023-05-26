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
<link rel='stylesheet' type='text/css' media='screen' href='css/style.css'>
<body>
  <header class="header">
    <img src="img/utec.jpg">
    <h1>
      <b>Sistema de Gestión de Alumnos</b>
    </h1>
  </header>
  
  <h2 class="separacion">
      Alumno:
  </h2>
  <div class="formulario">
  <div class="form_label">
      <label for="nombre">Nombre</label>
      <label class="apellido" for="apellido">Apellido</label>
      <label for="cedula">Cédula de Identidad</label>
      <label for="codigo">Código de carrera</label>
      <label for="direccion">Dirección</label>
      <label for="nacimiento">Año de nacimiento</label>
      <label for="foto">Archivo Foto de Alumno</label>
    </div>
    <div class="inputs">
      <input type="text" id="nombre" name="nombre" value=<%=ba.getNombre()%> readonly><br><br>
      <input type="text" id="apellido" name="apellido" value=<%=ba.getApellido()%> readonly><br><br>
      <input type="text" id="cedula" name="cedula" value=<%=ba.getCi()%> readonly><br><br>
      <input type="text" id="codigo" name="codigo" value=<%=ba.getCodigoCarrera()%> readonly><br><br>
      <input type="text" id="direccion" name="direccion" value=<%=ba.getDireccion()%>readonly><br><br>
      <input type="text" id="nacimiento" name="nacimiento" value=<%=ba.getAnioNacimiento()%> readonly><br><br>
      <input type="text" id="foto" name="foto"  value=<%=ba.getArchivoFoto()%> readonly><br><br>

    </div>
      </div>
    
    <footer>Unidad Curricular de Programación de Aplicaciones Web - UTEC - 2023</footer>
</html>