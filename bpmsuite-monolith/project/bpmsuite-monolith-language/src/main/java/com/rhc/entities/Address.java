package com.rhc.entities;

import java.io.Serializable;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8926877570621128355L;
	/**
	 * 
	 */
	private String number;
	private String streetName;
	private String city;
	private String zip;
	private Country country;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Address [number=" + number + ", streetName=" + streetName
				+ ", city=" + city + ", zip=" + zip + ", country=" + country
				+ "]";
	}

	
}
