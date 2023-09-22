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
public class LunchDTO {
	private Integer lunchId;
	private String lunchMenu;
	private Double lunchUnitPrice;
	private Boolean lunchState;
	private List<ReserveDTO> lsReserveDTOs;

}
