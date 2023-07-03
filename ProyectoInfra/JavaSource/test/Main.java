package test;

import com.auth.JWTUtils;
import com.auth.UserDetails;
import com.entities.enums.Rol;

public class Main {

	public static void main(String[] args) {
		
		JWTUtils jwtUtils = new JWTUtils();
		
		UserDetails details = new UserDetails();
		details.setNombreUsuario("Joaquin");
		details.setRol(Rol.ESTUDIANTE);
		System.out.print(jwtUtils.generateToken(details));
	}

}
