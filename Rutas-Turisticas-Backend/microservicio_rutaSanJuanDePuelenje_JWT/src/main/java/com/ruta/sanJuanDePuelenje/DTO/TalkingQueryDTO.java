package com.ruta.sanJuanDePuelenje.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalkingQueryDTO {
	private Integer talkingId;
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Boolean talkingAvailability;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Boolean talkingState;
//	private FincaDTO finca;
//	private List<ReserveDTO> lstReserveDTOs;
//	private RutaDTO ruta;
}
