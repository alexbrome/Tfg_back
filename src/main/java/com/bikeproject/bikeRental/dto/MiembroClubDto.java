package com.bikeproject.bikeRental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiembroClubDto {

	private String userName;
	private String email;
	private String phoneNumber;
	
}
