package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "finca")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Finca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "finca_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(nullable = false, length = 200)
	private String location;
	
	@OneToMany(mappedBy = "finca")
	private List<Talking> LstTalking;
	
	@OneToMany(mappedBy = "finca")
	private List<Workshop> LstWorkshop;

	@OneToMany(mappedBy = "finca")
	private List<Recreation> LstRecreation;
	
	@OneToMany(mappedBy = "finca")
	private List<Lodging> LstLodging;
	
	@OneToMany(mappedBy = "finca")
	private List<Festival> LstFestival;
}
