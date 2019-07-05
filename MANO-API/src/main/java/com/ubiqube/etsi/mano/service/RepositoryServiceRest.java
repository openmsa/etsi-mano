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

	private RepositoryService repositoryService;
	private final RestTemplate restTemplate;

	private final HttpHeaders httpHeaders;

	public RepositoryServiceRest() {
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic bmNyb290Ok9wZW5NU0E=");
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
	public List<String> doSearch(String path, String pattern) throws ServiceException {
		final URI uri = UriComponentsBuilder.fromHttpUrl(URL)
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
}
