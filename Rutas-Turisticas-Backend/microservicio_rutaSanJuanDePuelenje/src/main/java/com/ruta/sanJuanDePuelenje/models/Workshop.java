package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "workshop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Workshop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workshop_id")
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
	
	@Column(name = "total_price" , nullable = true)
	private Double totalPrice;
	
	@Column(nullable = true)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "workshop_type_id")
	private WorkshopType workshopType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "workshop")
	private List<Reserve> LstReserve ;
}
