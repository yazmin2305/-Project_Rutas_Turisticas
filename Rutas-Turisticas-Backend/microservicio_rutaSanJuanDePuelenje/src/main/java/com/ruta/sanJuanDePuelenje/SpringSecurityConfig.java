package com.ruta.sanJuanDePuelenje;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
		//El usuario con rol admin y contraseña admin va a tener dos roles "ADMIN" y "USER"
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER").build());

		return manager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((authz) -> {
			try {
				authz.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
						.requestMatchers("/festival/**").hasAnyRole("ADMIN")
						.requestMatchers("/finca/**").hasAnyRole("ADMIN")
						.requestMatchers("/lodging/**").hasAnyRole("ADMIN")
						.requestMatchers("/lunch/**").hasAnyRole("ADMIN")
						.requestMatchers("/recreation/**").hasAnyRole("ADMIN")
						.requestMatchers("/reserve/**").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/role/**").hasAnyRole("ADMIN")
						.requestMatchers("/talking/**").hasAnyRole("ADMIN")
						.requestMatchers("/user/**").hasAnyRole("ADMIN")
						.requestMatchers("/workshop/**").hasAnyRole("ADMIN")
						.requestMatchers("/workshopType/**").hasAnyRole("ADMIN")
						.anyRequest().authenticated()
                        .and()
                        .formLogin().permitAll()
                        .and()
                        .logout().permitAll();

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return http.build();

	}

}
