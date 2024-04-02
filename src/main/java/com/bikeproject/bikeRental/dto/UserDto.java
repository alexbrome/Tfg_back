package com.bikeproject.bikeRental.dto;

import com.bikeproject.bikeRental.Enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	
	private String name;
	
	private String email;
	
	private UserRole userRole;
	
}
