package com.bikeproject.bikeRental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bikeproject.bikeRental.dto.MiembroClubDto;
import com.bikeproject.bikeRental.services.auth.MiembroClubService;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MiembroClubController {

	private final MiembroClubService ms;
	
	@PostMapping()
	public ResponseEntity<?> postMiembro(@RequestBody MiembroClubDto miembroDto) throws IOException{
		System.out.println(miembroDto.getEmail());
		System.out.println(miembroDto.getPhoneNumber());
		System.out.println(miembroDto.getUserName());
           ms.saveMiembroclub(miembroDto);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}			
	}
	

