package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String identification;
	
	@Column(nullable = false, length = 45)
	//@Value("${usuario.nombre.novalido}")
	private String name;
	
	@Column(name = "last_name" ,nullable = false, length = 45)
	private String lastName;
	
	@Column(nullable = false, unique = true, length = 20)
	private String phone;
	
	@Column(nullable = false, unique = true, length = 30)
	@Email
	private String email;
	
	@Column(nullable = true)
	private Boolean state;
	
	@Column(nullable = false, length = 20)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", nullable = false)
	private Role role;
	
	@OneToMany(mappedBy = "user")
	private List<Reserve> LstReserve ;
}

