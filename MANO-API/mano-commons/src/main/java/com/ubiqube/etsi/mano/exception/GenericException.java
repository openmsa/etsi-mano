package com.ubiqube.etsi.mano.exception;

/**
 * A generic exception.
 * 
 * @author ovi@ubiqube.com
 *
 */
public class GenericException extends RuntimeException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public GenericException(Exception _e) {
		super(_e);
	}

	public GenericException(String _string) {
		super(_string);
	}

}
