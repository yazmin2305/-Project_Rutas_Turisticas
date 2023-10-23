package com.ruta.sanJuanDePuelenje.DTO.Command;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaCommandDTO {
	private String rutaName;
	private String rutaState;
	private List<TalkingCommandDTO> LstTalking;
	private List<WorkshopCommandDTO> LstWorkshop;
	private List<RecreationCommandDTO> LstRecreation;
	private List<LodgingCommandDTO> LstLodging;
	private List<FestivalCommandDTO> LstFestival;
	private List<FincaCommandDTO> LstFinca;
	private List<LunchCommandDTO> LstLunch;
	private List<ReserveCommandDTO> LstReserve;
	private List<UserCommandDTO> LstUser;
	//tengo que mapear todas estas listas si voy a hacer un post o un put?
	//ruta si se debe relacionar con usuario y con reserva?
}
