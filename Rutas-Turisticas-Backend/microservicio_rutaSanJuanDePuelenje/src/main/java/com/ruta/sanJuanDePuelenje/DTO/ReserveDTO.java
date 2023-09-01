package com.ruta.sanJuanDePuelenje.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveDTO {
	private Integer reserveId;
	private Integer reserveAmountP;
	private Double reserveTotalPrice;
	private Boolean reserveState;
	private UserDTO userDTO;
	private WorkshopDTO workshopDTO;
	private TalkingDTO talkingDTO;
	private RecreationDTO recreationDTO;
	private LodgingDTO lodgingDTO;
	private FestivalDTO festivalDTO;
	private LunchDTO lunchDTO;
}
