package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLodgingCommandDTO {
	private Long id;
    private LodgingCommandDTO lodging;
    private Integer cantidad;
    private ReserveCommandDTO reserve;
}
