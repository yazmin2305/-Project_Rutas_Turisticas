package com.ruta.sanJuanDePuelenje.DTO.Command;

import com.ruta.sanJuanDePuelenje.DTO.WorkshopTypeDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopCommandDTO {
	private Integer workshopId;
	private String workshopName;
	private String workshopDescription;
	private Integer workshopDuration;
	private Boolean workshopAvailability;
	private Integer workshopMaxAmountPerson;
	private Double workshopUnitPrice;
	private Boolean workshopState;
	private WorkshopTypeDTO workshopType;
	private FincaCommandDTO finca;
//	private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
