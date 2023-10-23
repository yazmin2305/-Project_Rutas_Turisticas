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
public class FincaDTO {
	private Integer fincaId;
	private String fincaName;
	private String fincaDescription;
	private String fincaLocation;
	private Boolean fincaState;
	private List<TalkingQueryDTO> lstTalkingDTOs;
	private List<WorkshopDTO> lstWorkshopDTOs;
	private List<RecreationDTO> lstRecreationDTOs;
	private List<LodgingDTO> lstLodgingDTOs;
	private List<FestivalDTO> lstFestivalDTOs;
	private RutaCommandDTO ruta;
}
