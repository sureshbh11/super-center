package com.walmart.supercenter.model;

/**
 * Models are the components that form the 'M' part of the MVC. This class
 * represents the Customer object
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

public class Customer {

	private final ZipCode zipCode;

	public Customer(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	public ZipCode getZipCode() {
		return zipCode;
	}
}
