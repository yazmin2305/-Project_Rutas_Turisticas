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
public class RecreationDTO {
	private Integer recreationId;
	private String recreationName;
	private Integer recreationDuration;
	private Boolean recreationAvailability;
	private Integer recreationMaxAmountP;
	private Double recreationUnitPrice;
	private Double recreationTotalPrice;
	private Boolean recreationState;
	private FincaDTO finca;
	private List<ReserveDTO> lstReserveDTOs;

}
