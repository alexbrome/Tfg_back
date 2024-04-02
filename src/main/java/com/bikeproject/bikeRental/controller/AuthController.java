package com.bikeproject.bikeRental.controller;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeproject.bikeRental.Utils.JWTUtil;
import com.bikeproject.bikeRental.dto.AuthenticationRequest;
import com.bikeproject.bikeRental.dto.AuthenticationResponse;
import com.bikeproject.bikeRental.dto.SignupRequest;
import com.bikeproject.bikeRental.dto.UserDto;
import com.bikeproject.bikeRental.entity.User;
import com.bikeproject.bikeRental.repository.UserRepository;
import com.bikeproject.bikeRental.services.auth.AuthService;
import com.bikeproject.bikeRental.services.auth.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	

	private  final AuthService authService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserService userService;
	
	private final JWTUtil jwtUtil;
	
	private final UserRepository userRepository;
	
	
	
	//Metodo para guardar usuarios(No admin)
	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest ){
		
		//Si el email ya existe
		if(authService.hasCustomerWithEmail(signupRequest.getEmail())) {		
			//System.out.println("entra en ya existe el mail");
		return new ResponseEntity<>("Customer already exists with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto createdCustomerDto = authService.createCustomer(signupRequest);
		
        //Si el usuario introducido es nulo
		if(createdCustomerDto==null) {			
			return new ResponseEntity<>("Customer not created, some fields errors",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(createdCustomerDto,HttpStatus.CREATED);
	}
	
   //Metodo para manejar el login
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws 
	BadCredentialsException,
	DisabledException,
	UsernameNotFoundException{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Nombre de ususario o password incorrecto");
		}
		final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		if(optionalUser.isPresent()) {
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserId(optionalUser.get().getId());
			authenticationResponse.setUserRole(optionalUser.get().getUserRole());			
		}
		return authenticationResponse;
	}
	
	
	
	
	
	
	
	
	
}
