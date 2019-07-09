package com.ubiqube.etsi.mano.service;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;

@Service
public class RepositoryServiceRest implements RepositoryService {
	private final static String URL = "http://localhost:8080/ubi-api-rest";

	private final RestTemplate restTemplate;

	private final HttpHeaders httpHeaders;

	public RepositoryServiceRest() {
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic bmNyb290Ok9wZW5NU0E=");
	}

	@Override
	public RepositoryElement getElement(String path) {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/element")
				.queryParam("URI", path)
				.build()
				.toUri();
		return get(uri, HttpMethod.GET, RepositoryElementModel.class);
	}

	static class RepositoryElementModel implements RepositoryElement {
		public String type;
		public String name;
		public String displayName;
		public Boolean file;
		public String uri;
		public String repositoryName;
		public String parentURI;
		public String fileType;

		public String getName() {
			return name;
		}
	}

	@Override
	public byte[] getRepositoryElementContent(RepositoryElement repositoryElement) {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/repository-content")
				.queryParam("fileURI", ((RepositoryElementModel)repositoryElement).uri)
				.build()
				.toUri();
		return get(uri, HttpMethod.GET, String.class)
			.getBytes(); // TODO: check if returned string needs decoding
	}

	@Override
	public boolean exists(String path) throws ServiceException {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/exists")
				.queryParam("uri", path)
				.build()
				.toUri();
		return get(uri, HttpMethod.GET, UbiBoolModel.class)
			.exists;
	}

	static class UbiBoolModel {
		public boolean exists;
	}

	@Override
	public void deleteRepositoryElement(RepositoryElement repositoryElement, String user) {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/repository")
				.queryParam("elementURI", ((RepositoryElementModel)repositoryElement).uri)
				.build()
				.toUri();
		get(uri, HttpMethod.DELETE, String.class);
	}

	@Override
	public void addFile(String path, String arg2, String arg3, String arg4, String user) throws ServiceException {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/file")
				.queryParam("uri", path)
				.queryParam("comment", arg2)
				.queryParam("tag", arg3)
				.queryParam("binary", "true")
				.queryParam("content", arg4)
				.build()
				.toUri();
		get(uri, HttpMethod.POST, String.class);
	}

	@Override
	public void addFile(String uri, String arg2, String arg3, byte[] arg4, String user) throws ServiceException {
		addFile(uri, arg2, arg3, new String(arg4), user);
	}

	@Override
	public void addDirectory(String path, String arg1, String arg2, String user) {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/directory")
				.queryParam("uri", path)
				.queryParam("comment", arg1)
				.queryParam("tag", arg2)
				.build()
				.toUri();
		get(uri, HttpMethod.POST, String.class);
	}

	@Override
	public List<String> doSearch(String path, String pattern) throws ServiceException {
		final URI uri = apiUriBuilder()
				.pathSegment("repository/v1/search")
				.queryParam("URI", path)
				.queryParam("pattern", pattern)
				.build()
				.toUri();
		return get(uri, HttpMethod.GET, List.class);
	}

	private <T> T get(URI uri, HttpMethod method, Class<T> clazz) {
		final HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

	private UriComponentsBuilder apiUriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(URL);
	}
}
