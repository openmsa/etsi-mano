package com.ubiqube.etsi.mano.service.vim;

public class VimException extends RuntimeException {

	public VimException(final String fault) {
		super(fault);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;
}
