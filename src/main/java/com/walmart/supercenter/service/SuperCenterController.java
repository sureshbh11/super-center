package com.walmart.supercenter.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.supercenter.model.Customer;
import com.walmart.supercenter.model.Item;
import com.walmart.supercenter.model.SuperCenter;
import com.walmart.supercenter.model.ZipCode;

/**
 * Controllers are the components that form the 'C' part of the MVC. This class
 * represents the controller for servicing SuperCenter service URLs
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

@RestController
public class SuperCenterController {

	@Autowired
	private SuperCenterService service;

	/*
	 * API_1: Returns 0 or more SuperCenters which are closest to the passed
	 * customerZipCode in the increasing order of distance from passed
	 * customerZipCode.
	 */
	@RequestMapping(value="/getAllSuperCenterThatCanDeliverFrom")
	List<SuperCenter> getAllSuperCenterThatCanDeliverFrom(@RequestParam(name="zipCode") ZipCode customerZipCode) {
		return service.getAllSuperCenterThatCanDeliverFrom(customerZipCode);
	}

	/*
	 * API_2: It returns Item if available in SuperCenter, given itemId. 
	 *
	 */
	@RequestMapping("/getItem")
	Item getItem(@RequestParam(name="superCenter") SuperCenter superCenter, @RequestParam(name="itemId") UUID itemId) {
		return service.getItem(superCenter, itemId);
	}
	
	/*
	 *	API_3: Returns Super center that has the item customer is requesting and is closest to the customer’s zip code. 
	 *
	 */
	@RequestMapping("/getFullfimentSuperCenter")
	SuperCenter getFullfimentSuperCenter(Customer customer, Item item) {
		return service.getFullfimentSuperCenter(customer, item);
	}
	
}
