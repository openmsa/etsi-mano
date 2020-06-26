package com.ubiqube.parser.tosca;

public class ParseException extends RuntimeException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public ParseException(final String message) {
		super(message);
	}

	public ParseException(final Exception e) {
		super(e);
	}

}
