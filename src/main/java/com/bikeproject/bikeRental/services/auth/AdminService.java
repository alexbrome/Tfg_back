package com.bikeproject.bikeRental.services.auth;

import java.util.List;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BikeDtoListDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.dto.SearchBikeDto;

import io.jsonwebtoken.io.IOException;

public interface AdminService {

	
	
	boolean postBike(BikeDto bikeDto)throws IOException;
	
	List<BikeDto> getAllBikes();
	
	void deleteBike(Long id);
	
	BikeDto getBikeById(Long id);
	
	boolean updateBike(Long bikeId , BikeDto bikeDto);
	
	List<BookBikeDto> getBookings();
	
	
	Boolean changeBookingStatus(Long bookingId,String status);
	
	BikeDtoListDto searchBike(SearchBikeDto searchBikeDto);
	
}
