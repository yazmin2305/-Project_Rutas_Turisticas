package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TalkingQueryDTO {
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Boolean talkingAvailability;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Boolean talkingState;
}
