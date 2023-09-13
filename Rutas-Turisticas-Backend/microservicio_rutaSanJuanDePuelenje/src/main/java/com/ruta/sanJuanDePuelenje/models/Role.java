package com.ruta.sanJuanDePuelenje.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;
	
	@Column(name = "name_rol", nullable = false, length = 15)
	private String name;
	
//	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "user_id")
//	private User user;
	
	@OneToOne(mappedBy = "role")
	private User userRol;
}
