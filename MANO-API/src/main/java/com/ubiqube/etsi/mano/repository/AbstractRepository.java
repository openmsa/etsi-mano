package com.ubiqube.etsi.mano.repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;

public abstract class AbstractRepository<T> {

	protected RepositoryService repositoryService;

	public AbstractRepository() {
/*
		try {
			final InitialContext jndiContext = new InitialContext();
			repositoryService = (RepositoryService) jndiContext.lookup("ubi-jentreprise/RepositoryManagerBean/remote-com.ubiqube.api.interfaces.repository.RepositoryService");

		} catch (final NamingException e) {
			throw new GenericException(e);
		}
*/
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
