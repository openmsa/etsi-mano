/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.Base64;
import java.util.Collections;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractRest implements NfvoRest {
	private RestTemplate restTemplate;

	protected AbstractRest() {
		restTemplate = new RestTemplate();
	}

	protected abstract String getUrl();

	protected void setRestTemplate(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

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
		return call(uri, method, request, clazz);
	}

	@Override
	public final <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz) {
		final HttpEntity<Object> request = new HttpEntity<>(body, getHttpHeaders());
		return call(uri, method, request, clazz);
	}

	@Override
	public final <T> T get(final URI uri, final ParameterizedTypeReference<T> params) {
		final HttpEntity<Object> request = new HttpEntity<>(getHttpHeaders());
		final ResponseEntity<T> resp = restTemplate.exchange(uri, HttpMethod.GET, request, params);
		return resp.getBody();
	}

	@Override
	public <T> T get(final URI uri, final ResponseExtractor<T> responseExtractor) {
		return restTemplate.execute(uri, HttpMethod.GET, request -> request.getHeaders().add("Content-Type", "application/json"), responseExtractor);
	}

	@Override
	public final UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(getUrl());
	}

	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	private final <T, B> T call(final URI uri, final HttpMethod method, final HttpEntity<B> request, final Class<T> clazz) {
		final ResponseEntity<T> resp = restTemplate.exchange(uri, method, request, clazz);
		return resp.getBody();
	}

	private final HttpHeaders getHttpHeaders() {
		final MultiValueMap<String, String> auth = getAutorization();
		final HttpHeaders httpHeaders = new HttpHeaders(auth);
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.add("Version", "2.6.1");
		return httpHeaders;
	}

	protected static final String authBasic(final String user, final String password) {
		final String toEncode = user + ':' + password;
		return "Basic " + Base64.getEncoder().encodeToString(toEncode.getBytes());
	}
}
