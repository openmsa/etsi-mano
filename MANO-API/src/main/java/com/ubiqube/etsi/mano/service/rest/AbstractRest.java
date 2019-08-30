package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.Base64;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractRest {
	private final RestTemplate restTemplate;

	public AbstractRest() {
		restTemplate = new RestTemplate();
	}

	protected abstract String getUrl();

	abstract UserPass getAutorization();

	public <T> T get(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.GET, clazz);
	}

	public <T> T post(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.POST, clazz);
	}

	public <T> T post(final URI uri, final Object body, final Class<T> clazz) {
		return call(uri, HttpMethod.POST, body, clazz);
	}

	public <T> T delete(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.DELETE, clazz);
	}

	public <T> T call(final URI uri, final HttpMethod method, final Class<T> clazz) {
		final HttpEntity<String> request = new HttpEntity<>(getHttpHeaders());
		return _call(uri, method, request, clazz);
	}

	public <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, getHttpHeaders());
		return _call(uri, method, request, clazz);
	}

	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(getUrl());
	}

	private <T, Tbody> T _call(final URI uri, final HttpMethod method, final HttpEntity<Tbody> request, final Class<T> clazz) {
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

	private HttpHeaders getHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		final UserPass userPass = getAutorization();
		final String basic = userPass.user + ":" + userPass.pass;
		httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(basic.getBytes()));
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	protected class UserPass {
		public UserPass(final String username, final String password) {
			user = username;
			pass = password;
		}

		String user;
		String pass;
	}
}
