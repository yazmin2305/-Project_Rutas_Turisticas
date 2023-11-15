package com.ruta.sanJuanDePuelenje.DTO.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FincaCommandDTO {
	private Integer fincaId;
	private String fincaName;
	private String fincaDescription;
	private String fincaLocation;
	private Boolean fincaState;
	private RutaCommandDTO ruta;
}
