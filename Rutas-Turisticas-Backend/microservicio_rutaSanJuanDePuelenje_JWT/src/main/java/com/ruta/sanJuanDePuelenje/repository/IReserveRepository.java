package com.ruta.sanJuanDePuelenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ruta.sanJuanDePuelenje.models.Reserve;
import com.ruta.sanJuanDePuelenje.models.User;

public interface IReserveRepository extends JpaRepository<Reserve, Integer>{
	
	//Query que obtiene todas las reservas que ha realizado un usuario
	@Query(value = "SELECT * FROM reserve WHERE user_id = ?1", nativeQuery = true )
	List<Reserve> reservasUsuario(Integer id);
	
	//Query que obtiene todos los usuarios que han hecho reservas en una determinada ruta
	@Query(value = "SELECT r.user FROM reserve r WHERE r.ruta.ruta_id =? 1", nativeQuery = true)
	public List<User> LstUserByRuta(Integer idRuta);
	
	//Query que permite a un administrador consultar solo las reservas que se han realizado en su ruta
	@Query(value = "SELECT * FROM reserve WHERE r.ruta.ruta_id = ?1", nativeQuery = true)
	public List<Reserve> LstReserveByRuta(Integer idRuta);
}
