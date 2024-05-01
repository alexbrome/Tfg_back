package com.bikeproject.bikeRental.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bikeproject.bikeRental.dto.MensajeContactoDto;
import com.bikeproject.bikeRental.services.auth.MensajeContactoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mensaje")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MensajeContactoController {

	
	private final MensajeContactoService ms; 
	
	@PostMapping("/save")
	public ResponseEntity<?> saveMensajeDto(@RequestBody MensajeContactoDto mensajeDto){		
		boolean success = ms.saveMensajeDto(mensajeDto);
        return (success) ? ResponseEntity.status(HttpStatus.CREATED).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;		
	}
		
	
	@GetMapping("")
	public ResponseEntity<?> getAllmensajeDto(){
		return ResponseEntity.ok(ms.getAllMensajeContacto());
	} 
	
	
	@PostMapping("/contestado")
	public ResponseEntity<?> cambiarContestado(@RequestBody Long mensajeId) {
	    ms.cambiarContestado(mensajeId);
	    return ResponseEntity.ok().build();
	}
	
	
	
	}
	

