package com.ubiqube.etsi.mano.service.rest;

import java.net.URI;
import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.service.HttpGateway;

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

	public String getBaseUri(final ApiVersionType sol003Grant) {
		// TODO Auto-generated method stub
		return null;
	}

	public HttpGateway httpGateway() {
		return httpGateway;
	}

	public URI getUriFor(final ApiVersionType type, final Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public FluxRest rest() {
		return rest;
	}
}
