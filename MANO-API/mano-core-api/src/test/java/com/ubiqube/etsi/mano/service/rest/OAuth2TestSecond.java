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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.endpoint.WebClientReactiveClientCredentialsTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.UnAuthenticatedServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;

import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.OAuth2GrantType;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersion;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.model.ApiVersionInformation;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import reactor.netty.http.client.HttpClient;

class OAuth2TestSecond {
	private final DefaultMapperFactory mapperFactory;

	public OAuth2TestSecond() {
		mapperFactory = new DefaultMapperFactory.Builder().compilerStrategy(new EclipseJdtCompilerStrategy()).build();
	}

	static ReactiveClientRegistrationRepository getRegistration(
			@Value("${spring.security.oauth2.client.provider.my-platform.token-uri}") final String tokenUri,
			@Value("${spring.security.oauth2.client.registration.my-platform.client-id}") final String clientId,
			@Value("${spring.security.oauth2.client.registration.my-platform.client-secret}") final String clientSecret,
			@Value("${spring.security.oauth2.client.registration.my-platform.scopes}") final String scope) {
		final ClientRegistration registration = ClientRegistration
				.withRegistrationId("my-platform")
				.tokenUri(tokenUri)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope(scope)
				.build();
		return new InMemoryReactiveClientRegistrationRepository(registration);
	}

	static InMemoryClientRegistrationRepository getRegistration2(
			@Value("${spring.security.oauth2.client.provider.my-platform.token-uri}") final String tokenUri,
			@Value("${spring.security.oauth2.client.registration.my-platform.client-id}") final String clientId,
			@Value("${spring.security.oauth2.client.registration.my-platform.client-secret}") final String clientSecret,
			@Value("${spring.security.oauth2.client.registration.my-platform.scopes}") final String scope) {
		final ClientRegistration registration = ClientRegistration
				.withRegistrationId("my-platform")
				.tokenUri(tokenUri)
				.clientId(clientId)
				.clientSecret(clientSecret)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope(scope)
				.build();
		return new InMemoryClientRegistrationRepository(registration);
	}

	@Test
	void testNameWebClient() throws Exception {
		final WebClient webClient = webClient();
		final String res = webClient.get().uri("http://10.31.1.245/ubi-etsi-mano/sol003/vnflcm/v1/vnf_instances")
				.header("Content-Type", "application/json")
				.retrieve()
				.bodyToMono(String.class)
				.block();
		System.out.println(">> " + res);
		webClient.post().uri("").header("Content-type", "application/json");
		assertTrue(true);
	}

	private static WebClient webClient() throws SSLException {
		final WebClientReactiveClientCredentialsTokenResponseClient accessTokenResponseClient = new WebClientReactiveClientCredentialsTokenResponseClient();
		final SslContext context = SslContextBuilder.forClient()
				.trustManager(InsecureTrustManagerFactory.INSTANCE)
				.build();

		final HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));
		final ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);
		accessTokenResponseClient.setWebClient(WebClient.builder().clientConnector(httpConnector).build());
		final ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider = ReactiveOAuth2AuthorizedClientProviderBuilder
				.builder()
				.password()
				.refreshToken()
				.authorizationCode()
				.clientCredentials(c -> {
					c.accessTokenResponseClient(accessTokenResponseClient);
				}).build();

		final DefaultReactiveOAuth2AuthorizedClientManager authorizedClientManager = new DefaultReactiveOAuth2AuthorizedClientManager(
				getRegistration("http://10.31.1.245/auth/realms/mano-realm/protocol/openid-connect/token", "mano-nfvo", "ed9aeb6d-3ea5-4392-bb22-835603cf3dfc", "openid"),
				new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
		final AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager2 = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
				getRegistration("http://10.31.1.245/auth/realms/mano-realm/protocol/openid-connect/token", "mano-nfvo", "ed9aeb6d-3ea5-4392-bb22-835603cf3dfc", "openid"),
				new InMemoryReactiveOAuth2AuthorizedClientService(getRegistration("http://10.31.1.245/auth/realms/mano-realm/protocol/openid-connect/token", "mano-nfvo", "ed9aeb6d-3ea5-4392-bb22-835603cf3dfc", "openid")));
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		authorizedClientManager2.setAuthorizedClientProvider(authorizedClientProvider);
		final ServerOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		final ServerOAuth2AuthorizedClientExchangeFilterFunction oauth3 = new ServerOAuth2AuthorizedClientExchangeFilterFunction(getRegistration("http://10.31.1.245/auth/realms/mano-realm/protocol/openid-connect/token", "mano-nfvo", "ed9aeb6d-3ea5-4392-bb22-835603cf3dfc", "openid"),
				new UnAuthenticatedServerOAuth2AuthorizedClientRepository());
		oauth2.setDefaultClientRegistrationId("my-platform");
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.filter(oauth2)
				.build();
	}

	static Servers createServer() {
		return Servers.builder()
				.authentification(AuthentificationInformations.builder()
						.authParamOath2(AuthParamOauth2.builder()
								.clientId("mano-nfvo")
								.clientSecret("ed9aeb6d-3ea5-4392-bb22-835603cf3dfc")
								.grantType(OAuth2GrantType.CLIENT_CREDENTIAL)
								.o2IgnoreSsl(true)
								.tokenEndpoint("http://10.31.1.245/auth/realms/mano-realm/protocol/openid-connect/token")
								.build())
						.authType(List.of(AuthType.OAUTH2_CLIENT_CREDENTIALS))
						.build())
				.ignoreSsl(true)
				.url("http://10.31.1.245/ubi-etsi-mano/sol003/")
				.build();
	}

	@Test
	void testName() throws Exception {
		final Servers server = createServer();
		final FluxRest rest = new FluxRest(server);
		final UriComponents uri = rest.uriBuilder().pathSegment("vnflcm/v1/vnf_instances").build();
		final String res = rest.get(uri.toUri(), String.class);
		assertTrue(true);
	}

	@Test
	void testName2() throws Exception {
		final Servers server = createServer();
		final List<ApiVersionType> arr = getEnum("SOL003_");
		for (final ApiVersionType element : arr) {
			getVersion(server, element);
		}
		assertTrue(true);
	}

	static List<ApiVersionType> getEnum(final String prefix) {
		final ApiVersionType[] arr = ApiVersionType.values();
		return Arrays.stream(arr).filter(x -> x.name().startsWith(prefix)).toList();
	}

	@Test
	void testApiVersion() throws Exception {
		final Servers server = createServer();
		final ApiVersion res = getVersion(server, ApiVersionType.SOL003_VNFPM);
		System.out.println("" + res);
		assertTrue(true);
	}

	ApiVersion getVersion(final Servers server, final ApiVersionType type) {
		final Map<String, Object> uriVariables = Map.of("module", type.toString());
		final FluxRest rest = new FluxRest(server);
		final UriComponents uri = rest.uriBuilder().pathSegment("{module}/api_versions")
				.buildAndExpand(uriVariables);
		final ApiVersionInformation res = rest.get(uri.toUri(), ApiVersionInformation.class);
		return mapperFactory.getMapperFacade().map(res, ApiVersion.class);
	}
}
