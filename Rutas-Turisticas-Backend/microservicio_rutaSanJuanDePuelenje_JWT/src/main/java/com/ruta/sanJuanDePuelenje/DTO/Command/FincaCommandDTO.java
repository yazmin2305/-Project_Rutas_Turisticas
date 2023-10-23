package com.ruta.sanJuanDePuelenje.DTO.Command;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Query.FestivalQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.LodgingQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.RecreationQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.TalkingQueryDTO;
import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FincaCommandDTO {
	private Integer fincaId;
	private String fincaName;
	private String fincaDescription;
	private String fincaLocation;
	private Boolean fincaState;
	private List<TalkingQueryDTO> lstTalkingDTOs;
	private List<WorkshopQueryDTO> lstWorkshopDTOs;
	private List<RecreationQueryDTO> lstRecreationDTOs;
	private List<LodgingQueryDTO> lstLodgingDTOs;
	private List<FestivalQueryDTO> lstFestivalDTOs;
	private RutaCommandDTO ruta;
}
