package com.ruta.sanJuanDePuelenje.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserve")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Reserve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserve_id")
	private Integer id;
	
	@Column(name = "amount_persons", nullable = false)
	private Integer amountPersons;
	
	@Column(name = "total_price" ,nullable = false)
	private Double totalPrice;
	
	//fechas falta 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "workshop_id")
	private Workshop workshop;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "charla_id")
	private Charla charla;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recreation_id")
	private Recreation recreation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lodging_id")
	private Lodging lodging;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "festival_id")
	private Festival festival;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lunch_id")
	private Lunch lunch;
}
