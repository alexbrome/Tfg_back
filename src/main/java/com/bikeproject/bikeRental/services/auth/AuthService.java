package com.bikeproject.bikeRental.services.auth;



import com.bikeproject.bikeRental.dto.SignupRequest;
import com.bikeproject.bikeRental.dto.UserDto;

public interface AuthService {
	
	UserDto createCustomer(SignupRequest signupRequest);
	
	boolean hasCustomerWithEmail(String email);
}
