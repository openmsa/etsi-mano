package com.ubiqube.etsi.mano.service.event;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOath2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.exception.GenericException;

/**
 * This class handle the notification callback.
 * <li>
 * <ul>
 * Building the HTTP client.
 * </ul>
 * <ul>
 * Crafting the request.
 * </ul>
 * <ul>
 * Sending the request.
 * </ul>
 * <ul>
 * Interpreting the result.
 * </ul>
 * </li> This class should be compatible with Basic, OAuth2, TLS CERT
 * authentification. One the possiblities for OAuth authentification is group:
 * 'net.oauth.core', name: 'oauth-httpclient4', version: '20090913' you may also
 * need this: http://www.codedq.net/blog/articles/146.html
 *
 * @author ovi@ubiqube.com
 *
 */
@Service
public class Notifications {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(Notifications.class);
	/** JSON mapper. */
	private final ObjectMapper mapper;

	public Notifications(final ObjectMapper _mapper) {
		mapper = _mapper;
	}

	/**
	 * Send a notification Object to the _uri
	 *
	 * @param obj  The JSON Onject.
	 * @param _uri The complete URL.
	 * @param auth Auth parameters.
	 */
	public void doNotification(final Object obj, final String _uri, final AuthentificationInformations auth) {
		String content;
		try {
			content = mapper.writeValueAsString(obj);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}

		sendRequest(content, auth, _uri);
	}

	private static void sendRequest(final String _content, final AuthentificationInformations auth, final String _uri) {
		HttpClientContext context;
		try {
			context = createContext(auth, _uri);
		} catch (final MalformedURLException e) {
			throw new GenericException(e);
		}
		final HttpPost httpPost = new HttpPost(_uri);
		httpPost.setHeader("Content-type", "application/json");
		final org.apache.http.entity.StringEntity requestEntity = new StringEntity(_content, ContentType.APPLICATION_JSON);
		httpPost.setEntity(requestEntity);
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			try (CloseableHttpResponse response = httpClient.execute(context.getTargetHost(), httpPost, context)) {
				final int status = response.getStatusLine().getStatusCode();
				if ((status < 200) || (status >= 300)) {
					LOG.error("An error Occured while contacting {} errorcode was: {}", status, _uri);
					throw new GenericException("HttpClient got an error: " + status);
				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static HttpClientContext createContext(final AuthentificationInformations auth, final String _uri) throws MalformedURLException {
		final AuthType authType = auth.getAuthType();
		final URL url = new URL(_uri);
		final HttpHost targetHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());

		final HttpClientContext context = Optional.ofNullable(createContext(authType, auth, targetHost))
				.orElse(new HttpClientContext());
		context.setTargetHost(targetHost);
		return context;
	}

	private static HttpClientContext createContext(final AuthType authType, final AuthentificationInformations auth, final HttpHost targetHost) {
		if (authType == AuthType.BASIC) {
			return createBasicContext(auth.getAuthParamBasic(), targetHost);
		} else if (authType == AuthType.OAUTH2_CLIENT_CREDENTIALS) {
			return createOAuth2Context(auth.getAuthParamOath2(), targetHost);
		} else if (authType == AuthType.TLS_CERT) {
			return createTlsCertContext(targetHost);
		}
		throw new GenericException("Unknown Auth type.");
	}

	private static HttpClientContext createTlsCertContext(final HttpHost _targetHost) {
		// http://svn.apache.org/viewvc/httpcomponents/oac.hc3x/trunk/src/contrib/org/apache/commons/httpclient/contrib/ssl/AuthSSLProtocolSocketFactory.java?view=markup
		return new HttpClientContext();
	}

	private static HttpClientContext createOAuth2Context(final AuthParamOath2 authParamOath2, final HttpHost _targetHost) {
		return new HttpClientContext();
	}

	private static HttpClientContext createBasicContext(final AuthParamBasic authParamBasic, final HttpHost _targetHost) {

		final CredentialsProvider credsProvider = new BasicCredentialsProvider();
		final String _username = authParamBasic.getUserName();
		final String _password = authParamBasic.getPassword();
		credsProvider.setCredentials(
				new AuthScope(_targetHost.getHostName(), _targetHost.getPort()),
				new UsernamePasswordCredentials(_username, _password));

		// Create AuthCache instance
		final AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		final BasicScheme basicAuth = new BasicScheme();
		authCache.put(_targetHost, basicAuth);

		// Add AuthCache to the execution context
		final HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);
		return context;
	}

	public static void check(final AuthentificationInformations auth, final String _uri) {
		HttpClientContext context;
		try {
			context = createContext(auth, _uri);
		} catch (final MalformedURLException e) {
			throw new GenericException(e);
		}
		final HttpGet httpPost = new HttpGet(_uri);
		httpPost.setHeader("Content-type", "application/json");
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			try (CloseableHttpResponse response = httpClient.execute(context.getTargetHost(), httpPost, context)) {
				final int status = response.getStatusLine().getStatusCode();
				if (status != 204) {
					LOG.error("Status response must be 204 by was: {} <=> {}", status, _uri);
					throw new GenericException("HttpClient got an error: " + status);
				}
			}
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

}
