package com.ubiqube.etsi.mano.service.rest;

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
		final String url = httpGateway.getUrlFor(type) + urlPart;
		return rest.uriBuilder().pathSegment(url).buildAndExpand(params).toUri();
	}

	public FluxRest rest() {
		return rest;
	}
}
