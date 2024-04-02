package com.bikeproject.bikeRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeproject.bikeRental.entity.Bike;


@Repository
public interface BikeRepository extends JpaRepository<Bike, Long>{
	
	

}
