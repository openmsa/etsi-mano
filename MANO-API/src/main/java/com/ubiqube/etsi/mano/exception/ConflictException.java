package com.ubiqube.etsi.mano.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ConflictException extends ResponseStatusException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public ConflictException(String _detail) {
		super(HttpStatus.CONFLICT, _detail);
	}
}
