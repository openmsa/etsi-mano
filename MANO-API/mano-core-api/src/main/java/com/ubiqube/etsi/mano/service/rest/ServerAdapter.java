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

import java.io.File;
import java.net.URI;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ServerAdapter {

	private final HttpGateway httpGateway;
	private final FluxRest rest;
	private final Servers server;

	public ServerAdapter(final HttpGateway httpGateway, final Servers server) {
		super();
		this.httpGateway = httpGateway;
		this.server = server;
		rest = new FluxRest(server);
	}

	public Servers getServer() {
		return server;
	}

	public HttpGateway httpGateway() {
		return httpGateway;
	}

	public URI getUriFor(final ApiVersionType type, final String urlPart, final Map<String, Object> params) {
		final String url = new File(httpGateway.getUrlFor(type), urlPart).toString();
		return rest.uriBuilder().pathSegment(url).buildAndExpand(params).toUri();
	}

	public FluxRest rest() {
		return rest;
	}

	public URI getUriFor(final ApiVersionType type, final String urlPart) {
		return getUriFor(type, urlPart, Map.of());
	}
}
