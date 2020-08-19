package com.walmart.supercenter.model;

import java.util.UUID;

/**
 * Models are the components that form the 'M' part of the MVC. This class
 * represents the Item object
 *
 * Created by Suresh Bhaskaran on 8/18/2020.
 */

public class Item {

	private final UUID itemId;

	public UUID getItemId() {
		return itemId;
	}

	public Item(final UUID itemId) {
		this.itemId = itemId;
	}

	public static Item valueOf(String value) {
		final String[] val = value.split(":");
		
		UUID uuId = UUID.fromString(val[1]);
		return new Item(uuId);
	}
}
