package com.ruta.sanJuanDePuelenje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.annotation.PostConstruct;

@SpringBootApplication()
public class MicroservicioRutaSanJuanDePuelenjeApplication {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRutaSanJuanDePuelenjeApplication.class, args);
		System.out.println("Inicio app");

	}

	@PostConstruct
	public void encodePasswords() {
		String password = "user";

		for (int i = 0; i < 2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}
}
