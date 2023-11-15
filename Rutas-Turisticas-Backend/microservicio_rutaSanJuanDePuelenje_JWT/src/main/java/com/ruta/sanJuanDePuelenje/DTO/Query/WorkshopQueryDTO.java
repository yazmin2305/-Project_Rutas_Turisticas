package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkshopQueryDTO {
	private String workshopName;
	private String workshopDescription;
	private Integer workshopDuration;
//	private Boolean workshopAvailability;
	private Integer workshopMaxAmountPerson;
	private Double workshopUnitPrice;
	private Boolean workshopState;
	private WorkshopTypeQueryDTO workshopType;
}