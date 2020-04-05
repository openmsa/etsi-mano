package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.scalar.Size;

public class SizeConverter implements Converter {

	@Override
	public Object convert(final Object value) {

		return new Size((String) value);
	}

}
