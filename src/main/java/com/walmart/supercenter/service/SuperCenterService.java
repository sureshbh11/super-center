package com.walmart.supercenter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walmart.supercenter.model.Customer;
import com.walmart.supercenter.model.Item;
import com.walmart.supercenter.model.SuperCenter;
import com.walmart.supercenter.model.ZipCode;

/**
 * This class represents the Service Layer component of SuperCenterService project
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

@Component
public class SuperCenterService {

	@Value("${supercenter.names}")
	private String centers;

	private List<SuperCenter> superCenters;

	/**
	 * init
	 */
	public void init() {
		superCenters = new ArrayList<SuperCenter>();
		
		final String[] centerList = centers.split(",");
		for(String center:centerList) {
			final String[] first = center.split("-");
			final String[] vals  = first[1].split(" ");
			final int code = Integer.parseInt(vals[0]); 
			final int lati = Integer.parseInt(vals[1]); 
			final int longi= Integer.parseInt(vals[2]);
			final int item= Integer.parseInt(vals[3]);
			
			final SuperCenter sc = new SuperCenter(new ZipCode(code, lati, longi));
			sc.addItem(new Item(new UUID(item, item)));
			superCenters.add(sc);
		}
	}

	/*
	 * Returns 0 or more SuperCenters which are closest to the passed
	 * customerZipCode in the increasing order of distance from passed
	 * customerZipCode.
	 */
	List<SuperCenter> getAllSuperCenterThatCanDeliverFrom(final ZipCode customerZipCode) {

		if(superCenters == null) {
			 this.init();
		}
		
		final SuperCenter customerLocation = new SuperCenter(customerZipCode);
		final List<SuperCenter> centers = new ArrayList<SuperCenter>(this.superCenters);
		centers.add(customerLocation);
		Collections.sort(centers);

		final int ndx = centers.indexOf(customerLocation);
		final List<SuperCenter> centersCloserToCustomer = centers.subList(ndx+1, centers.size());
		return centersCloserToCustomer;
	}
	
	/*
	 * It returns Item if available in SuperCenter, given itemId. 
	 *
	 */
	Item getItem(final SuperCenter superCenter, final UUID itemId) {
		
		for(final Item item:superCenter.getStock()) {
			if(itemId.equals(item.getItemId())) {
				return item;
			}
		}
		
		return null;
	}
	
	/*
	 *	Returns Super center that has the item customer is requesting and is closest to the customer’s zip code. 
	 *
	 */
	SuperCenter getFullfimentSuperCenter(Customer customer, Item item) {

		final List<SuperCenter> centers = getAllSuperCenterThatCanDeliverFrom(customer.getZipCode());
		
		for(final SuperCenter sc:centers) {
			
			Item available = this.getItem(sc, item.getItemId());
			if(available != null) {
				return sc;
			}
		}
		return null;
	}
	
}