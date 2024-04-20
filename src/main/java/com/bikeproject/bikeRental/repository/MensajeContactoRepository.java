package com.bikeproject.bikeRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeproject.bikeRental.entity.MensajeContacto;


@Repository
public interface MensajeContactoRepository extends JpaRepository<MensajeContacto, Long> {

}
