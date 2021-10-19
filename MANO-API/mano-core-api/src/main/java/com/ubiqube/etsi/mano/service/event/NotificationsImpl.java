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
package com.ubiqube.etsi.mano.service.event;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.rest.FluxRest;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

@Service
public class NotificationsImpl implements Notifications {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(NotificationsImpl.class);
	/** JSON mapper. */
	private final ObjectMapper mapper;

	public NotificationsImpl(final ObjectMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * Send a notification Object to the _uri
	 *
	 * @param obj  The JSON Onject.
	 * @param _uri The complete URL.
	 * @param auth Auth parameters.
	 */
	@Override
	public void doNotification(final Object obj, final String _uri, final ServerAdapter server) {
		String content;
		try {
			content = mapper.writeValueAsString(obj);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}

		sendRequest(content, server, _uri);
	}

	private static void sendRequest(final String _content, final ServerAdapter server, final String _uri) {
		final var rest = server.rest();
		rest.post(URI.create(_uri), _content, Void.class);
	}

	@Override
	public void check(final ServerAdapter server, final String _uri) {
		final var rest = server.rest();
		doRealCheck(rest, _uri);
	}

	private static void doRealCheck(final FluxRest rest, final String _uri) {
		final ResponseEntity<Void> status = rest.getWithReturn(URI.create(_uri), Void.class);
		if (status.getStatusCode() != HttpStatus.NO_CONTENT) {
			LOG.error("Status response must be 204 by was: {} <=> {}", status, _uri);
			throw new GenericException("HttpClient got an error: " + status + ", must be 204");
		}
	}

	@Override
	public void check(final AuthentificationInformations authentication, final String callbackUri) {
		final Servers server = new Servers();
		server.setAuthentification(authentication);
		server.setUrl(callbackUri);
		final FluxRest rest = new FluxRest(server);
		doRealCheck(rest, callbackUri);
	}

}
