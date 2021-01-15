package com.ubiqube.parser.tosca.scalar;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Version {

	private final String value;

	public Version(final String _value) {
		value = _value;
	}

	@Override
	public String toString() {
		return value;

	}

}
