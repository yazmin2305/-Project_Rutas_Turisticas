package com.ruta.sanJuanDePuelenje.DTO;

import java.util.Date;
import java.util.List;

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
	private Double lodgingTotalPrice;
	private Double lunchTotalPrice;
	private Double talkingTotalPrice;
	private Double recreationTotalPrice;
	private Double workshopTotalPrice;
	private Boolean reserveState;
	private Date reserveDate;
	private UserDTO user;
	private List<WorkshopDTO> LstWorkshop;
	private List<TalkingDTO> LstTalking;
	private List<RecreationDTO> LstRecreation;
	private List<LodgingDTO> LstLodging;
	private List<FestivalDTO> LstFestival;
	private List<LunchDTO> ListLunch;
}
