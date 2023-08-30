package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "taller")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Taller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taller_id")
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String nombre;
	
	@Column(nullable = false, length = 200)
	private String descripcion;
	
	@Column(nullable = false)
	private Integer duracion;
	
	@Column(nullable = false)
	private Boolean disponibilidad;
	
	@Column(name = "cant_max_personas", nullable = false)
	private Integer cantidadMaxPersonas;
	
	@Column(nullable = false)
	private Double precioUnitario;
	
	@Column(nullable = true)
	private Double precioTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipo_taller_id")
	private TipoTaller tipoTaller;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "taller")
	private List<Reserva> LstReserva ;
}
