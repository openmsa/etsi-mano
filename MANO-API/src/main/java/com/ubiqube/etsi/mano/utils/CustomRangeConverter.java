package com.ubiqube.etsi.mano.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomRangeConverter implements Converter<String, RangeHeader> {

	@Override
	public RangeHeader convert(String source) {
		return RangeHeader.fromValue(source);
	}

}
