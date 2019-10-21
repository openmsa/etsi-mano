package com.ubiqube.etsi.mano.repository;

import java.util.List;

public interface Low {

	boolean exist(String _path);

	void mkdir(String _path);

	void add(String _path, byte[] _content);

	byte[] get(String _path);

	void delete(String _path);

	List<String> find(String _path, String _pattern);

	boolean isDirectory(String _path);

}