package com.ruta.sanJuanDePuelenje.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 

public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Integer id;
	
	@Column(nullable = false)
	private String identificacion;
	
	@Column(nullable = false, length = 45)
	private String nombre;
	
	@Column(nullable = false, length = 45)
	private String apellido;
	
	@Column(nullable = false, length = 20)
	private String telefono;
	
	@Column(nullable = false, length = 30)
	private String correo;
	
	@Column(nullable = false, length = 20)
	private String contraseña;
	
	@OneToOne(mappedBy = "usuario")
	private Rol rol;
	
	@OneToMany(mappedBy = "usuario")
	private List<Reserva> LstReserva ;
}

