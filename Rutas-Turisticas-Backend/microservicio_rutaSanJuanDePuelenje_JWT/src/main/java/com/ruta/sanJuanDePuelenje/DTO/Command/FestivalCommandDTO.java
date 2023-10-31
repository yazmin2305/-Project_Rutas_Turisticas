package com.ruta.sanJuanDePuelenje.DTO.Command;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FestivalCommandDTO {
	private Integer festivalId;
	private String festivalName;
	private String festivalDescription;
	private Date festivalDate;
	private Boolean festivalState;
	private FincaCommandDTO finca;
	private RutaCommandDTO ruta;
}
 