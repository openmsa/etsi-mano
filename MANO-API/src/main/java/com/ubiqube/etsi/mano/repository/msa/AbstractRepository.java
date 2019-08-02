package com.ubiqube.etsi.mano.repository.msa;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.CrudRepository;

/**
 * Handle MSA repository services for T objects.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <T> A storable object.
 */
public abstract class AbstractRepository<T> implements CrudRepository<T> {

	protected RepositoryService repositoryService;

	public AbstractRepository(final RepositoryService _repositoryService) {
		repositoryService = _repositoryService;
	}

	protected void verify(final String _uri) {
		try {
			if (!repositoryService.exists(_uri)) {
				throw new NotFoundException("Object not found ");
			}
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}
}
