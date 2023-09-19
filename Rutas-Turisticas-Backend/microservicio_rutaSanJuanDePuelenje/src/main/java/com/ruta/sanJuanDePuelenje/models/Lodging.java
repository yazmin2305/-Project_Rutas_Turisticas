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
	@Column(name = "lodging_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(name = "beds_available" ,nullable = false)
	private Integer bedsAvailable;
	
	@Column(name = "max_amount_person",nullable = true)
	private Integer maxAmountPerson;
	
	@Column(name = "unit_price" ,nullable = false)
	private Double unitPrice;
	
	@Column(name = "total_price" , nullable = true)
	private Double totalPrice;
	
	@Column(name = "number_nights" ,nullable = true)
	private Integer numberNights;
	
	@Column(nullable = true)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "lodging")
	private List<Reserve> LstReserve ;
}
