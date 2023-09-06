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
public class TalkingDTO {
	private Integer talkingId;
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Boolean talkingAvailability;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Double talkingTotalPrice;
	private Boolean talkingState;
	private FincaDTO fincaDTO;
	private List<ReserveDTO> lstReserveDTOs;
}
