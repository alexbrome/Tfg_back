package com.bikeproject.bikeRental.services.auth;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.entity.Bike;
import com.bikeproject.bikeRental.entity.BookBike;
import com.bikeproject.bikeRental.entity.User;
import com.bikeproject.bikeRental.repository.BikeRepository;
import com.bikeproject.bikeRental.repository.BookBikeRepository;
import com.bikeproject.bikeRental.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	private final BikeRepository bikeRepository;
	
	private final UserRepository userRepository;
	
	private final BookBikeRepository bookBikeRepository;

	//Get all Bikes
	@Override
	public List<BikeDto> getAllBikes() {		
		return bikeRepository.findAll().stream().map(Bike::getBikeDto).collect(Collectors.toList());
	}

//	@Override
//	public boolean bookBike(BookBikeDto bookBikeDto) {
//		Optional<Bike> optionalBike = bikeRepository.findById(bookBikeDto.getBikeId());
//		Optional<User> optionalUser = userRepository.findById(bookBikeDto.getUserId());
//		if(optionalBike.isPresent() && optionalUser.isPresent()) {
//			Bike existingBike = optionalBike.get();
//			BookBike bookBike = new BookBike();
//			bookBike.setUser(optionalUser.get() );
//			bookBike.setBike(existingBike);
//			bookBike.setBookBikeStatus(BookBikeStatus.PENDIENTE);
//			long difInMilliSeconds = bookBikeDto.getToDate().getTime() - bookBikeDto.getFromDate().getTime();
//			long days = TimeUnit.MILLISECONDS.toDays(difInMilliSeconds);
//			bookBike.setDays(days);
//			String priceString = existingBike.getPrecio();
//			int priceInt = Integer.parseInt(priceString);
//			bookBike.setPrice(priceInt * days);
//			bookBike.setFromDate((Date) bookBikeDto.getFromDate());
//			bookBike.setToDate((Date) bookBikeDto.getToDate());
//			bookBikeRepository.save(bookBike);
//			return true;
//		}
//		return false;
//	}

	//Booka Bike
	@Override
	public boolean bookBike(BookBikeDto bookBikeDto) {
	    Optional<Bike> optionalBike = bikeRepository.findById(bookBikeDto.getBikeId());
	    Optional<User> optionalUser = userRepository.findById(bookBikeDto.getUserId());
	    if (optionalBike.isPresent() && optionalUser.isPresent()) {
	        Bike existingBike = optionalBike.get();
	        BookBike bookBike = new BookBike();
	        bookBike.setUser(optionalUser.get());
	        bookBike.setBike(existingBike);
	        bookBike.setBookBikeStatus(BookBikeStatus.PENDIENTE);
	        long difInMilliSeconds = bookBikeDto.getToDate().getTime() - bookBikeDto.getFromDate().getTime();
	        long days = TimeUnit.MILLISECONDS.toDays(difInMilliSeconds);
	        bookBike.setDays(days);
	        String priceString = existingBike.getPrecio();
	        int priceInt = Integer.parseInt(priceString);
	        bookBike.setPrice(priceInt * days);
	        
	        // Convertir java.util.Date a java.sql.Date
	        java.sql.Date fromDateSql = new java.sql.Date(bookBikeDto.getFromDate().getTime());
	        java.sql.Date toDateSql = new java.sql.Date(bookBikeDto.getToDate().getTime());
	        
	        bookBike.setFromDate(fromDateSql);
	        bookBike.setToDate(toDateSql);
	        bookBikeRepository.save(bookBike);
	        return true;
	    }
	    return false;
	}

	
	//Get bike by id
	@Override
	public BikeDto getBikeById(Long idBike) {
		Optional<Bike> optionalBike = bikeRepository.findById(idBike);
		return optionalBike.map(Bike::getBikeDto).orElse(null);
	}

	
	//Get booking by user id
	@Override
	public List<BookBikeDto> getBookingsByUserId(Long userId) {
		
		return bookBikeRepository.findAllByUserId(userId).stream().map(BookBike::getBookBikeDto).collect(Collectors.toList()) ;
	}
	
	
	
	
}
