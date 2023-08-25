package models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "finca")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Finca {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "finca_id")
	private Integer id;
	
	@Column(nullable = false, length = 50)
	private String nombre;
	
	@Column(nullable = false, length = 200)
	private String descripcion;
	
	@Column(nullable = false, length = 200)
	private String ubicacion;
	
	@OneToMany(mappedBy = "finca")
	private List<Charla> ListCharla;
	
	@OneToMany(mappedBy = "finca")
	private List<Taller> ListTaller;

	@OneToMany(mappedBy = "finca")
	private List<Recreacion> ListRecreacion;
	
	@OneToMany(mappedBy = "finca")
	private List<Hospedaje> ListHospedaje;
	
	@OneToMany(mappedBy = "finca")
	private List<Festival> ListFestival;
}
