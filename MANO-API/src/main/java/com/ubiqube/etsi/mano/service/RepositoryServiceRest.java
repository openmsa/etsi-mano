package com.ubiqube.etsi.mano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;

@Service
public class RepositoryServiceRest implements RepositoryService {
	private RepositoryService repositoryService;

	public RepositoryServiceRest() {
	}

	@Override
	public RepositoryElement getElement(String uri) {
		return repositoryService.getElement(uri);
	}
	@Override
	public byte[] getRepositoryElementContent(RepositoryElement repositoryElement) {
		return repositoryService.getRepositoryElementContent(repositoryElement);
	}
	@Override
	public boolean exists(String uri) throws ServiceException {
		return repositoryService.exists(uri);
	}
	@Override
	public void deleteRepositoryElement(RepositoryElement repositoryElement, String user) {
		repositoryService.deleteRepositoryElement(repositoryElement, user);
	}

	@Override
	public void addFile(String uri, String arg2, String arg3, String arg4, String user) {
		repositoryService.addFile(uri, arg2, arg3, arg4, user);
	}
	@Override
	public void addFile(String uri, String arg2, String arg3, byte[] arg4, String user) {
		repositoryService.addFile(uri, arg2, arg3, arg4, user);
	}

	@Override
	public void addDirectory(String path, String arg1, String arg2, String user) {
		repositoryService.addDirectory(path, arg1, arg2, user);
	}
	@Override
	public List<String> doSearch(String path, String arg1) throws ServiceException {
		return repositoryService.doSearch(path, arg1);
	}

}
