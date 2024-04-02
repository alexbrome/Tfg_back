package com.bikeproject.bikeRental.dto;

import com.bikeproject.bikeRental.Enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	
	private UserRole userRole;
	
	private Long userId;
	
}
