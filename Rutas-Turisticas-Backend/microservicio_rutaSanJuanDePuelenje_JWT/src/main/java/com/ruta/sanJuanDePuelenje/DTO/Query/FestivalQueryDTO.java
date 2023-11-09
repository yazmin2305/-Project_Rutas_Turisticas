package com.ruta.sanJuanDePuelenje.DTO.Query;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FestivalQueryDTO {
	private String festivalName;
	private String festivalDescription;
	private Date festivalDate;
	private Boolean festivalState;
}
