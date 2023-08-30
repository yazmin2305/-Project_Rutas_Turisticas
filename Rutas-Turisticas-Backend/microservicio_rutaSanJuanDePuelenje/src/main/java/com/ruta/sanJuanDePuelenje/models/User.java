package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
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
	
	@Column(nullable = false)
	private String identification;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(name = "last_name" ,nullable = false, length = 45)
	private String lastName;
	
	@Column(nullable = false, length = 20)
	private String phone;
	
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String passsword;
	
	@OneToOne(mappedBy = "user")
	private Role role;
	
	@OneToMany(mappedBy = "user")
	private List<Reserve> LstReserve ;
}

