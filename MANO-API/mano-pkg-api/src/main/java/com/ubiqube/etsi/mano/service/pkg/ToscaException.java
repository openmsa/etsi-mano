package com.ubiqube.etsi.mano.service.pkg;

import java.io.IOException;

public class ToscaException extends RuntimeException {

	public ToscaException(final IOException e) {
		super(e);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

}
