package com.bikeproject.bikeRental.services.auth;

import java.util.List;

import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;

public interface CustomerService {

	
	List<BikeDto> getAllBikes();

	boolean bookBike(BookBikeDto bookBikeDto);
	
	BikeDto getBikeById(Long idBike);
	
	List<BookBikeDto> getBookingsByUserId(Long userId);
	public List<BookBikeDto> getBookingsByBikeId(Long bikeId) ;
	
	
	
}
