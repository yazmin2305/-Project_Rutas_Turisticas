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
	private Integer lodgingBedsAvailable;
	private Integer lodgingMaxAmountPerson;
	private Double lodgingUnitPrice;
	private Integer lodgingNumberNights;
	private Boolean lodgingState;
	private FincaCommandDTO finca;
	//private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
