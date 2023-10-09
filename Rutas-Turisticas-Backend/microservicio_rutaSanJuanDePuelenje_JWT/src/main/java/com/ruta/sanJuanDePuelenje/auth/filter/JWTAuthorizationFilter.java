package com.ruta.sanJuanDePuelenje.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruta.sanJuanDePuelenje.auth.SimpleGrantedAuthorityMixin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//esto se ejecuta cuando el authorization exista y sea del tipo "Bearer"
		String header = request.getHeader("Authorization");//aqui obtenemos el token
		if(!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		//Implementar la validación del token
		//invocar el metodo "parse" para analizar el token de la misma clase que crea el token
		boolean validToken;
		Claims token = null;
		try {
			token = Jwts.parser()
			.setSigningKey(JWTAuthenticationFilter.SECRET_KEY).parseClaimsJws(header.replace("Bearer ", "")).getBody();
			validToken = true;
		}catch(JwtException | IllegalArgumentException e) {
			validToken = false;
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		if(validToken) {
			String username = token.getSubject();
			Object roles = token.get("authorities");
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class).readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class)) ;
			authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		}
		//Maneja el contexto de seguridad.. Asignamos el objeto authentication dentro del contexto. esto autentica al usuario dentro de la petición del request
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	protected boolean requiresAuthentication(String header) {
		if(header == null || !header.startsWith("Bearer ")) {
			return false;
		}
		return true;
	}

}
