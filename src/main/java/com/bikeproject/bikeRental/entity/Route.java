package com.bikeproject.bikeRental.entity;

import java.sql.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.bikeproject.bikeRental.Enums.BookBikeStatus;
import com.bikeproject.bikeRental.dto.BookBikeDto;
import com.bikeproject.bikeRental.dto.RouteDto;
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
public class Route {

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

	public RouteDto routeDto() {
		RouteDto routeDto = new RouteDto();
		routeDto.setId(id);
		routeDto.setDays(days);
		routeDto.setBookBikeStatus(bookBikeStatus);
		routeDto.setPrice(price);
		routeDto.setToDate(toDate);
		routeDto.setFromDate(fromDate);
		routeDto.setEmail(user.getEmail());
		routeDto.setUserName(user.getName());
		routeDto.setUserId(user.getId());
		//routeDto.setBikeId(bike.getId());
	      return routeDto;
	}
	
	
}
