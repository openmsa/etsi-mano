package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

public interface NfvoRest {

	<T> T get(URI uri, Class<T> clazz);

	<T> T post(URI uri, Class<T> clazz);

	<T> T post(URI uri, Object body, Class<T> clazz);

	<T> T delete(URI uri, Class<T> clazz);

	<T> T call(URI uri, HttpMethod method, Class<T> clazz);

	<T> T call(URI uri, HttpMethod method, Object body, Class<T> clazz);

	UriComponentsBuilder uriBuilder();

}
