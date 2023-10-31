package com.ruta.sanJuanDePuelenje.util;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageParameterResponse {
	
	private Integer page;

	private Integer size;

	private Long totalNumberElements;

	private Integer totalNumberPage;
}
