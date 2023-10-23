package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingDTO {
	private Integer lodgindId;
	private String lodgingName;
	private String lodgingDescription;
	private Integer lodgingBedsAvailable;
	private Integer lodgingMaxAmountPerson;
	private Double lodgingUnitPrice;
	private Integer lodgingNumberNights;
	private Boolean lodgingState;
	private FincaDTO finca;
	private List<ReserveDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
