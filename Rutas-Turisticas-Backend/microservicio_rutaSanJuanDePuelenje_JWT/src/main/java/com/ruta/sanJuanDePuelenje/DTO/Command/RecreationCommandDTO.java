package com.ruta.sanJuanDePuelenje.DTO.Command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecreationCommandDTO {
	private Integer recreationId;
	private String recreationName;
	private Integer recreationDuration;
	private Boolean recreationAvailability;
	private Integer recreationMaxAmountP;
	private Double recreationUnitPrice;
	private Boolean recreationState;
	private FincaCommandDTO finca;
	//private List<ReserveCommandDTO> lstReserveDTOs;
	private RutaCommandDTO ruta;
}
