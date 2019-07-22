package com.ubiqube.etsi.mano.utils;

/**
 * This class allows to parsing the range header parameters. TODO this is a
 * converter we have to make it work in spring.
 */
public class RangeHeaderConverter {

	public RangeHeader fromString(String value) {
		return RangeHeader.fromValue(value);
	}

	public String toString(RangeHeader value) {
		return value.toString();
	}

}
