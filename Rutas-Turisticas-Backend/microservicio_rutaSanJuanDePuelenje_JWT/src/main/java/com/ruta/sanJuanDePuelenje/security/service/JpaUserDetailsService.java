package com.ruta.sanJuanDePuelenje.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruta.sanJuanDePuelenje.models.Role;
import com.ruta.sanJuanDePuelenje.models.User;
import com.ruta.sanJuanDePuelenje.repository.IUserRepository;
import com.ruta.sanJuanDePuelenje.security.exception.UserDisabledException;

@Service("JpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
        
        if(user == null) {
        	logger.error("Error en el Login: no existe el usuario registrado con el correo'" + email + "' en el sistema!");
        	throw new UsernameNotFoundException("Correo: " + email + " no existe en el sistema!");
        }
        
        if (!user.getState()) {
            logger.error("Error en el Login: no existe el usuario '" + email + "' está deshabilitado!");
            throw new UserDisabledException("El usuario está deshabilitado");
        }
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// Agregar el rol con el que se autentico el usuario ya sea admin o user
		Role role = user.getRole(); // Obtén el primer (y único) rol del usuario
		logger.info("Role: ".concat(role.getName()));
		authorities.add(new SimpleGrantedAuthority(role.getName()));
		
		 if(authorities.isEmpty()) {
	        	logger.error("Error en el Login: Usuario '" + email + "' no tiene roles asignados!");
	        	throw new UsernameNotFoundException("Error en el Login: usuario '" + email + "' no tiene roles asignados!");
	        }
		//Devolvemos un objeto user detail con la información necesaria para el inicio de sesión
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),true, true, true, true, authorities);
	}

}
