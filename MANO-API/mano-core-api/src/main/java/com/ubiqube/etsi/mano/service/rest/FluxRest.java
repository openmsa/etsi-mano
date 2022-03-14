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
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.net.ssl.SSLException;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.endpoint.WebClientReactiveClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.exception.GenericException;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class FluxRest {
	private static final String VERSION = "Version";
	private static final Logger LOG = LoggerFactory.getLogger(FluxRest.class);

	private final WebClient webClient;
	private final String rootUrl;
	private final String id = UUID.randomUUID().toString();

	public FluxRest(final Servers server) {
		this.rootUrl = server.getUrl();
		webClient = createWebClient(server);
	}

	private WebClient createWebClient(final Servers server) {
		final Builder wcb = WebClient.builder();
		createAuthPart(wcb, server.getAuthentification());
		if (server.isIgnoreSsl()) {
			wcb.clientConnector(new ReactorClientHttpConnector(getHttpClient()));
		}
		wcb.baseUrl(rootUrl);
		return wcb.build();
	}

	private void createAuthPart(final Builder wcb, final AuthentificationInformations auth) {
		if (auth == null) {
			return;
		}
		Optional.ofNullable(auth.getAuthParamBasic()).ifPresent(x -> wcb.filter(ExchangeFilterFunctions.basicAuthentication(x.getUserName(), x.getPassword())));
		Optional.ofNullable(auth.getAuthParamOath2()).ifPresent(x -> {
			final AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
					getRegistration(x.getTokenEndpoint(), x.getClientId(), x.getClientSecret(), "openid"),
					new InMemoryReactiveOAuth2AuthorizedClientService(getRegistration(x.getTokenEndpoint(), x.getClientId(), x.getClientSecret(), "openid")));
			Optional.ofNullable(x.getO2IgnoreSsl()).filter(Boolean::booleanValue).ifPresent(y -> authorizedClientManager.setAuthorizedClientProvider(getAuthorizedClientProvider()));
			final ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
			oauth2.setDefaultClientRegistrationId(id);
			wcb.filter(oauth2);
		});
	}

	private static ReactiveOAuth2AuthorizedClientProvider getAuthorizedClientProvider() {
		final ClientHttpConnector httpConnector = new ReactorClientHttpConnector(getHttpClient());
		final WebClientReactiveClientCredentialsTokenResponseClient accessTokenResponseClient = new WebClientReactiveClientCredentialsTokenResponseClient();
		accessTokenResponseClient.setWebClient(WebClient.builder().clientConnector(httpConnector).build());
		return ReactiveOAuth2AuthorizedClientProviderBuilder
				.builder()
				.clientCredentials(c -> c.accessTokenResponseClient(accessTokenResponseClient)).build();
	}

	private static HttpClient getHttpClient() {
		SslContext context;
		try {
			context = SslContextBuilder.forClient()
					.trustManager(InsecureTrustManagerFactory.INSTANCE)
					.build();
		} catch (final SSLException e) {
			throw new GenericException(e);
		}
		return HttpClient.create().secure(t -> t.sslContext(context));
	}

	private ReactiveClientRegistrationRepository getRegistration(final String tokenUri, final String clientId, final String clientSecret, final String scope) {
		final ClientRegistration registration = ClientRegistration
				.withRegistrationId(id)
				.tokenUri(tokenUri)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope(scope)
				.build();
		return new InMemoryReactiveClientRegistrationRepository(registration);
	}

	public final <T> ResponseEntity<T> getWithReturn(final URI uri, final Class<T> clazz, final String version) {
		final Mono<ResponseEntity<T>> resp = makeBaseQuery(uri, HttpMethod.GET, null, version)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(clazz);
		return resp.block();
	}

	public final <T> T get(final URI uri, final Class<T> clazz, final String version) {
		return call(uri, HttpMethod.GET, clazz, version);
	}

	public final <T> ResponseEntity<T> postWithReturn(final URI uri, final Object body, final Class<T> clazz, final String version) {
		final Mono<ResponseEntity<T>> resp = makeBaseQuery(uri, HttpMethod.POST, body, version)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(clazz);
		return resp.block();
	}

	public final <T> ResponseEntity<T> deleteWithReturn(final URI uri, final Object body, final String version) {
		final ResponseSpec resp = makeBaseQuery(uri, HttpMethod.DELETE, body, version)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve();
		return (ResponseEntity<T>) resp.toBodilessEntity().block();
	}

	public final <T> T post(final URI uri, final Class<T> clazz, final String version) {
		return call(uri, HttpMethod.POST, clazz, version);
	}

	public final <T> T post(final URI uri, final Object body, final Class<T> clazz, final String version) {
		return call(uri, HttpMethod.POST, body, clazz, version);
	}

	public final <T> T delete(final URI uri, final Class<T> clazz, final String version) {
		return call(uri, HttpMethod.DELETE, clazz, version);
	}

	public final <T> T call(final URI uri, final HttpMethod method, final Class<T> clazz, final String version) {
		return innerCall(uri, method, null, clazz, version);
	}

	public final <T> T call(final URI uri, final HttpMethod method, final Object body, final Class<T> clazz, final String version) {
		return innerCall(uri, method, body, clazz, version);
	}

	public <T> T get(final URI uri, final ParameterizedTypeReference<T> myBean, final String version) {
		final Mono<ResponseEntity<T>> resp = makeBaseQuery(uri, HttpMethod.GET, null, version)
				.retrieve()
				.toEntity(myBean);
		return getBlockingResult(resp, null, version);
	}

	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(rootUrl);
	}

	public void download(final URI uri, final Path path, final String version) {
		final RequestHeadersSpec<?> wc = webClient
				.get()
				.uri(uri)
				.accept(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL);
		if (null != version) {
			wc.header(VERSION, version);
		}
		final Publisher<DataBuffer> dataBufferFlux = wc.retrieve()
				.bodyToFlux(DataBuffer.class);
		DataBufferUtils.write(dataBufferFlux, path, StandardOpenOption.CREATE).block();
	}

	private RequestHeadersSpec<?> makeBaseQuery(final URI uri, final HttpMethod method, final Object requestObject, final String version) {
		final RequestHeadersSpec<?> wc = webClient
				.mutate()
				.build()
				.method(method)
				.uri(uri)
				.contentType(MediaType.APPLICATION_JSON);
		if (null != requestObject) {
			((RequestBodySpec) wc).bodyValue(requestObject);
		}
		if (null != version) {
			wc.header(VERSION, version);
		}
		return wc;
	}

	private final <T> T innerCall(final URI uri, final HttpMethod method, final Object requestObject, final Class<T> clazz, final String version) {
		final Mono<ResponseEntity<T>> resp = makeBaseQuery(uri, method, requestObject, version)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(clazz);
		return getBlockingResult(resp, clazz, version);
	}

	private <T> T getBlockingResult(final Mono<ResponseEntity<T>> resp, final Class<T> clazz, final String version) {
		final ResponseEntity<T> resp2 = resp.block();
		if (null == resp2) {
			return null;
		}
		final Optional<URI> uri = Optional.ofNullable(resp2.getHeaders().getLocation()).filter(x -> !x.toString().isEmpty());
		if (uri.isPresent()) {
			LOG.info("Location: {}", uri);
			return innerCall(uri.get(), HttpMethod.GET, null, clazz, version);
		}
		return resp2.getBody();
	}

	public void upload(final URI uri, final Path path, final String accept, final String version) {
		final RequestHeadersSpec<?> wc = webClient.put()
				.uri(uri)
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData(fromPath(path, accept)));
		if (null != version) {
			wc.header(VERSION, version);
		}
		wc.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	private static MultiValueMap<String, ?> fromPath(final Path path, final String accept) {
		final MultipartBodyBuilder builder = new MultipartBodyBuilder();
		builder.part("file", new FileSystemResource(path), MediaType.valueOf(accept));
		return builder.build();
	}

	public <T> T patch(final URI uri, final Class<T> clazz, final String ifMatch, final Map<String, Object> patch, final String version) {
		final RequestHeadersSpec<?> base = makeBaseQuery(uri, HttpMethod.PATCH, patch, version);
		if (ifMatch != null) {
			base.header(HttpHeaders.IF_MATCH, ifMatch);
		}
		final Mono<ResponseEntity<T>> resp = base
				.retrieve()
				.toEntity(clazz);
		return getBlockingResult(resp, null, version);
	}
}
