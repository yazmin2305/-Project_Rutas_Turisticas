package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ruta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ruta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ruta_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@OneToMany(mappedBy = "ruta")
	private List<Talking> LstTalking;
	
	@OneToMany(mappedBy = "ruta")
	private List<Workshop> LstWorkshop;

	@OneToMany(mappedBy = "ruta")
	private List<Recreation> LstRecreation;
	
	@OneToMany(mappedBy = "ruta")
	private List<Lodging> LstLodging;
	
	@OneToMany(mappedBy = "ruta")
	private List<Festival> LstFestival;
	
	@OneToMany(mappedBy = "ruta")
	private List<Finca> LstFinca;

	@OneToMany(mappedBy = "ruta")
	private List<Lunch> LstLunch;
	
	@OneToMany(mappedBy = "ruta")
	private List<Reserve> LstReserve;
	
	@OneToMany(mappedBy = "ruta")
	private List<User> LstUser;
		
	
}
