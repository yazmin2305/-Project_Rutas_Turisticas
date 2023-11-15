package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecreationCommandDTO {
	private Integer recreationId;
	private String recreationName;
	private Integer recreationDuration;
	private String recreationDescription;
	private Integer recreationMaxAmountPerson;
	private Double recreationUnitPrice;
	private Boolean recreationState;
	private FincaCommandDTO finca;
}
