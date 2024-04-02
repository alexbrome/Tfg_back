package com.bikeproject.bikeRental.entity;

import org.springframework.web.multipart.MultipartFile;

import com.bikeproject.bikeRental.dto.BikeDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bike {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String brand;
	
	private String name;
	
	private String type;
	
	private String transmision;
	
	private String precio;
	
	@Column(columnDefinition = "longblob")
	private byte[] image;
	
	
	public BikeDto getBikeDto() {
		BikeDto bikeDto = new BikeDto();
		bikeDto.setId(id);
		bikeDto.setBrand(brand);
		bikeDto.setName(name);
		bikeDto.setType(type);
		bikeDto.setTransmision(transmision);
		bikeDto.setPrecio(precio);
		bikeDto.setReturnedImage(image);
		return bikeDto;
	}
	
}
