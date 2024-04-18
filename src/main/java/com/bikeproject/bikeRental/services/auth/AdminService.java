package com.bikeproject.bikeRental.services.auth;

import java.util.List;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BikeDtoListDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.dto.SearchBikeDto;

import io.jsonwebtoken.io.IOException;

public interface AdminService {

	
	//CRUD
	boolean postBike(BikeDto bikeDto)throws IOException;
	
	List<BikeDto> getAllBikes();
	
	void deleteBike(Long id);
	
	BikeDto getBikeById(Long id);
	
	boolean updateBike(Long bikeId , BikeDto bikeDto);
	
	
	//Listar booking
	List<BookBikeDto> getBookings();
	
	//Cambiar estatus 
	Boolean changeBookingStatus(Long bookingId,String status);
	
	
	//Busar bikes
	BikeDtoListDto searchBike(SearchBikeDto searchBikeDto);
	
}
