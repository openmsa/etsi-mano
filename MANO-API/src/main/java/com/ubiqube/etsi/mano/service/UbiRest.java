package com.ubiqube.etsi.mano.service;

import java.net.URI;
import java.util.Base64;

import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@PropertySource("classpath:ubi-mano.properties")
public class UbiRest {

	private final String url;

	private final RestTemplate restTemplate;

	private final HttpHeaders httpHeaders;

	public UbiRest(final Configuration _conf) {
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		url = _conf.build("msa.rest-api.url").notNull().build();
		final String user = _conf.get("msa.rest-api.user");
		if (null != user) {
			final String password = _conf.build("msa.rest-api.password").withDefault("").build();
			final String toEncode = user + ':' + password;
			httpHeaders.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(toEncode.getBytes()));
		}
	}

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
		final HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		return _call(uri, method, request, clazz);
	}

	public <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
		return _call(uri, method, request, clazz);
	}

	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(url);
	}

	private <T, Tbody> T _call(final URI uri, final HttpMethod method, final HttpEntity<Tbody> request, final Class<T> clazz) {
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

}
