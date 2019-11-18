package com.ubiqube.etsi.mano.repository.phys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.FileSystemUtils;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.Low;

public class LowPhys implements Low {

	@Override
	public boolean exist(final String _path) {
		return new File(_path).exists();
	}

	@Override
	public void mkdir(final String _path) {
		new File(_path).mkdirs();
	}

	@Override
	public void add(final String _path, final byte[] _content) {
		try {
			Files.write(Paths.get(_path), _content);
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public void add(final String _path, final InputStream _stream) {
		try {
			Files.copy(_stream, Paths.get(_path));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public byte[] get(final String _path) {
		try {
			return Files.readAllBytes(Paths.get(_path));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void delete(final String _path) {
		boolean result;
		try {
			result = FileSystemUtils.deleteRecursively(Paths.get(_path));
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		if (!result) {
			throw new GenericException("Unable to delete " + _path);
		}
	}

	@Override
	public List<String> find(final String _path, final String _pattern) {
		final Path path = Paths.get(_path);
		try (final Stream<Path> walk = Files.walk(path)) {
			return walk.filter(x -> x.toString().endsWith(_pattern))
					.map(x -> x.toString())
					.collect(Collectors.toList());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public boolean isDirectory(final String _path) {
		return new File(_path).isDirectory();
	}

	@Override
	public byte[] get(final String path, final int min, final Long max) {
		try (InputStream fis = new FileInputStream(path)) {
			final int delta = max.intValue() - min;
			final byte[] res = new byte[delta];
			fis.read(res, min, delta);
			return res;
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

}
