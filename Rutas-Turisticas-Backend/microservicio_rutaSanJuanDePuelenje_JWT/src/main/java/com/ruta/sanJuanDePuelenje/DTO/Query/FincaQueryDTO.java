package com.ruta.sanJuanDePuelenje.DTO.Query;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FincaQueryDTO {
	private String fincaName;
	private String fincaDescription;
	private String fincaLocation;
	private Boolean fincaState;
	private List<TalkingQueryDTO> lstTalkingDTOs;
	private List<WorkshopQueryDTO> lstWorkshopDTOs;
	private List<RecreationQueryDTO> lstRecreationDTOs;
	private List<LodgingQueryDTO> lstLodgingDTOs;
}
