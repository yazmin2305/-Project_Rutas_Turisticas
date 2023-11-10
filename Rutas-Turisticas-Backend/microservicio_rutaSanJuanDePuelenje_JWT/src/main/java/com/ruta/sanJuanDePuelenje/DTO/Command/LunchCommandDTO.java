package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LunchCommandDTO {
	private Integer lunchId;
	private String lunchMenu;
	private Double lunchUnitPrice;
	private Boolean lunchState;
	private RutaCommandDTO ruta;
}
