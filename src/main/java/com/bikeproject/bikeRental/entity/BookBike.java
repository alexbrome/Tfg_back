package com.bikeproject.bikeRental.entity;

import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookBike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Long days;
	
	private long price;
	
	private BookBikeStatus bookBikeStatus;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	@OnDelete(action =OnDeleteAction.CASCADE )
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "bike_id",nullable = false)
	@OnDelete(action =OnDeleteAction.CASCADE )
	@JsonIgnore
	private Bike bike;
	
	public BookBikeDto getBookBikeDto() {
		BookBikeDto bookBikeDto = new BookBikeDto();
		bookBikeDto.setId(id);
		bookBikeDto.setDays(days);
		bookBikeDto.setBookBikeStatus(bookBikeStatus);
		bookBikeDto.setPrice(price);
		bookBikeDto.setToDate(toDate);
		bookBikeDto.setFromDate(fromDate);
		bookBikeDto.setEmail(user.getEmail());
		bookBikeDto.setUserName(user.getName());
		bookBikeDto.setUserId(user.getId());
		bookBikeDto.setBikeId(bike.getId());
	      return bookBikeDto;
	}
	
	
	
	
}
