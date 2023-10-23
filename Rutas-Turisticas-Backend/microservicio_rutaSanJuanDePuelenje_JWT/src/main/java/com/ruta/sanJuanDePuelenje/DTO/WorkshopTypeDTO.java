package com.ruta.sanJuanDePuelenje.DTO;

import java.util.List;

import com.ruta.sanJuanDePuelenje.DTO.Query.WorkshopQueryDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopTypeDTO {
	private String workshopTypeName;
	private Boolean WorkshopTypeState;
	private List<WorkshopQueryDTO> lstWorkshopDTOs;
}
