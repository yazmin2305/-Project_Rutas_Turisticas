package com.ruta.sanJuanDePuelenje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.ruta.sanJuanDePuelenje.models.Reserve;

public interface IReserveRepository extends JpaRepository<Reserve, Integer> {

	// Query que obtiene todas las reservas que ha realizado un usuario
	@Query(value = "SELECT * FROM reserve WHERE user_id = ?1", nativeQuery = true)
	Page<Reserve> reservasUsuario(Integer idUser, Pageable pageable);

	// Query que permite a un administrador consultar solo las reservas que se han realizado en su ruta
	@Query(value = "SELECT r FROM Reserve r WHERE r.ruta.id = ?1", nativeQuery = false)
	Page<Reserve> LstReserveByRuta(Integer idRuta, Pageable pageable);

}
