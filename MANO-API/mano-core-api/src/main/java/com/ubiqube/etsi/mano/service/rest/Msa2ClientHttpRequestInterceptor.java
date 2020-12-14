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

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class Msa2ClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	private final String password;
	private final String username;
	private final String url;
	private String token;

	public Msa2ClientHttpRequestInterceptor(final String _url, final String _username, final String _password) {
		username = _username;
		password = _password;
		url = _url;
	}

	private String getToken() {
		final RestTemplate restTemplate = new RestTemplate();
		final Authentificate auth = new Authentificate(username, password);
		final URI uri = UriComponentsBuilder.fromHttpUrl(url).pathSegment("auth/token").build().toUri();
		final ResponseEntity<AuthResponse> resp = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(auth), AuthResponse.class);
		final AuthResponse body = resp.getBody();
		return body.getToken();
	}

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
		final HttpHeaders headers = request.getHeaders();
		if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
			applyBearer(headers);
		}
		return execution.execute(request, body);
	}

	private void applyBearer(final HttpHeaders headers) {
		if (null == token) {
			token = getToken();
		}
		headers.setBearerAuth(token);
	}

}
