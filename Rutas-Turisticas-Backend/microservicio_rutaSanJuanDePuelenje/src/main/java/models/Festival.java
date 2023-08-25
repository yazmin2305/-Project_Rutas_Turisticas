package models;

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
	private String nombre;
	
	@Column(nullable = false, length = 200)
	private String descripcion;
	
	@Column(nullable = false)
	
	private Date fecha;
	//@Temporal(TemporalType.timestamp)
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "finca_id")
	private Finca finca;
	
	@OneToMany(mappedBy = "festival")
	private List<Reserva> LstReserva ;
}
