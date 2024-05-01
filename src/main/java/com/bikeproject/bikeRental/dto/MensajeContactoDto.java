package com.bikeproject.bikeRental.dto;

import lombok.Data;

@Data
public class MensajeContactoDto {

private Long id;
	
	private String nombre;
	
	private String email;
	
	private String mensaje;
	
	private Boolean contestado = false;
	
}
