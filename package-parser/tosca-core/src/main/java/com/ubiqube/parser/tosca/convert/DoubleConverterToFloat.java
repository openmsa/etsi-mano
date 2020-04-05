package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.ParseException;

public class DoubleConverterToFloat implements Converter {

	@Override
	public Object convert(final Object value) {
		if (value instanceof Float) {
			final Float dbl = (Float) value;
			return dbl.doubleValue();
		}
		throw new ParseException("Can't convert " + value.getClass());
	}

}
