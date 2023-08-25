package models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "almuerzo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Almuerzo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "almuerzo_id")
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String menu;
	
	@Column(name = "precio_unitario", nullable = false)
	private Double precioUnitario;
	
	@Column(name = "precio_total", nullable = true)
	private Double precioTotal;
	
	@OneToMany(mappedBy = "almuerzo")
	private List<Reserva> LstReserva ;
}
