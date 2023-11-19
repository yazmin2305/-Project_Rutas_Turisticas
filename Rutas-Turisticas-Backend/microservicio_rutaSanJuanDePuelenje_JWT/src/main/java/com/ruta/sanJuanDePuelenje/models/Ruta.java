package com.ruta.sanJuanDePuelenje.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ruta_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = true)
	private Boolean state;
		
}
