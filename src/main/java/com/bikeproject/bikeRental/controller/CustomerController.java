package com.bikeproject.bikeRental.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.services.auth.CustomerService;
import com.bikeproject.bikeRental.services.auth.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class CustomerController {

	
	private final CustomerService customerService;
	
	private final UserServiceImpl us;
	
	@GetMapping("/bikes")
	public ResponseEntity<List<BikeDto>> getAllBikes(){
		return ResponseEntity.ok(customerService.getAllBikes());
	}
	
	
	@PostMapping("/bike/book")
	public ResponseEntity<Void> bookBike(@RequestBody BookBikeDto bookBikeDto){
		boolean success = customerService.bookBike(bookBikeDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@GetMapping("/bike/{bikeId}")
	public ResponseEntity<BikeDto> getBikeById(@PathVariable Long bikeId){
		BikeDto bikeDto = customerService.getBikeById(bikeId);
		if(bikeDto == null)return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bikeDto);
	}
	
	@GetMapping("/bike/bookings/{userId}")
	public ResponseEntity<List<BookBikeDto>> getBookingsByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
	}
	
	@GetMapping("/bike/bookings/email/{id}")
	public ResponseEntity<Map<String, String>> getEmailByUserId(@PathVariable Long id) {
	    String email = us.getEmailByUserId(id);
	    Map<String, String> response = new HashMap<>();
	    response.put("email", email);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/bike/bookings/bike/{bikeId}")
	public ResponseEntity<List<BookBikeDto>> getBookingsByBikeId(@PathVariable Long bikeId){
		return ResponseEntity.ok(customerService.getBookingsByBikeId(bikeId));
	}
	
}
