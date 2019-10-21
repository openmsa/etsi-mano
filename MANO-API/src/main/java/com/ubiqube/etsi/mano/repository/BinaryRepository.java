package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;

import javax.validation.constraints.NotNull;

public interface BinaryRepository {

	void storeObject(@NotNull String _id, @NotNull String _filename, Object _object);

	<T, U extends Class> T loadObject(@NotNull final String _id, @NotNull final String _filename, final U t);

	void storeBinary(@NotNull String _id, @NotNull String _filename, InputStream _stream);

	byte[] getBinary(@NotNull String _id, @NotNull String _filename);

	byte[] getBinary(@NotNull String _id, @NotNull String _filename, int min, Integer max);
}
