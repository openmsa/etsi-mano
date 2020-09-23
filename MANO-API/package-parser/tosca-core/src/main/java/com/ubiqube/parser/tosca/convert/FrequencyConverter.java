package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.scalar.Frequency;

public class FrequencyConverter implements Converter {

	@Override
	public Object convert(final Object value) {
		return new Frequency((String) value);
	}

}
