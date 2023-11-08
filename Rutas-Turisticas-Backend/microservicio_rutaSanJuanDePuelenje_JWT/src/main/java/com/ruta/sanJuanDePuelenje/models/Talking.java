package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "talking")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Talking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "talking_id")
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(nullable = false)
	private Integer duration;
	
	@Column(nullable = false)
	private Boolean availability;
	
	@Column(name = "max_amount_person", nullable = false)
	private Integer maxAmountPerson;
	
	@Column(name = "unit_price" ,nullable = false)
	private Double unitPrice;
	
	@Column(nullable = true)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@ManyToMany(mappedBy = "LstTalking")
	private List<Reserve> LstReserve;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ruta_id")
	private Ruta ruta;
}
