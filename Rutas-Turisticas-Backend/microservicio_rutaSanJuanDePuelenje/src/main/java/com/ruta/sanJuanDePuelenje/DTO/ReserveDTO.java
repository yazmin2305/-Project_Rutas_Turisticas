package com.ruta.sanJuanDePuelenje.DTO;

import java.util.Date;

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
	private Integer reserveAmountPersons;
	private Double reserveTotalPrice;
	private Boolean reserveState;
	private Date reserveDate;
	private UserDTO user;
	private WorkshopDTO workshop;
	private TalkingDTO talking;
	private RecreationDTO recreation;
	private LodgingDTO lodging;
	private FestivalDTO festival;
	private LunchDTO lunch;
}
