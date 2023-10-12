package com.ruta.sanJuanDePuelenje.models;

import java.util.Date;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private String imagen;
	
	@Column(nullable = true)
	private Boolean state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
}
