package com.ruta.sanJuanDePuelenje.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reserve_lodging")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReserveLodging {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cantidadP")
	private Integer cantidad;
	
    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;
    
    @ManyToOne
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;
}
