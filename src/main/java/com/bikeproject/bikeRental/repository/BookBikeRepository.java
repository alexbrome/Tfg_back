package com.bikeproject.bikeRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeproject.bikeRental.entity.BookBike;
@Repository
public interface BookBikeRepository extends JpaRepository<BookBike,Long>{

	
	List<BookBike> findAllByUserId(Long userId);
	
}
