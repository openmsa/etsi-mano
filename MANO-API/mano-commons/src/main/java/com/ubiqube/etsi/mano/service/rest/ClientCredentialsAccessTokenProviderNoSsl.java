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

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.ubiqube.etsi.mano.exception.GenericException;

public class ClientCredentialsAccessTokenProviderNoSsl extends ClientCredentialsAccessTokenProvider {

	private Object messageConverters;
	private RestOperations restTemplate;
	private List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

	/**
	 * Sets the request interceptors that this accessor should use.
	 */
	@Override
	public void setInterceptors(final List<ClientHttpRequestInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	@Override
	protected synchronized RestOperations getRestTemplate() {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(getResponseErrorHandler());
		restTemplate.setRequestFactory(getNoSslRequestFactory());
		restTemplate.setInterceptors(interceptors);
		this.restTemplate = restTemplate;
		if (messageConverters == null) {
			setMessageConverters(new RestTemplate().getMessageConverters());
		}
		return restTemplate;
	}

	private ClientHttpRequestFactory getNoSslRequestFactory() {
		final TrustStrategy acceptingTrustStrategy = (final X509Certificate[] chain, final String authType) -> true;

		SSLContext sslContext;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new GenericException(e);
		}

		final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

		final CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLSocketFactory(csf)
				.build();
		final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return requestFactory;
	}

}
