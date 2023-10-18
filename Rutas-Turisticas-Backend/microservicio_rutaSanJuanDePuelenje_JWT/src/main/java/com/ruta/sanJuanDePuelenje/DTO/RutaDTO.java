package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaDTO {
	private Integer rutaID;
	private String rutaName;
	private List<WorkshopDTO> LstWorkshop;
	private List<TalkingDTO> LstTalking;
	private List<RecreationDTO> LstRecreation;
	private List<LodgingDTO> LstLodging;
	private List<FestivalDTO> LstFestival;
	private List<LunchDTO> ListLunch;
	
	
}
