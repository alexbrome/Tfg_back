package com.bikeproject.bikeRental.services.auth;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {

	UserDetailsService userDetailsService();
	
	String getEmailByUserId(Long id);
	
	
}
