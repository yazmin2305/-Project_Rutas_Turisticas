package models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospedaje")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospedaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hospedaje_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 200)
	private String descripcion;
	
	@Column(nullable = false)
	private Integer camas_disponibles;
	
	@Column(nullable = true)
	private Integer numeroNoches;
	
	@Column(name = "cant_max_personas", nullable = false)
	private Integer cantidadMaxPersonas;
	
	@Column(nullable = false)
	private Double precioUnitario;
	
	@Column(nullable = true)
	private Double precioTotal;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "hospedaje")
	private List<Reserva> LstReserva ;
}
