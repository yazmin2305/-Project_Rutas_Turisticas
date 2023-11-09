package com.ruta.sanJuanDePuelenje.DTO.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FincaQueryDTO {
	private String fincaName;
	private String fincaDescription;
	private String fincaLocation;
	private Boolean fincaState;
}
