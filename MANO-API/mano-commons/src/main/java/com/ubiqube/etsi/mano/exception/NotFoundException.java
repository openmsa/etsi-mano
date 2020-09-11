package com.ubiqube.etsi.mano.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

	/**
	 * Default Serial.
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String _detail) {
		super(HttpStatus.NOT_FOUND, _detail);
	}

	public NotFoundException(String _detail, Throwable e) {
		super(HttpStatus.NOT_FOUND, _detail, e);
	}
}
