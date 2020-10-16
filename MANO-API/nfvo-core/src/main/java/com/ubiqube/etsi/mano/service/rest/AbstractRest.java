package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.Base64;
import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractRest implements NfvoRest {
	private final RestTemplate restTemplate;

	public AbstractRest() {
		restTemplate = new RestTemplate();
	}

	protected abstract String getUrl();

	protected abstract MultiValueMap<String, String> getAutorization();

	@Override
	public final <T> T get(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.GET, clazz);
	}

	@Override
	public final <T> T post(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.POST, clazz);
	}

	@Override
	public final <T> T post(final URI uri, final Object body, final Class<T> clazz) {
		return call(uri, HttpMethod.POST, body, clazz);
	}

	@Override
	public final <T> T delete(final URI uri, final Class<T> clazz) {
		return call(uri, HttpMethod.DELETE, clazz);
	}

	@Override
	public final <T> T call(final URI uri, final HttpMethod method, final Class<T> clazz) {
		final HttpEntity<String> request = new HttpEntity<>(getHttpHeaders());
		return _call(uri, method, request, clazz);
	}

	@Override
	public final <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, getHttpHeaders());
		return _call(uri, method, request, clazz);
	}

	@Override
	public final UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(getUrl());
	}

	private final <T, Tbody> T _call(final URI uri, final HttpMethod method, final HttpEntity<Tbody> request, final Class<T> clazz) {
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

	private final HttpHeaders getHttpHeaders() {
		final HttpHeaders httpHeaders = new HttpHeaders();
		final MultiValueMap<String, String> auth = getAutorization();
		httpHeaders.addAll(auth);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}

	protected final static String authBasic(final String user, final String password) {
		final String toEncode = user + ':' + password;
		return "Basic " + Base64.getEncoder().encodeToString(toEncode.getBytes());
	}
}
