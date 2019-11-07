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

	@Override
	public Path getNameFor(final Class<?> frontClass, final String _id, final String _filename) {
		final Path classPath = cpConverter.convert(frontClass);
		return Paths.get(root, classPath.toString(), sanitize(_id), sanitize(_filename));
	}

	protected final static String sanitize(final String filename) {
		// It's ok for path segment not for a full path.
		return filename.replaceAll("\\.+", ".");
	}

	private final Path getRootId(final Path root, final String _id) {
		return combine(root, _id);
	}

	private static final Path combine(final Path path, final String _filename) {
		return Paths.get(path.toString(), sanitize(_filename));
	}

}
