package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.service.Configuration;

public class UbiRest {

	private static final Logger LOG = LoggerFactory.getLogger(UbiRest.class);

	private final String url;
	private final RestTemplate restTemplate;

	private final boolean isv2;

	public UbiRest(final Configuration _conf) {
		restTemplate = new RestTemplate();

		url = _conf.get("msa.rest-api.url");
		final String user = _conf.get("msa.rest-api.user");
		String password = null;
		if (null != user) {
			password = _conf.build("msa.rest-api.password").withDefault("").build();
		}
		isv2 = "2.0".equals(_conf.get("msa.rest-api.version"));
		if (isv2) {
			restTemplate.getInterceptors().add(new Msa2ClientHttpRequestInterceptor(url, user, password));
		} else {
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, password));
		}
		LOG.info("MSA REST client against {}", url);
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
		return _call(uri, method, null, clazz);
	}

	public <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body);
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
