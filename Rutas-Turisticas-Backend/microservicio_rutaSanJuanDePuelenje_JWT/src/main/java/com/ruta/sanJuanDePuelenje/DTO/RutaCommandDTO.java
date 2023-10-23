package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaCommandDTO {
	private Integer rutaId;
	private String rutaName;
	private String rutaState;
	private List<TalkingCommandDTO> LstTalking;
	private List<WorkshopDTO> LstWorkshop;
	private List<RecreationDTO> LstRecreation;
	private List<LodgingDTO> LstLodging;
	private List<FestivalDTO> LstFestival;
	private List<FincaDTO> LstFinca;
	private List<LunchDTO> LstLunch;
	private List<ReserveDTO> LstReserve;
	private List<UserDTO> LstUser;

}
