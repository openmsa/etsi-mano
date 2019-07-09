package com.ubiqube.etsi.mano.service;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


public class UbiRest {
	private final static String URL = "http://localhost:8080/ubi-api-rest";

	private final RestTemplate restTemplate;

	private final HttpHeaders httpHeaders;

	public UbiRest() {
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", "Basic bmNyb290Ok9wZW5NU0E=");
	}

	public <T> T call(URI uri, HttpMethod method, Class<T> clazz) {
		final HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		return _call(uri, method, request, clazz);
	}

	public <T> T call(URI uri, HttpMethod method, Object body, Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
		return _call(uri, method, request, clazz);
	}

	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(URL);
	}

	private <T, Tbody> T _call(URI uri, HttpMethod method, HttpEntity<Tbody> request, Class<T> clazz) {
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

}
