package com.ruta.sanJuanDePuelenje.models;

import java.util.Date;

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
	
	@Column(name = "total_price" , nullable = false)
	private Double totalPrice;
	
	@Column(name = "lodging_total_price" , nullable = true)
	private Double totalPriceLodging;
	
	@Column(name = "lunch_total_price" , nullable = true)
	private Double totalPriceLunch;
	
	@Column(name = "talking_total_price" , nullable = true)
	private Double totalPriceTalking;
	
	@Column(name = "recreation_total_price" , nullable = true)
	private Double totalPriceRecreation;
	
	@Column(name = "workshop_total_price" , nullable = true)
	private Double totalPriceWorkshop;
	
	@Column(nullable = true)
	private Boolean state;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "workshop_id", nullable = true)
	private Workshop workshop;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "talking_id", nullable = true)
	private Talking talking;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recreation_id", nullable = true)
	private Recreation recreation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lodging_id", nullable = true)
	private Lodging lodging;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "festival_id", nullable = true)
	private Festival festival;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lunch_id", nullable = true)
	private Lunch lunch;
}
