package com.ubiqube.etsi.mano.utils;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.Provider;

/**
 * This class allows to parsing the range header parameters.
 */
@Provider
public class RangeHeaderConverter implements ParamConverter<RangeHeader> {

	@Override
	public RangeHeader fromString(String value) {
		return RangeHeader.fromValue(value);
	}

	@Override
	public String toString(RangeHeader value) {
		return value.toString();
	}

}
