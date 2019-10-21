package com.ubiqube.etsi.mano.repository.msa;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.entities.repository.RepositoryElement.RepositoryElementType;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotAcceptableException;
import com.ubiqube.etsi.mano.repository.Low;

public class LowMsa implements Low {
	private final RepositoryService repositoryService;

	public LowMsa(final RepositoryService _repositoryService) {
		repositoryService = _repositoryService;
	}

	@Override
	public boolean exist(final String _path) {
		try {
			return repositoryService.exists(_path);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void mkdir(final String _path) {
		try {
			repositoryService.addDirectory(_path, "", "etsi-mano", "ncroot");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public void add(final String _path, final byte[] _content) {
		try {
			repositoryService.addFile(_path, "etsi-mano", "etsi-mano", _content, "ncroot");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public byte[] get(final String _path) {
		final RepositoryElement repositoryElement = repositoryService.getElement(_path);
		System.out.println("_path=" + _path);
		return repositoryService.getRepositoryElementContent(repositoryElement);
	}

	@Override
	public void delete(final String _path) {
		final RepositoryElement repositoryElement = repositoryService.getElement(_path);
		repositoryService.deleteRepositoryElement(repositoryElement, "ncroot");
	}

	@Override
	public List<String> find(final String _path, final String _pattern) {
		try {
			return repositoryService.doSearch(_path, _pattern);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public boolean isDirectory(final String _path) {
		final RepositoryElement element = repositoryService.getElement(_path);
		return (element.getType() == RepositoryElementType.DIRECTORY) || (element.getType() == RepositoryElementType.FOLDER);
	}

	@Override
	public void add(final String _path, final InputStream _stream) {
		byte[] content;
		try {
			content = IOUtils.toByteArray(_stream);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		add(_path, content);
	}

	@Override
	public byte[] get(final Path _path, final int min, final Integer max) {
		final byte[] repositoryContent = get(_path.toString());
		if (min >= repositoryContent.length) {
			throw new NotAcceptableException("Could not retreive a min > lenght of file.");
		}
		return Arrays.copyOfRange(repositoryContent, min, max == null ? repositoryContent.length - min : max);
	}
}
