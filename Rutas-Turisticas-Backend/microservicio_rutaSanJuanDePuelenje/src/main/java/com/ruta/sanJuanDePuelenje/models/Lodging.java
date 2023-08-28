package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lodging")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lodging {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hospedaje_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(name = "beds_available" ,nullable = false)
	private Integer bedsAvailable;
	
	@Column(name = "number_night" ,nullable = true)
	private Integer numberNight;
	
	@Column(name = "cant_max_personas", nullable = false)
	private Integer MaxAmountPerson;
	
	@Column(name =  "unit_price" ,nullable = false)
	private Double unitPrice;
	
	@Column(name = "total_price" ,nullable = true)
	private Double totalPrice;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "hospedaje")
	private List<Reserve> LstReserva ;
}
