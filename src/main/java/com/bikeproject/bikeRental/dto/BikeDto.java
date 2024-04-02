package com.bikeproject.bikeRental.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BikeDto {

	
   private Long id;
	
	private String brand;
	
	private String name;
	
	private String type;
	
	private String transmision;
	
	private String precio;
	
	private MultipartFile image;
	
	private  byte[] returnedImage;
	
	
}
