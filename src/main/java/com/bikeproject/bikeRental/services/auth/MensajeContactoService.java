package com.bikeproject.bikeRental.services.auth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bikeproject.bikeRental.dto.MensajeContactoDto;
import com.bikeproject.bikeRental.entity.MensajeContacto;
import com.bikeproject.bikeRental.repository.MensajeContactoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class MensajeContactoService {

	private final MensajeContactoRepository mensajeRepository;
	
	 public List<MensajeContactoDto> getAllMensajeContacto() {		
		return mensajeRepository.findAll().stream().map(MensajeContacto::getMensajeDto).collect(Collectors.toList());
	}

	 
	 
	 public boolean saveMensajeDto(MensajeContactoDto mensajeContactoDto) {
		 
		 System.out.println(mensajeContactoDto);
			try {
				   MensajeContacto mc = new MensajeContacto();
				  mc.setId(mensajeContactoDto.getId());	
				  mc.setEmail(mensajeContactoDto.getEmail());	
				  mc.setMensaje(mensajeContactoDto.getMensaje());	
				  mc.setNombre(mensajeContactoDto.getNombre());	
				
					mensajeRepository.save(mc);
					
					return true;
				}catch (Exception e) {
					
					return false;
				}
				
			}
		
	 @Transactional
	 public void cambiarContestado(Long mensajeId) {
		 Optional<MensajeContacto> mensajeOptional = mensajeRepository.findById(mensajeId);
		 mensajeOptional.ifPresent(mensaje -> {
	            mensaje.setContestado(true);
	            mensajeRepository.save(mensaje);
	        });
	 }
	 
	 
	 
	 }
	 
	 
	

