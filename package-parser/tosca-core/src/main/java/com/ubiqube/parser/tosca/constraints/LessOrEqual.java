package com.ubiqube.parser.tosca.constraints;

import com.ubiqube.parser.tosca.ParseException;

public class LessOrEqual extends SimpleValue implements Constraint {

	public LessOrEqual(final String _value) {
		super(_value);
		throw new ParseException("key=" + _value);
	}

}
