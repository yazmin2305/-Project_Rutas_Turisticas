package com.ruta.sanJuanDePuelenje.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class mapper {
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
