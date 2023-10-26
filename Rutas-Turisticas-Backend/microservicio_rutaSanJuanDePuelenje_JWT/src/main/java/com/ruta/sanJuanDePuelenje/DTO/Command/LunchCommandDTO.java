package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchCommandDTO {
	private String lunchMenu;
	private Double lunchUnitPrice;
	private Boolean lunchState;
	private RutaCommandDTO ruta;
}
