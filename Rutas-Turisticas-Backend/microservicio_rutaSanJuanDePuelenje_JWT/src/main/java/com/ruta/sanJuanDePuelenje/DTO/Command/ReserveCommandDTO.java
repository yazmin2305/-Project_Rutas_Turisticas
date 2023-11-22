package com.ruta.sanJuanDePuelenje.DTO.Command;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveCommandDTO {
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
	private UserCommandDTO user;
	private List<WorkshopCommandDTO> LstWorkshop;
	private List<TalkingCommandDTO> LstTalking;
	private List<RecreationCommandDTO> LstRecreation;
	private List<ReserveLodgingCommandDTO> reserveLodging;
	private List<FestivalCommandDTO> LstFestival;
	private List<ReserveLunchCommandDTO> reserveLunch;
	private RutaCommandDTO ruta;
}
