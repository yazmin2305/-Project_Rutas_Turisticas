package com.ruta.sanJuanDePuelenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ruta.sanJuanDePuelenje.auth.filter.JWTAuthenticationFilter;
import com.ruta.sanJuanDePuelenje.auth.filter.JWTAuthorizationFilter;
import com.ruta.sanJuanDePuelenje.auth.service.JWTService;
import com.ruta.sanJuanDePuelenje.services.JpaUserDetailsService;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

//	@Autowired
//	private LoginSuccesHandler succesHandler;

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsService userDetailService;

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Autowired
	private JWTService jwtService;
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Autowired
	public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		RequestMatcher publicMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/"),
				new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/js/**"),
				new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/festival/ConsultAllFestivales"), 
				new AntPathRequestMatcher("/finca/ConsultAllFincas"), new AntPathRequestMatcher("/lodging/ConsultAllLodging"),
				new AntPathRequestMatcher("/lunch/ConsultAllLunch"), new AntPathRequestMatcher("/recreation/ConsultAllRecreation"),
				new AntPathRequestMatcher("/talking/ConsultAllTalking"), new AntPathRequestMatcher("/workshop/ConsultAllWorkshop"));

		http.authorizeHttpRequests(authorize -> {
			try {
				authorize.requestMatchers(publicMatchers).permitAll().anyRequest().authenticated();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).csrf(csrf -> csrf.disable()) // Deshabilitar CSRF ya que vamos a utilizar el jwt
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// para que no utilice  sesiones  y  no utilice el estado
				.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationConfiguration.getAuthenticationManager(), jwtService)); 

		return http.build();
	}

}
