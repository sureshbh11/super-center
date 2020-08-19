package com.walmart.supercenter.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Models are the components that form the 'M' part of the MVC. This class
 * represents the SuperCenter object
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

public class SuperCenter implements Comparable<SuperCenter> {
	
	private final ZipCode zipCode;
	
	private final Set<Item> stock = new HashSet<>(); //this is updated through CMS interface 

	public SuperCenter(final ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/*
	 * Implement a comparing function, that uses zipCode code property for ordering.
	 * 
	 * Zip z1 = new ZipCode(10, 0.1, 0.2);
	 * 
	 * Zip z2 = new ZipCode(8, 10.1, 10.22);
	 * 
	 * z2 < z1
	 * 
	 * For ZipCode with same `code` property, this method should return 0.
	 * 
     * Sorting by code. compareTo should return 0 if this(code) 
     * is equal to code, otherwise return the absolute difference
     */
	@Override
	public int compareTo(SuperCenter that) {
		return this.zipCode.compareTo(that.zipCode);
	}

	public void addItem(final Item item) {
		this.stock.add(item);
	}

	public Set<Item> getStock() {
		return stock;
	}

	public static SuperCenter valueOf(String value) {
		
		final SuperCenter sc = new SuperCenter(new ZipCode(0,0d,0d));
		final String[] vals = value.split(",");

		for(int i=0; i<vals.length; i++) {
			
			final String[] val = vals[i].split(":");
			
			final int ndx = (i==0) ? 2 : 1;
			final String uuIdS = val[ndx].replace('"', ' ').replace('}', ' ').replace(']', ' ').trim();
			final UUID uuId = UUID.fromString(uuIdS);
			sc.addItem(new Item(uuId));
		}
			
		return sc;
	}
}
