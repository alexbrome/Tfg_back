package com.bikeproject.bikeRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bikeproject.bikeRental.entity.MensajeContacto;


@Repository
public interface MensajeContactoRepository extends JpaRepository<MensajeContacto, Long> {

	
	 @Modifying
	    @Query("UPDATE MensajeContacto m SET m.contestado = true WHERE m.id = :id")
	    void cambiarContestado(@Param("id") Long id);
	
}
