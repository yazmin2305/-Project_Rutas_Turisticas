package com.ruta.sanJuanDePuelenje.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserve_lunch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLunch {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cantidadP")
	private Integer cantidad;
	
    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;
    
    @ManyToOne
    @JoinColumn(name = "lunch_id")
    private Lunch lunch;

}
