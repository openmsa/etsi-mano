package com.ubiqube.etsi.mano.service.event;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;

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
public interface Notifications {

	/**
	 * Send a notification Object to the _uri
	 *
	 * @param obj  The JSON Onject.
	 * @param _uri The complete URL.
	 * @param auth Auth parameters.
	 */
	void doNotification(final Object obj, final String _uri, final AuthentificationInformations auth);

	void check(final AuthentificationInformations auth, final String _uri);

}
