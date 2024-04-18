package com.bikeproject.bikeRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeproject.bikeRental.dto.MiembroClubDto;
import com.bikeproject.bikeRental.entity.MiembroClub;

@Repository
public interface MiembroClubRespository extends JpaRepository<MiembroClub, Long>{

//	public boolean save(MiembroClub nuevoMiembro);

}
