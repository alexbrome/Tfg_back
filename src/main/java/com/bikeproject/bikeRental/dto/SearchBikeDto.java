package com.bikeproject.bikeRental.dto;

import lombok.Data;


public class SearchBikeDto {

	private String brand;
	private String type;
	private String transmision;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransmision() {
		return transmision;
	}
	public void setTransmision(String transmision) {
		this.transmision = transmision;
	}

	
	
}
