package com.walmart.supercenter.service;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class UUIDConverter implements Converter<String, UUID> {

	@Override
	public UUID convert(String source) {
		final String uuIdS = source.replace('"', ' ').replace('}', ' ').replace(']', ' ').trim();
		final UUID uuId = UUID.fromString(uuIdS);

		return uuId;
	}
}