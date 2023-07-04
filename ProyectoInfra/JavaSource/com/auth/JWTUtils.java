package com.auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.ejb.Singleton;

import com.entities.enums.Rol;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Singleton
public class JWTUtils {
	
	// 1000 Milisegundos, 60 Segundos, 60 Minutos, 1 Hora
	public static final long JWT_DURATION = 1000 * 60 * 60 * 1;

	private String SECRET_KEY = "SoySuperMegaUltraDificil";

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
		
	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims) // Le asignos las Claims
				.setSubject(subject) // Username (que seria la idetenfificacion del JWT)
				.setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de creacion
				.setExpiration(new Date(System.currentTimeMillis() + JWT_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY) // Inscripto el Token con la key 
				.compact();
	}
	
	public UserDetails getUserDetails(String token) {
		Claims claims = getAllClaimsFromToken(token);
		UserDetails ud = new UserDetails();
		ud.setNombreUsuario(claims.getSubject());
		ud.setRol(Rol.valueOf(String.valueOf(claims.get("rol"))));
		ud.setIdUsuario(Long.valueOf(String.valueOf(claims.get("idUsuario"))));
		ud.setIdRol(Long.valueOf(String.valueOf(claims.get("idRol"))));
		return ud;
	}
	
	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("rol", userDetails.getRol().name());
		claims.put("idUsuario", userDetails.getIdUsuario());
		claims.put("idRol", userDetails.getIdRol());
		return doGenerateToken(claims, userDetails.getNombreUsuario());
	}
	
	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		UserDetails ud = getUserDetails(token);
		if(isTokenExpired(token)) return false;
		if(!ud.getIdUsuario().equals(userDetails.getIdUsuario())) return false;
		if(!ud.getIdRol().equals(userDetails.getIdRol())) return false;
		if(!ud.getNombreUsuario().equals(userDetails.getNombreUsuario())) return false;
		if(!ud.getRol().equals(userDetails.getRol())) return false;
		return true;
	}	
	
}
