package com.ruta.sanJuanDePuelenje.models;

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
	
	@Column(nullable = false, length = 60)
	private String password;
	
	/*Este campo queda vacio ya que solo se van a crear usuarios con rol USER,
	 * los usuarios con Rol ADMIN van a estar almacenados previamente en la base de datos */
	//Esta relacion se cambio por unidireccional ya que no necesito acceser a la lista de usuarios por medio de la entidad Role
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rol_id", nullable = true)
	private Role role;
	
//	@OneToMany(mappedBy = "user")
//	private List<Reserve> LstReserve ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ruta_id")
	private Ruta ruta;
}

