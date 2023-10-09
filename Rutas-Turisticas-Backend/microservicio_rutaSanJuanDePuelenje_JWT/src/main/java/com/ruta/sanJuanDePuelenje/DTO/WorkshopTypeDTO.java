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
public class WorkshopTypeDTO {
	private Integer workshopTypeId;
	private String workshopTypeName;
	private Boolean WorkshopTypeState;
	private List<WorkshopDTO> lstWorkshopDTOs;
}
