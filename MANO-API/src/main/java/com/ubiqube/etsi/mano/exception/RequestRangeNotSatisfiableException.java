package com.ubiqube.etsi.mano.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestRangeNotSatisfiableException extends ResponseStatusException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public RequestRangeNotSatisfiableException(String _detail) {
		super(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, _detail);
	}
}
