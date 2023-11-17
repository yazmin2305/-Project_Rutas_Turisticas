package com.ruta.sanJuanDePuelenje.DTO.Query;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveQueryDTO {
	private Integer reserveAmountPersons;
	private Double reserveTotalPrice;
	private Double lodgingTotalPrice;
	private Double lunchTotalPrice;
	private Double talkingTotalPrice;
	private Double recreationTotalPrice;
	private Double workshopTotalPrice;
	private Boolean reserveState;
	private Date reserveDate;
	private UserQueryDTO user;
	private List<WorkshopQueryDTO> LstWorkshop;
	private List<TalkingQueryDTO> LstTalking;
	private List<RecreationQueryDTO> LstRecreation;
	private List<LodgingQueryDTO> LstLodging;
	private List<FestivalQueryDTO> LstFestival;
	private List<ReserveLunchQueryDTO> reserveLunch;
}
