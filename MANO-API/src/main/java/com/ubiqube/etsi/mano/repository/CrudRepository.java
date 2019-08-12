package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Nonnull;

public interface CrudRepository<T> {

	@Nonnull
	T get(@Nonnull String id);

	void delete(@Nonnull String id);

	@Nonnull
	T save(@Nonnull T entity);

	List<T> query(String filter);

	void storeBinary(String _vnfPkgId, InputStream _stream, String _filename);

	byte[] getBinary(String _vnfPkgId, String _filename);

	byte[] getBinary(String _vnfPkgId, String _filename, int min, Integer max);
}
