package com.bikeproject.bikeRental.services.auth;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BikeDto;
import com.bikeproject.bikeRental.dto.BikeDtoListDto;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.dto.SearchBikeDto;
import com.bikeproject.bikeRental.entity.Bike;
import com.bikeproject.bikeRental.entity.BookBike;
import com.bikeproject.bikeRental.repository.BikeRepository;
import com.bikeproject.bikeRental.repository.BookBikeRepository;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final  BikeRepository bikeRepository;
	
	private final BookBikeRepository bookBikeRepository;

	
	//Guardar una bike
	@Override
	public boolean postBike(BikeDto bikeDto) throws IOException{
		
		try {
			Bike bike = new Bike();
			bike.setBrand(bikeDto.getBrand());
			bike.setType(bikeDto.getType());
			bike.setPrecio(bikeDto.getPrecio());
			bike.setName(bikeDto.getName());
			bike.setTransmision(bikeDto.getTransmision());
			bike.setImage(bikeDto.getImage().getBytes());
			bike.setId(bikeDto.getId());
			bikeRepository.save(bike);
			
			return true;
		}catch (Exception e) {
			
			return false;
		}
		
	}
	
	//Get All bikes
    @Override
	public List<BikeDto> getAllBikes(){
    	return bikeRepository.findAll().stream().map(Bike::getBikeDto).collect(Collectors.toList());
    }
		
	
	public byte[] getImageById(Long id) {
	    Bike bike = bikeRepository.findById(id).orElse(null);
	    return (bike != null) ? bike.getImage() : null;
	}

	//Borrar Bike por id
	@Override
	public void deleteBike(Long id) {
		bikeRepository.deleteById(id);						
	}

	@Override
	public BikeDto getBikeById(Long id) {
	Optional<Bike> optionalBike = bikeRepository.findById(id);
		return optionalBike.map(Bike::getBikeDto).orElse(null);
	}

	@Override//modificar para que no sea obligatorio cambiar la imagen
	public boolean updateBike(Long bikeId, BikeDto bikeDto) throws IOException{
		
		Optional<Bike> optionalbike = bikeRepository.findById(bikeId);
		if(optionalbike.isPresent()) {
			Bike existingBike = optionalbike.get();
			if(bikeDto.getImage()!=null) {
				try {
					existingBike.setImage(bikeDto.getImage().getBytes());
				} catch (java.io.IOException e) {
					
					e.printStackTrace();
				}
				existingBike.setBrand(bikeDto.getBrand());
				existingBike.setName(bikeDto.getName());
				existingBike.setPrecio(bikeDto.getPrecio());
				existingBike.setType(bikeDto.getType());
				bikeRepository.save(existingBike);
				return true;
			}else {
				return false;
					
			}
		}
		return false;
		
	}

	@Override
	public List<BookBikeDto> getBookings() {		
		return bookBikeRepository.findAll().stream().map(BookBike::getBookBikeDto).collect(Collectors.toList());
	}

	
	
	@Override
	public Boolean changeBookingStatus(Long bookingId, String status) {
		Optional<BookBike> optionalBike = bookBikeRepository.findById(bookingId);
		if(optionalBike.isPresent()) {
			BookBike existingBookBike = optionalBike.get();
			if(Objects.equals(status,"APROBADO")) 
				existingBookBike.setBookBikeStatus(BookBikeStatus.APROBADO);
			else 
				existingBookBike.setBookBikeStatus(BookBikeStatus.RECHAZADO);
			bookBikeRepository.save(existingBookBike);
			return true;
		}
		return false;
	}

	@Override
	public BikeDtoListDto searchBike(SearchBikeDto searchBikeDto) {
		Bike bike = new Bike();
		bike.setBrand(searchBikeDto.getBrand());
		bike.setType(searchBikeDto.getType());
		bike.setTransmision(searchBikeDto.getTransmision());
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
			    .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			    .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			    .withMatcher("transmision", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

		Example<Bike> bikeExample = Example.of(bike,exampleMatcher);
		List<Bike> bikeList = bikeRepository.findAll(bikeExample);
		BikeDtoListDto bikeDtoToList = new BikeDtoListDto();
		bikeDtoToList.setBikeDtoList(bikeList.stream().map(Bike::getBikeDto).collect(Collectors.toList()));
		return bikeDtoToList;
	}
	

	
	
	
	
	
	
	
}
