package com.ruta.sanJuanDePuelenje.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FestivalDTO {
	private Integer festivalId;
	private String festivalName;
	private String festivalDescription;
	private Date festivalDate;
	private Boolean festivalState;
	private FincaDTO finca;

}
 