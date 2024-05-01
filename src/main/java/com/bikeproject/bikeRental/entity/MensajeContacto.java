package com.bikeproject.bikeRental.entity;

import com.bikeproject.bikeRental.dto.MensajeContactoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MensajeContacto {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String email;
	
	private String mensaje;
	
	private Boolean contestado = false;
	
	public MensajeContactoDto getMensajeDto() {
		MensajeContactoDto mensajeContacto = new MensajeContactoDto();
		mensajeContacto.setEmail(email);
		mensajeContacto.setId(id);
		mensajeContacto.setMensaje(mensaje);
		mensajeContacto.setNombre(nombre);
		mensajeContacto.setContestado(contestado);
		return mensajeContacto;
	}
	
}
