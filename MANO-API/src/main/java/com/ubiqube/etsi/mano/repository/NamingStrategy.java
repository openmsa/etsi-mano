package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;
import java.util.UUID;

public interface NamingStrategy {

	Path getRoot(Class<?> clazz);

	Path getRoot(Class<?> clazz, UUID _id);

	Path getRoot(Class<?> clazz, UUID _id, String _filename);

	Path getRoot();

}
