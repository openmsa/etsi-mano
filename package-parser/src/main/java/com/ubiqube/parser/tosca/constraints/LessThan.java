package com.ubiqube.parser.tosca.constraints;

import com.ubiqube.parser.tosca.ParseException;

public class LessThan extends SimpleValue implements Constraint {

	public LessThan(final String key) {
		super(null);
		throw new ParseException("key=" + key);
	}

}
