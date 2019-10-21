package com.ubiqube.etsi.mano.repository.msa;

import java.util.List;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.entities.repository.RepositoryElement.RepositoryElementType;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
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
}
