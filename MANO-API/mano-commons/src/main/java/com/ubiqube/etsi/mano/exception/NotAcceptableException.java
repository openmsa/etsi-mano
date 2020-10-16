package com.ubiqube.etsi.mano.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotAcceptableException extends ResponseStatusException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public NotAcceptableException(String _detail) {
		super(HttpStatus.NOT_ACCEPTABLE, _detail);
	}
}
