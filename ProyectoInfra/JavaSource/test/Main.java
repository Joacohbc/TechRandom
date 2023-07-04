package test;

import com.auth.JWTUtils;
import com.auth.UserDetails;
import com.entities.enums.Rol;

public class Main {

	public static void main(String[] args) {
		
		JWTUtils jwtUtils = new JWTUtils();
		
//		UserDetails details = new UserDetails();
//		details.setNombreUsuario("Joaquin");
//		details.setRol(Rol.ESTUDIANTE);
//		System.out.print(jwtUtils.generateToken(details));
		
		UserDetails details = jwtUtils.getUserDetails("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKb2FxdWluIiwiZXhwIjoxNjg4NDM0OTgyLCJpYXQiOjE2ODgzNDg1ODIsInJvbCI6IkVTVFVESUFOVEUifQ.wSKjyU_zS-_Ka7xkSyQE4VoVKhRE5M5D2byx0QkjAxrGHmnE2OjW4yNyMOv0UQV_vTzOSdivDZqCBZX2IjdeLA");
		System.out.println(details.getNombreUsuario());
		System.out.println(details.getRol());
	}

}
