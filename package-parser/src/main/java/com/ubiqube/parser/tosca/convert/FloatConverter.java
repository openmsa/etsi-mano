package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.ParseException;

public class FloatConverter implements Converter {

	@Override
	public Object convert(final Object value) {
		if (value instanceof Double) {
			final Double dbl = (Double) value;
			return dbl.floatValue();
		}
		throw new ParseException("Can't convert " + value.getClass());
	}

}
