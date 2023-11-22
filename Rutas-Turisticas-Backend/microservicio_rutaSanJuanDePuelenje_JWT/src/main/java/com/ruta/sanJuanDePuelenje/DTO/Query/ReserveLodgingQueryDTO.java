package com.ruta.sanJuanDePuelenje.DTO.Query;

import com.ruta.sanJuanDePuelenje.DTO.Command.LodgingCommandDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLodgingQueryDTO {
	private Long id;
    private LodgingCommandDTO lodging;
    private Integer cantidad;
}
