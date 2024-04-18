package com.bikeproject.bikeRental.services.auth;

import org.springframework.stereotype.Service;

import com.bikeproject.bikeRental.dto.MiembroClubDto;
import com.bikeproject.bikeRental.entity.MiembroClub;
import com.bikeproject.bikeRental.repository.MiembroClubRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MiembroClubService {

    private final MiembroClubRespository mr;
    
    //Guardar miembro club
    public void saveMiembroclub(MiembroClubDto miembro) {
        MiembroClub nuevoMiembro = new MiembroClub();
        nuevoMiembro.setUserName(miembro.getUserName());
        nuevoMiembro.setEmail(miembro.getEmail());
        nuevoMiembro.setPhoneNumber(miembro.getPhoneNumber());
        mr.save(nuevoMiembro);
    }
    
}

