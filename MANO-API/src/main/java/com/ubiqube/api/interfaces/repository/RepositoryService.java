package com.ubiqube.api.interfaces.repository;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;

import java.util.List;


public interface RepositoryService {
	public RepositoryElement getElement(String uri);
	public byte[] getRepositoryElementContent(RepositoryElement repositoryElement);
	public boolean exists(String uri) throws ServiceException;
	public void deleteRepositoryElement(RepositoryElement repositoryElement, String user);

	public void addFile(String uri, String arg2, String arg3, String arg4, String user) throws ServiceException;
	public void addFile(String uri, String arg2, String arg3, byte[] arg4, String user) throws ServiceException;

	public void addDirectory(String path, String arg1, String arg2, String user);
	public List<String> doSearch(String path, String arg1) throws ServiceException;
}
