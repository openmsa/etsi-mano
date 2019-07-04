package com.ubiqube.etsi.mano.service;

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
	private final static String URL = "http://localhost:8380/ubi-api-rest";

	private RepositoryService repositoryService;
	private final RestTemplate restTemplate;
	private final HttpEntity request;

	public RepositoryServiceRest() {
		restTemplate = new RestTemplate();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic bmNyb290OnViaXF1YmU=");
		request = new HttpEntity<>(httpHeaders);
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
	public List<String> doSearch(String path, String argl1) throws ServiceException {
		final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(URL)
				.pathSegment("repository/v1/search")
				.queryParam("URI", path)
				.queryParam("pattern", argl1);
		final ResponseEntity<List> resp = restTemplate.exchange(uriBuilder.build().toUri(), HttpMethod.GET, request, List.class);
		return resp.getBody();
	}

}
