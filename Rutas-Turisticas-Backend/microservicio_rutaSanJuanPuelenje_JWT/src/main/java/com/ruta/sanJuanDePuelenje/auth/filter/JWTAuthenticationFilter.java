package com.ruta.sanJuanDePuelenje.auth.filter;

import java.io.IOException;
import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ruta.sanJuanDePuelenje.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//se extiende de esta clase porque vamos a usar un username y un password para realizar el login y acceder los recursos del API 
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	// verifica las credenciales del usuario utilizando JWT
	private AuthenticationManager authenticationManager;
	//Esta clave secreta es necesaria para firmar y verificar tokens JWT.
	public static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		// setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login",
		// "POST"));
	}

	// Este metodo se encarga de realizar la autenticación
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username != null && password != null) {
			// De esta forma podemos ver en consola cuales son los valores que recibimos en
			// el API rest para realizar login
			logger.info("Username desde request parameter (form-data): " + username);
			logger.info("Password desde request parameter (form-data): " + password);

		} else {
			User user = null;
			try {

				user = new ObjectMapper().readValue(request.getInputStream(), User.class);

				username = user.getEmail();
				password = user.getPassword();
				// se debe tenr en cuenta que desde desde el json ya no se envia "username" sino "email"
				// porque es el campo con el que se esta trabajando
				logger.info("Username desde request InputStream (raw): " + username);
				logger.info("Password desde request InputStream (raw): " + password);

			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		username = username.trim();

		// Se encarga de contener las credenciales (usuario y contraseña)...Token que se maneja de forma interna
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		// Intenta autenticar al usuario utilizando el token de autenticación proporcionado
		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// Creamos el token que vamos a retornar al cliente
		//SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		
		//SecretKey secretKey = Keys.hmacShaKeyFor("algunaLlaveSecreta".getBytes());
		//SecretKey secretKey = new SecretKeySpec("Clave.Secreta.12345".getBytes(), SignatureAlgorithm.HS512.getJcaName());

		String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal())
				.getUsername();
		// authResult: contiene los datos del usuario
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

		String token = Jwts.builder().setClaims(claims).setSubject(username).signWith(SECRET_KEY).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000 * 4)).compact();

		// Guardamos el token en el parametro Authorization, e indicamos el prefijo Bearer
		response.addHeader("Authorization", "Bearer " + token);
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (org.springframework.security.core.userdetails.User) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s, has iniciado sesión con éxito",
				((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername()));

		// ObjectMapper: convertir el objeto map a un objeto de tipo json
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje","Error de autenticación: username o password incorrecto");
		body.put("error", failed.getMessage());
		
		// ObjectMapper: convertir el objeto map a un objeto de tipo json
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}

}
