package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface Low {

	boolean exist(String _path);

	void mkdir(String _path);

	void add(String _path, byte[] _content);

	void add(String _path, InputStream _stream);

	byte[] get(String _path);

	void delete(String _path);

	List<String> find(String _path, String _pattern);

	boolean isDirectory(String _path);

	byte[] get(Path path, int min, Integer max);

}