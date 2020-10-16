package com.ubiqube.etsi.mano.service.vim;

public class VimException extends RuntimeException {
	public VimException(final Throwable e) {
		super(e);
	}

	public VimException(final String string) {
		super(string);
	}

	/** Serial. */
	private static final long serialVersionUID = 1L;

}
