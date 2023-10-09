package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lunch")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lunch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lunch_id")
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String menu;
	
	@Column(name = "unit_price" ,nullable = false)
	private Double unitPrice;
	
//	@Column(name = "total_price" , nullable = true)
//	private Double totalPrice;
	
	@Column(nullable = true)
	private Boolean state;
	
	@OneToMany(mappedBy = "lunch")
	private List<Reserve> LstReserve ;
}
