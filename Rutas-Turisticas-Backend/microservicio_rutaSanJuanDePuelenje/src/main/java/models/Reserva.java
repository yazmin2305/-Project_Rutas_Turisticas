package models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reserva_id")
	private Integer id;
	
	@Column(name = "cantidad_personas", nullable = false)
	private Integer cantidadPersonas;
	
	@Column(nullable = true)
	private Double precioTotal;
	
	//fechas falta 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "taller_id")
	private Taller taller;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "charla_id")
	private Charla charla;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recreacion_id")
	private Recreacion recreacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospedaje_id")
	private Hospedaje hospedaje;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "festival_id")
	private Festival festival;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "almuerzo_id")
	private Almuerzo almuerzo;
}
