package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;

public interface NamingStrategy {

	Path getNameFor(Class<?> frontClass, String _id, String _filename);

}
