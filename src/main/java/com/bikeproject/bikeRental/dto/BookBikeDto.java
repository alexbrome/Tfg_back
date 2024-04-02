package com.bikeproject.bikeRental.dto;

import java.util.Date;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;

import lombok.Data;

@Data
public class BookBikeDto {

	private Long id;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Long days;
	
	private Long price;
	
	private BookBikeStatus bookBikeStatus;
	
	private Long bikeId;
	
	private Long userId;
	
	private String userName;
	
	private String email;
}
