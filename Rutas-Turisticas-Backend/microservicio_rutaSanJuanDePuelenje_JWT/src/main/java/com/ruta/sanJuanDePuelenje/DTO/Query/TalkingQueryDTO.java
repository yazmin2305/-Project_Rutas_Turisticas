package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalkingQueryDTO {
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Boolean talkingState;
}
