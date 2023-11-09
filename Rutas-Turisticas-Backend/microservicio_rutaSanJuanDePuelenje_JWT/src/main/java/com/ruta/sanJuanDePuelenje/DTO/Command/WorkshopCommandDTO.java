package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private WorkshopTypeCommandDTO workshopType;
	private FincaCommandDTO finca;
//	private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
