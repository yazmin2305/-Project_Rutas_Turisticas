package com.ruta.sanJuanDePuelenje;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SpringSecurityConfig {
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		manager.createUser(User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build());
		// El usuario con rol admin y contraseña admin va a tener dos roles "ADMIN" y
		// "USER"
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER").build());

		return manager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		RequestMatcher publicMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/"),
				new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/js/**"),
				new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/listar"));

		RequestMatcher userMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/reserve/**"));

		RequestMatcher adminMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/festival/**"),
				new AntPathRequestMatcher("/finca/**"), new AntPathRequestMatcher("/lodging/**"),
				new AntPathRequestMatcher("/lunch/**"), new AntPathRequestMatcher("/recreation/**"),
				new AntPathRequestMatcher("/reserve/**"), new AntPathRequestMatcher("/talking/**"),
				new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/workshop/**"),
				new AntPathRequestMatcher("/workshopType/**"));

		http.authorizeHttpRequests(authorize -> {
			try {
				authorize.requestMatchers(publicMatchers).permitAll().requestMatchers(userMatchers).hasAnyRole("USER")
						.requestMatchers(adminMatchers).hasRole("ADMIN").anyRequest().authenticated();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).formLogin(formLogin -> formLogin.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll())
				.exceptionHandling(e -> {
					e.accessDeniedPage("/error_403");
				});

		return http.build();
	}



}
