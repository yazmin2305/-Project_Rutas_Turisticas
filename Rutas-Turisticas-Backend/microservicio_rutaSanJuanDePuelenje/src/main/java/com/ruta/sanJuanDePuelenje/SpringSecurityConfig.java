package com.ruta.sanJuanDePuelenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ruta.sanJuanDePuelenje.auth.handler.LoginSuccesHandler;
import com.ruta.sanJuanDePuelenje.services.JpaUserDetailsService;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

	@Autowired
	private LoginSuccesHandler succesHandler;

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsService userDetailService;

	@Autowired
	public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
	}
//
//	@Bean
//	public UserDetailsService userDetailsService() throws Exception {
//
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//		manager.createUser(User.withUsername("user").password(passwordEncoder().encode("user")).roles("USER").build());
//		// El usuario con rol admin y contraseña admin va a tener dos roles "ADMIN" y
//		// "USER"
//		manager.createUser(
//				User.withUsername("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER").build());
//
//		return manager;
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		RequestMatcher publicMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/"),
				new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/js/**"),
				new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/listar"));
//
//		RequestMatcher userMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/reserve/**"));
//
//		RequestMatcher adminMatchers = new OrRequestMatcher(new AntPathRequestMatcher("/festival/**"),
//				new AntPathRequestMatcher("/finca/**"), new AntPathRequestMatcher("/lodging/**"),
//				new AntPathRequestMatcher("/lunch/**"), new AntPathRequestMatcher("/recreation/**"),
//				new AntPathRequestMatcher("/reserve/**"), new AntPathRequestMatcher("/talking/**"),
//				new AntPathRequestMatcher("/user/**"), new AntPathRequestMatcher("/workshop/**"),
//				new AntPathRequestMatcher("/workshopType/**"));
//
//		http.authorizeHttpRequests(authorize -> {
//			try {
//				authorize.requestMatchers(publicMatchers).permitAll().requestMatchers(userMatchers).hasAnyRole("USER")
//						.requestMatchers(adminMatchers).hasRole("ADMIN").anyRequest().authenticated();
//
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}).formLogin(formLogin -> formLogin.successHandler(succesHandler).permitAll())
//				.logout((logout) -> logout.permitAll()).exceptionHandling(e -> {
//					e.accessDeniedPage("/error_403");
//				});

//		return http.build();

		http.csrf(csrf -> {
            // Configura la protección CSRF según tus necesidades
            csrf
                .disable(); // Para deshabilitar CSRF (no recomendado en la mayoría de los casos)
               
        }).authorizeHttpRequests(authorize -> {
			try {
				authorize.requestMatchers(publicMatchers).permitAll().anyRequest()
						.authenticated();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).formLogin(formLogin -> formLogin.successHandler(succesHandler).permitAll())
				.logout((logout) -> logout.permitAll()).exceptionHandling(e -> {
					e.accessDeniedPage("/error_403");
				});

		return http.build();
	}

}
