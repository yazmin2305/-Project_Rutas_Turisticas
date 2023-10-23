package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchQueryDTO {
	private String lunchMenu;
	private Double lunchUnitPrice;
	private Boolean lunchState;
}
