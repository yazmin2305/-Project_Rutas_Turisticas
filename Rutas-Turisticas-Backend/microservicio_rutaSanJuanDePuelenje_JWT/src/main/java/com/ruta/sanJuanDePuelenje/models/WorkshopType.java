package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workshopType")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class WorkshopType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workshop_type_id")
	private Integer workshopTypeId;
	
	@Column(name = "name_workshop_type", nullable = false, length = 45)
	private String name;
	
	@Column(nullable = true)
	private Boolean state;
	
	@OneToMany(mappedBy = "workshopType")
	private List<Workshop> lstWorkshops;
	
}
