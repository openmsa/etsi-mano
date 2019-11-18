package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;

public interface NamingStrategy {

	Path getRoot(Class<?> clazz);

	Path getRoot(Class<?> clazz, String _id);

	Path getRoot(Class<?> clazz, String _id, String _filename);

	Path getRoot();

}
