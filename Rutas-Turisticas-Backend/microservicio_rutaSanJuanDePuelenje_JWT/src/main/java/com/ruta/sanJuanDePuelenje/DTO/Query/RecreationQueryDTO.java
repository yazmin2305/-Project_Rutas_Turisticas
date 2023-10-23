package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecreationQueryDTO {
	private String recreationName;
	private Integer recreationDuration;
	private Boolean recreationAvailability;
	private Integer recreationMaxAmountP;
	private Double recreationUnitPrice;
	private Boolean recreationState;
}
