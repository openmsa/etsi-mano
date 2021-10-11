package com.ubiqube.etsi.mano.service.rest;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Optional;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.OAuth2GrantType;
import com.ubiqube.etsi.mano.exception.GenericException;

public class ManoRest extends AbstractRest {
	private final String url;
	private final MultiValueMap<String, String> auth = new LinkedMultiValueMap<>();

	public ManoRest(final String url, final AuthentificationInformations authParams) {
		super();
		this.url = url;
		if (authParams == null) {
			return;
		}
		if (AuthType.OAUTH2_CLIENT_CREDENTIALS == authParams.getAuthType()) {
			final var oauth = authParams.getAuthParamOath2();
			final var resource = getResourceDetails(oauth);
			final var oauth2 = new OAuth2RestTemplate(resource);
			disableSsl(oauth2);
			setRestTemplate(oauth2);
		}
		if (AuthType.BASIC == authParams.getAuthType()) {
			final var basic = authParams.getAuthParamBasic();
			final var user = basic.getUserName();
			if (null != user) {
				final var password = Optional.of(basic.getPassword()).orElse("");
				auth.add("Authorization", authBasic(user, password));
			}
		}
		Assert.notNull(url, "url connot be null.");
	}

	private static final TrustManager[] UNQUESTIONING_TRUST_MANAGER = {
			new X509TrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}

				@Override
				public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
					//
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
					//
				}
			}
	};

	private static void disableSsl(final OAuth2RestTemplate oauth2) {
		try {
			final var sc = SSLContext.getInstance("SSL");
			sc.init(null, UNQUESTIONING_TRUST_MANAGER, null);
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
	}

	private OAuth2ProtectedResourceDetails getResourceDetails(final AuthParamOauth2 oauth) {
		if (OAuth2GrantType.PASSWORD == oauth.getGrantType()) {
			final var resource = new ResourceOwnerPasswordResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getTokenEndpoint());
			resource.setUsername(oauth.getO2Username());
			resource.setPassword(oauth.getO2Password());
			return resource;
		}
		final var resource = new ClientCredentialsResourceDetails();
		resource.setClientId(oauth.getClientId());
		resource.setClientSecret(oauth.getClientSecret());
		resource.setAccessTokenUri(oauth.getTokenEndpoint());
		resource.setClientAuthenticationScheme(AuthenticationScheme.form);
		return resource;
	}

	@Override
	public MultiValueMap<String, String> getAutorization() {
		return auth;
	}

	@Override
	protected String getUrl() {
		return url;
	}

}
