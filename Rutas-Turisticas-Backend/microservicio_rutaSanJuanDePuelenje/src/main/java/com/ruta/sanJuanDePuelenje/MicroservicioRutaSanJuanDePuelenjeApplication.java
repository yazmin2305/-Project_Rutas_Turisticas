package com.ruta.sanJuanDePuelenje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MicroservicioRutaSanJuanDePuelenjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRutaSanJuanDePuelenjeApplication.class, args);
		System.out.println("Inicio app");
	}

}
