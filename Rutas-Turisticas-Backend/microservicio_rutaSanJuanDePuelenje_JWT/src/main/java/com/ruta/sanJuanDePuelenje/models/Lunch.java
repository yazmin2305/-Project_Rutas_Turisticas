package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lunch")
@Getter
@Data
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
	
	@Column(nullable = true)
	private Boolean state;
	
	
	@ManyToMany(mappedBy = "LstLunch")
	private List<Reserve> LstReserve ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ruta_id")
	private Ruta ruta;
	
}
