package com.ubiqube.etsi.mano.utils;

/**
 * This class allows to parsing the range header parameters.
 */
public class RangeHeaderConverter {

	public RangeHeader fromString(final String value) {
		return RangeHeader.fromValue(value);
	}

	public String toString(final RangeHeader value) {
		return value.toString();
	}

}
