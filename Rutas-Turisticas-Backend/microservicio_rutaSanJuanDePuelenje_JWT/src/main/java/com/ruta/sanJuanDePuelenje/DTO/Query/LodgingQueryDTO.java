package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingQueryDTO {
	private String lodgingName;
	private String lodgingDescription;
	private Integer lodgingBedsAvailable;
	private Integer lodgingMaxAmountPerson;
	private Double lodgingUnitPrice;
	private Integer lodgingNumberNights;
	private Boolean lodgingState;
}
