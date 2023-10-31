package com.ruta.sanJuanDePuelenje.util;

import org.springframework.data.domain.Page;

public class PageableUtils {
	
	public static GenericPageableResponse createPageableResponse(Page<?> page, Object list) {
        PageParameterResponse pagination = PageParameterResponse.builder()
                .page(page.getNumber())
                .size(page.getNumberOfElements())
                .totalNumberElements(page.getTotalElements())
                .totalNumberPage(page.getTotalPages())
                .build();
        return GenericPageableResponse.builder()
                .elements(list)
                .pagination(pagination)
                .build();
    }
}
