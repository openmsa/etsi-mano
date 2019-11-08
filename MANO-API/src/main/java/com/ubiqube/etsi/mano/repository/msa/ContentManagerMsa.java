package com.ubiqube.etsi.mano.repository.msa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.Low;

@Service
public class ContentManagerMsa implements ContentManager {
	private final Low lowDriver;

	public ContentManagerMsa(final Low lowDriver) {
		super();
		this.lowDriver = lowDriver;
	}

	@Override
	public void store(final Path _filename, final InputStream _stream) {
		lowDriver.add(_filename.toString(), _stream);
	}

	@Override
	public InputStream load(final Path _filename, final int start, final Long end) {
		final byte[] bytes = lowDriver.get(_filename.toString(), start, end);
		return new ByteArrayInputStream(bytes);
	}

}
