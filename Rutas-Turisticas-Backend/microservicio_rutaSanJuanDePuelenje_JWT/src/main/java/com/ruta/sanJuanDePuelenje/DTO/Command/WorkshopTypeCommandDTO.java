package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopTypeCommandDTO {
	private Integer workshopTypeId;
	private String workshopTypeName;
	private Boolean WorkshopTypeState;
//	private List<WorkshopQueryDTO> lstWorkshopDTOs;
}
