package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.scalar.Time;

public class TimeConverter implements Converter {

	@Override
	public Object convert(final Object value) {
		return new Time((String) value);
	}

}
