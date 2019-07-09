package com.ubiqube.etsi.mano.repository;

import javax.inject.Inject;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;

/**
 * Handle MSA repository services for T objects.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <T> A storable object.
 */
public abstract class AbstractRepository<T> {

	protected RepositoryService repositoryService;

	@Inject
	public AbstractRepository(RepositoryService _repositoryService) {
		repositoryService = _repositoryService;
	}

	abstract T get(String id);

	abstract void delete(String id);

	abstract T save(T entity);

	protected void verify(String _uri) {
		try {
			if (!repositoryService.exists(_uri)) {
				throw new NotFoundException("Object not found ");
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}
}
