package com.bikeproject.bikeRental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.dto.SearchBikeDto;
import com.bikeproject.bikeRental.services.auth.AdminServiceImpl;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	
	private final AdminServiceImpl adminService;
	
	@PostMapping(value = "/bike", consumes = "multipart/form-data")
	public ResponseEntity<?> postBike(@ModelAttribute BikeDto bikeDto) throws IOException{
		boolean success = adminService.postBike(bikeDto);
		System.out.println("entra en el metodo post del controler");
		System.out.println(bikeDto.getImage());
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	
		}			
	}
	
	@GetMapping("/bikes")
	public ResponseEntity<?> getAllBikes(){
		return ResponseEntity.ok(adminService.getAllBikes());
	}
	
	@DeleteMapping("/bike/{id}")
	public ResponseEntity<Long> deleteBike(@PathVariable Long id){
		adminService.deleteBike(id);
		return new ResponseEntity<Long>(id,HttpStatus.ACCEPTED);   
	}
	
	
	@GetMapping("/bike/{id}")
	public ResponseEntity<BikeDto> getBikeById( @PathVariable Long id  ){
		BikeDto bikeDto = adminService.getBikeById(id);
		return ResponseEntity.ok(bikeDto);
	}
	
	@PutMapping("/bike/{id}")
	public ResponseEntity<Void> updateBike(@PathVariable Long id , @ModelAttribute BikeDto bikeDto){
		try {
			boolean success = adminService.updateBike(id, bikeDto);
			if(success) return ResponseEntity.status(HttpStatus.OK).build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
		catch(Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/bike/bookings")
	public ResponseEntity<List<BookBikeDto>> getBookings(){
		return ResponseEntity.ok(adminService.getBookings());
	}
	
	
	@GetMapping("/bike/booking/{bookingId}/{status}")
	public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId,@PathVariable String status){
	boolean success =	adminService.changeBookingStatus(bookingId, status);
		if(success)return ResponseEntity.ok().build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/bike/search")
	public ResponseEntity<?> searchBike(@RequestBody SearchBikeDto searchBikeDto){
		return ResponseEntity.ok(adminService.searchBike(searchBikeDto));
	}
	
}
