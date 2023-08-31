package com.ruta.sanJuanDePuelenje.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "festival")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "festival_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String description;
	
	@Column(nullable = false)
	private Date date;
	//@Temporal(TemporalType.timestamp)
	
	@Column(nullable = false)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "festival")
	private List<Reserve> LstReserve ;
}
