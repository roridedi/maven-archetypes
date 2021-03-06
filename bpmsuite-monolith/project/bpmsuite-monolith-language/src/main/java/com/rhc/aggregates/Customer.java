package com.rhc.aggregates;

import java.io.Serializable;

import com.rhc.entities.Address;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6063045533064468446L;
	/**
	 * 
	 */
	private String firstName;
	private String lastName;
	private Address address;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + "]";
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}


	

}
