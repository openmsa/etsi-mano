package com.ubiqube.etsi.mano.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String _detail) {
		super(HttpStatus.BAD_REQUEST, _detail);
	}

	public BadRequestException(String _detail, Throwable e) {
		super(HttpStatus.BAD_REQUEST, _detail, e);
	}
}
