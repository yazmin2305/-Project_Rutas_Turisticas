package com.ruta.sanJuanDePuelenje.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;

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
	
	@Column(nullable = true)
	private Boolean state;
	
//	@Nullable
//	//@JsonIgnore
//	@OneToMany(mappedBy = "ruta")
//	private List<Talking> LstTalking;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Workshop> LstWorkshop;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Recreation> LstRecreation;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Lodging> LstLodging;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Festival> LstFestival;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Finca> LstFinca;
//
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Lunch> LstLunch;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<Reserve> LstReserve;
//	
//	@Nullable
//	@OneToMany(mappedBy = "ruta")
//	private List<User> LstUser;
		
	
}
