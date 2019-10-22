package com.ubiqube.etsi.mano.repository.msa;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.repository.ContentManager;

@Service
public class ContentManagerMsa implements ContentManager {

	@Override
	public void store(final String _id, final String _filename, final InputStream _stream) {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream load(final String _id, final String _filename, final long start, final Long end) {
		// TODO Auto-generated method stub
		return null;
	}

}
