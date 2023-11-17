package com.ruta.sanJuanDePuelenje.models;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "lunch")
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

//	@ManyToMany(mappedBy = "LstLunch")
//	private List<Reserve> LstReserve ;
	
	@OneToMany(mappedBy = "lunch" , cascade = CascadeType.MERGE)
    private List<ReserveLunch> reserveLunch ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ruta_id")
	@NotNull
	private Ruta ruta;
	
	
}
