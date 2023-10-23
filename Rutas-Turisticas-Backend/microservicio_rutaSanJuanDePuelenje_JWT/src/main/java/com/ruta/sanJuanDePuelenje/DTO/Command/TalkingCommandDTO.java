package com.ruta.sanJuanDePuelenje.DTO.Command;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalkingCommandDTO {
	private Integer talkingId;
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Boolean talkingAvailability;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Boolean talkingState;
	private FincaCommandDTO finca;
	private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
