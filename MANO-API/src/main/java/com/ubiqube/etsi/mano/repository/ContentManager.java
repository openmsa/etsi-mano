package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;

public interface ContentManager {

	void store(String _id, String _filename, InputStream _stream);

	InputStream load(String _id, String _filename, long start, Long end);

}
