package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TalkingCommandDTO {
	private Integer talkingId;
	private String talkingName;
	private String talkingDescription;
	private Integer talkingDuration;
	private Integer talkingMaxAmountPerson;
	private Double talkingUnitPrice;
	private Boolean talkingState;
	private FincaCommandDTO finca;
}
