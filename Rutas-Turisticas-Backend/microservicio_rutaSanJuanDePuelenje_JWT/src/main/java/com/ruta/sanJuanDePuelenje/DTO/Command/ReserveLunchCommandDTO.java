package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLunchCommandDTO {
	//private Long id;
    private LunchCommandDTO lunch;
    private Integer cantidad;

}
