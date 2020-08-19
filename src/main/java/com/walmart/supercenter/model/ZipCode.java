package com.walmart.supercenter.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

/**
 * Models are the components that form the 'M' part of the MVC. This class
 * represents the ZipCode object
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ZipCode implements Comparable<ZipCode> {
	
	private final int code;
	private final double latitude;
	private final double longitude;

	public ZipCode(int code, double latitude, double longitude) {
		this.code = code;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/*
	 * Implement a comparing function, that uses code property for ordering.
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
	public int compareTo(ZipCode that) {
		return (this.code == that.code) ? 0 : Math.abs(this.code - that.code);
	}

	public static ZipCode valueOf(String value) {
		final String[] val1 = value.split(",");
		
		final String[] codeS = val1[0].split(":");
		final int code = Integer.parseInt(codeS[1]);

		final String[] latiS = val1[1].split(":");
		final double lati = Double.parseDouble(latiS[1]);
		
		final String[] longiS = val1[2].split(":");
		final double longi = Double.parseDouble(longiS[1].substring(0, longiS[1].length()-1));

		return new ZipCode(code, lati, longi);
	}
}
