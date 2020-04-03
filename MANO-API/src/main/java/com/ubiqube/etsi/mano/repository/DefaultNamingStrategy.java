package com.ubiqube.etsi.mano.repository;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.Configuration;

@Service
public class DefaultNamingStrategy implements NamingStrategy {
	private final ClassPathConverter cpConverter = new ClassPathConverter();
	private final String root;

	public DefaultNamingStrategy(final Configuration configuration) {
		root = configuration.build("repository.phys.root").notNull().build();
	}

	protected static final String sanitize(final String filename) {
		// It's ok for path segment not for a full path.
		return filename.replaceAll("\\.+", ".");
	}

	@Override
	public Path getRoot(final Class<?> clazz) {
		return Paths.get(root, cpConverter.convert(clazz));
	}

	@Override
	public Path getRoot(final Class<?> clazz, final String _id) {
		return Paths.get(root, cpConverter.convert(clazz), sanitize(_id));
	}

	@Override
	public Path getRoot(final Class<?> clazz, final String _id, final String _filename) {
		return Paths.get(root, cpConverter.convert(clazz), sanitize(_id), sanitize(_filename));
	}

	@Override
	public Path getRoot() {
		return Paths.get(root);
	}

}
