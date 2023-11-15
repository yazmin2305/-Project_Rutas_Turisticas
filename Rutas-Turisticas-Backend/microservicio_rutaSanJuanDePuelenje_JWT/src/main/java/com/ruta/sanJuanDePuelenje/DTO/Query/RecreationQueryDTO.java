package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecreationQueryDTO {
	private String recreationName;
	private Integer recreationDuration;
	private Integer recreationMaxAmountPerson;
	private Double recreationUnitPrice;
	private Boolean recreationState;
}
