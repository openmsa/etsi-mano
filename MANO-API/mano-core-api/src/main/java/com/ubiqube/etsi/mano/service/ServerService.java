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
package com.ubiqube.etsi.mano.service;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.config.RemoteSubscription;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.rest.FluxRest;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class ServerService {

	private static final Logger LOG = LoggerFactory.getLogger(ServerService.class);

	private final ServersJpa serversJpa;
	private final EventManager eventManager;
	private final List<HttpGateway> httpGateway;

	public ServerService(final ServersJpa serversJpa, final EventManager eventManager, final List<HttpGateway> httpGateway) {
		super();
		this.serversJpa = serversJpa;
		this.eventManager = eventManager;
		this.httpGateway = httpGateway;
	}

	public Page<Servers> findAll(final Pageable pageable) {
		return serversJpa.findAll(pageable);
	}

	public Servers findById(final UUID id) {
		return serversJpa.findById(id).orElseThrow(() -> new GenericException("Could not find server id " + id));
	}

	/**
	 * Cannot be transactional because of CLOG field on servers.tlsCert.
	 *
	 * @param servers
	 * @return
	 */
	public Servers createServer(final Servers servers) {
		servers.setServerStatus(PlanStatusType.NOT_STARTED);
		serversJpa.findByUrl(servers.getUrl()).ifPresent(x -> {
			throw new GenericException("duplicate Server: " + x.getId() + " url=" + servers.getUrl());
		});
		return serversJpa.save(servers);
	}

	public void deleteById(final UUID id) {
		final Servers server = serversJpa.findById(id).orElseThrow(() -> new GenericException("Could not find server id " + id));
		final FluxRest rest = new FluxRest(server);
		server.getRemoteSubscriptions().forEach(x -> unregister(rest, x));
		serversJpa.deleteById(id);
	}

	private void unregister(final FluxRest rest, final RemoteSubscription x) {
		final Servers srv = findById(x.getRemoteServerId());
		final HttpGateway hg = filterServer(srv);
		final String uri = "/" + new File(hg.getUrlFor(ApiVersionType.SOL003_VNFPKGM), "subscriptions/{id}");
		final URI resp = rest.uriBuilder().path(uri).build(x.getRemoteSubscriptionId());
		try {
			rest.deleteWithReturn(resp, null);
		} catch (final RuntimeException e) {
			LOG.debug("", e);
			LOG.warn("Could not remove subscription: {}", x.getRemoteSubscriptionId());
		}
	}

	public ServerAdapter findNearestServer() {
		final List<Servers> lst = serversJpa.findByServerStatusIn(List.of(PlanStatusType.SUCCESS));
		if (lst.isEmpty()) {
			LOG.warn("Unable to find a remote server.");
			return new ServerAdapter(httpGateway.get(0), new Servers());
		}
		if (lst.size() > 1) {
			LOG.warn("More than one server exist, picking the first one.");
		}
		final Servers server = lst.get(0);
		final HttpGateway hg = filterServer(server);
		return new ServerAdapter(hg, server);
	}

	public void retryById(final UUID id) {
		eventManager.sendAction(ActionType.REGISTER_SERVER, id);
	}

	public ServerAdapter buildServerAdapter(final Subscription subscription) {
		final Servers server = Servers.builder()
				.authentification(subscription.getAuthentication())
				.url(subscription.getCallbackUri())
				.build();
		if (null == server.getVersion()) {
			return new ServerAdapter(httpGateway.get(0), server);
		}
		final HttpGateway hg = filterServer(server);
		return new ServerAdapter(hg, server);
	}

	public ServerAdapter buildServerAdapter(final Servers servers) {
		final HttpGateway hg = filterServer(servers);
		return new ServerAdapter(hg, servers);
	}

	private HttpGateway filterServer(final Servers servers) {
		return httpGateway.stream().filter(x -> x.getVersion().equals(servers.getVersion())).findAny().orElseThrow(() -> new GenericException("Unable to find version " + servers.getVersion()));
	}

}
