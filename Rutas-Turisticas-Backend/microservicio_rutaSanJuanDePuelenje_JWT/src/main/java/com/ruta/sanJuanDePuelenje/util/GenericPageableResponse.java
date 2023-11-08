package com.ruta.sanJuanDePuelenje.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericPageableResponse {
	
	private Object elements;

    private PageParameterResponse pagination;
    
    private String mensaje;
    
    
    public static GenericPageableResponse emptyResponse(String message) {
        return GenericPageableResponse.builder()
                .mensaje(message)
                .build();
    }

}
