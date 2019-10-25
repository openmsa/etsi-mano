package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;

@Service
public class RepositoryServiceRest implements RepositoryService {

	private final UbiRest rest;

	public RepositoryServiceRest(final UbiRest rest) {
		super();
		this.rest = rest;
	}

	@Override
	public RepositoryElement getElement(final String path) {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/element")
				.queryParam("URI", path)
				.build()
				.toUri();
		return rest.get(uri, RepositoryElementModel.class);
	}

	static class RepositoryElementModel implements RepositoryElement {
		public RepositoryElementType type;
		public String name;
		public String displayName;
		public Boolean file;
		public String uri;
		public String repositoryName;
		public String parentURI;
		public String fileType;

		@Override
		public String getName() {
			return name;
		}

		@Override
		public RepositoryElementType getType() {
			return type;
		}
	}

	@Override
	public byte[] getRepositoryElementContent(final RepositoryElement repositoryElement) {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/repository-content")
				.queryParam("fileURI", ((RepositoryElementModel) repositoryElement).uri)
				.build()
				.toUri();
		return Base64.getDecoder().decode(
				rest.get(uri, String.class)
						.replaceAll("\"", ""));
	}

	@Override
	public boolean exists(final String path) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/exists")
				.queryParam("uri", path)
				.build()
				.toUri();
		return rest.get(uri, UbiBoolModel.class).exists;
	}

	static class UbiBoolModel {
		public boolean exists;
	}

	@Override
	public void deleteRepositoryElement(final RepositoryElement repositoryElement, final String user) {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/repository")
				.queryParam("elementURI", ((RepositoryElementModel) repositoryElement).uri)
				.build()
				.toUri();
		rest.delete(uri, String.class);
	}

	@Override
	public void addFile(final String path, final String arg2, final String arg3, final String arg4, final String user) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/file")
				.queryParam("uri", path)
				.queryParam("comment", arg2)
				.queryParam("tag", arg3)
				.queryParam("binary", "true")
				.queryParam("content", arg4)
				.build()
				.toUri();
		rest.post(uri, String.class);
	}

	@Override
	public void addFile(final String uri, final String arg2, final String arg3, final byte[] arg4, final String user) throws ServiceException {
		addFile(uri, arg2, arg3, new String(arg4), user);
	}

	@Override
	public void addDirectory(final String path, final String arg1, final String arg2, final String user) {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/directory")
				.queryParam("uri", path)
				.queryParam("comment", arg1)
				.queryParam("tag", arg2)
				.build()
				.toUri();
		rest.post(uri, String.class);
	}

	@Override
	public List<String> doSearch(final String path, final String pattern) throws ServiceException {
		final URI uri = rest.uriBuilder()
				.pathSegment("repository/v1/search")
				.queryParam("URI", path)
				.queryParam("pattern", pattern)
				.build()
				.toUri();
		return rest.get(uri, List.class);
	}
}
