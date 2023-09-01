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
public class WorkshopDTO {
	private Integer workshopId;
	private String workshopName;
	private String workshopDescription;
	private Integer workshopDuration;
	private Boolean workshopAvailability;
	private Double workshopUnitPrice;
	private Double workshopTotalPrice;
	private Boolean workshopState;
	private WorkshopTypeDTO workshopTypeDTO;
	private FincaDTO fincaDTO;
	private List<ReserveDTO> lstReserveDTOs;
}
