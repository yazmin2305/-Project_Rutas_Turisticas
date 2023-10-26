package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingCommandDTO {
	private String lodgingName;
	private String lodgingDescription;
	private Integer lodgingBedsAvailable;
	private Integer lodgingMaxAmountPerson;
	private Double lodgingUnitPrice;
	private Integer lodgingNumberNights;
	private Boolean lodgingState;
	private FincaCommandDTO finca;
	//private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
