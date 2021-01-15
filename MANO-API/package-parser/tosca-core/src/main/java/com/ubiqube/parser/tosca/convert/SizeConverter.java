package com.ubiqube.parser.tosca.convert;

import com.ubiqube.parser.tosca.scalar.Size;

public class SizeConverter implements Converter<Size> {

	@Override
	public Size convert(final Object value) {

		return new Size((String) value);
	}

}
