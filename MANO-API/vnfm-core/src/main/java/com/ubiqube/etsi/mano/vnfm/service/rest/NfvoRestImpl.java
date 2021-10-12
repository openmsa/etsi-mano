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
package com.ubiqube.etsi.mano.vnfm.service.rest;
import java.util.Optional;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties.Basic;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties.Oauth2;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.rest.AbstractRest;

/**
 * HTTP way to exchange with NFVO.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoRestImpl extends AbstractRest {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoRestImpl.class);

	private final String url;
	private final MultiValueMap<String, String> auth = new LinkedMultiValueMap<>();

	public NfvoRestImpl(final NfvoConnectionProperties props) {
		url = props.getUrl();
		if (null != props.getOauth2()) {
			final Oauth2 oauth = props.getOauth2();
			final OAuth2ProtectedResourceDetails resource = getResourceDetails(oauth);
			final var oauth2 = new OAuth2RestTemplate(resource);
			disableSsl(oauth2);
			setRestTemplate(oauth2);
		}
		if (props.getBasic() != null) {
			final Basic basic = props.getBasic();
			final String user = basic.getUsername();
			if (null != user) {
				final String password = Optional.of(basic.getPassword()).orElse("");
				auth.add("Authorization", authBasic(user, password));
			}
		}
		Assert.notNull(url, "nfvo.url is not declared in property file.");
	}

	private void disableSsl(final OAuth2RestTemplate oauth2) {
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
		oauth2.setRequestFactory(requestFactory);
	}

	private static OAuth2ProtectedResourceDetails getResourceDetails(final Oauth2 oauth) {
		if ("passsword".equals(oauth.getGrantType())) {
			final var resource = new ResourceOwnerPasswordResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getOauthUrl());
			resource.setUsername(oauth.getUsername());
			resource.setPassword(oauth.getPassword());
			return resource;
		}
		if ("client_credentials".equals(oauth.getGrantType())) {
			final var resource = new ClientCredentialsResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getOauthUrl());
			resource.setClientAuthenticationScheme(AuthenticationScheme.form);
			return resource;
		}
		throw new GenericException("Unable to find correct grant type: " + oauth.getGrantType());
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	public MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
