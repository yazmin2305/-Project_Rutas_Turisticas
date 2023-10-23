package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaQueryDTO {
	private Integer rutaId;
	private String rutaName;
	private String rutaState;
	private List<TalkingQueryDTO> LstTalking;
	private List<WorkshopDTO> LstWorkshop;
	private List<RecreationDTO> LstRecreation;
	private List<LodgingDTO> LstLodging;
	private List<FestivalDTO> LstFestival;
	private List<FincaDTO> LstFinca;
	private List<LunchDTO> LstLunch;
//	private List<ReserveDTO> LstReserve;
//	private List<UserDTO> LstUser;
}
