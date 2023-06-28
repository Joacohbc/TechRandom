<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion de Usuarios</title>
</head>
<body style="font-family: Arial;"> 
		<div style="color: red;">${requestScope.mensajeError}</div>
		<form action="validar" method="post">
			<table>
				<tr>
					<td><label for="nombre">Nombre:</label></td>
					<td><input name="nombre" type="text"></td>
				</tr>

				<tr>
					<td><label for="edad">Edad:</label></td>
					<td><input name="edad" type="number"></td>
				</tr>
				
				<tr>
					<td><label for="direccion">Direccion:</label></td>
					<td><input name="direccion" type="text"></td>
				</tr>
				
				
				<tr>
					<td><label for="tel">Tel:</label></td>
					<td><input name="tel" type="text"></td>
				</tr>
				
				<tr>
					<td><input type="submit"></td>
				</tr>
			</table>
		</form>
</body>	
</html>