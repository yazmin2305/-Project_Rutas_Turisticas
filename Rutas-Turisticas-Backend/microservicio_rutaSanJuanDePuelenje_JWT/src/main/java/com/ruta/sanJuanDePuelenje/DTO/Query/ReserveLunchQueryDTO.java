package com.ruta.sanJuanDePuelenje.DTO.Query;

import com.ruta.sanJuanDePuelenje.DTO.Command.LunchCommandDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLunchQueryDTO {
	private Long id;
    private LunchCommandDTO lunch;
    private Integer cantidad;

}
