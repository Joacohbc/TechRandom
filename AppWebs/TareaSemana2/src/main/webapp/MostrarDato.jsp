<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion de Usuarios</title>
</head>
<body style="font-family: Arial;"> 
		<table>
			<tr>
				<td><label for="identificador">Identificador:</label></td>
				<td><input name="identificador" type="text" value="${requestScope.id}" readonly></td>
			</tr>
			
			<tr>
				<td><label for="nombre">Nombre:</label></td>
				<td><input name="nombre" type="text" value="${requestScope.nombre}" readonly></td>
			</tr>

			<tr>
				<td><label for="edad">Edad:</label></td>
				<td><input name="edad" type="text" value="${requestScope.edad}" readonly></td>
			</tr>
			
			<tr>
				<td><label for="direccion">Direccion:</label></td>
				<td><input name="direccion" type="text" value="${requestScope.direccion}" readonly></td>
			</tr>
			
			
			<tr>
				<td><label for="tel">Tel:</label></td>
				<td><input name="tel" type="text" value="${requestScope.telefono}" readonly></td>
			</tr>
		</table>
</body>	
</html>