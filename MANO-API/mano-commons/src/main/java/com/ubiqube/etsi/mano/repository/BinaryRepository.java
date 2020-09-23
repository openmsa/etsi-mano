package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public interface BinaryRepository {

	void storeObject(@NotNull UUID _id, @NotNull String _filename, Object _object);

	void storeBinary(@NotNull UUID _id, @NotNull String _filename, InputStream _stream);

	byte[] getBinary(@NotNull UUID _id, @NotNull String _filename);

	byte[] getBinary(@NotNull UUID _id, @NotNull String _filename, int min, Long max);

	void delete(@NotNull UUID _id, @NotNull String _filename);

	void delete(@NotNull UUID _id);
}
