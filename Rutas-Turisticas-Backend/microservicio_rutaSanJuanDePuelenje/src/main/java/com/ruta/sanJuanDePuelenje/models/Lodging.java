package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@Column(name = "number_nights" ,nullable = true)
	private Integer numberNights;
	
	@Column(name = "max_amount_person", nullable = false)
	private Integer maxAmountPerson;
	
	@Column(name = "unit_price" ,nullable = false)
	private Double unitPrice;
	
	@Column(name = "total_price" , nullable = true)
	private Double totalPrice;
	
	@Column(nullable = false)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "lodging")
	private List<Reserve> LstReserve ;
}
