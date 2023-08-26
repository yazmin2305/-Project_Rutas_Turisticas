package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipoTaller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class TipoTaller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipo_taller_id")
	private Integer tipoTaller;
	
	@Column(name = "nombre_tipo_taller", nullable = false, length = 45)
	private String nombre;
	
	@OneToMany(mappedBy = "tipoTaller")
	private List<Taller> listaTalleres;
	
}
