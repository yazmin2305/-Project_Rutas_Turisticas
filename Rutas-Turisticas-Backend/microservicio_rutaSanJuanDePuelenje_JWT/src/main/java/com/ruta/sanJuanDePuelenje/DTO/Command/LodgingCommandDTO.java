package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LodgingCommandDTO {
	private Integer lodgingId;
	private String lodgingName;
	private String lodgingDescription;
	private Integer lodgingQuantityAvailable;
	private Integer lodgingMaxAmountPerson;
	private Double lodgingUnitPrice;
	private Boolean lodgingState;
	private FincaCommandDTO finca;
}
