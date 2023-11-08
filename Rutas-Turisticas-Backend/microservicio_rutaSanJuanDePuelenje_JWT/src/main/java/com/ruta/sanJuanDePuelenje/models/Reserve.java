package com.ruta.sanJuanDePuelenje.models;

import java.util.Date;
import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserve")
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Reserve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserve_id")
	private Integer id;
	
	@Column(name = "amount_persons", nullable = false)
	private Integer amountPersons;
	
//	@Column(nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date fechaInicio;
//	
//	@Column(nullable = false)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date fechaFin;
	
	//si el usuario va a reservar hospedaje debe añadir que tantas noches se va a quedar
	@Column(name = "number_nights" ,nullable = true)
	private Integer numberNights;
	
	@Column(name = "total_price" , nullable = true)
	private Double totalPriceReserve;
	
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
	
	@Nullable
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reserve_workshop", joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id"),
			inverseJoinColumns = @JoinColumn(name = "workshop_id", referencedColumnName = "workshop_id")
	)
	private List<Workshop> LstWorkshop;

	@Nullable
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reserve_talking", joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id"),
			inverseJoinColumns = @JoinColumn(name = "talking_id", referencedColumnName = "talking_id")
	)
	private List<Talking> LstTalking;
	
	@Nullable
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reserve_recreation", joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id"),
			inverseJoinColumns = @JoinColumn(name = "recreation_id", referencedColumnName = "recreation_id")
	)
	private List<Recreation> LstRecreation;
	
	@Nullable
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reserve_lodging", joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id"),
			inverseJoinColumns = @JoinColumn(name = "lodging_id", referencedColumnName = "lodging_id")
	)
	private List<Lodging> LstLodging;
	
	@Nullable
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
			name = "reserve_lunch", joinColumns = @JoinColumn(name = "reserve_id", referencedColumnName = "reserve_id"),
			inverseJoinColumns = @JoinColumn(name = "lunch_id", referencedColumnName = "lunch_id")
	)
	private List<Lunch> LstLunch;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ruta_id")
	private Ruta ruta;
}
