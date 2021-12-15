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

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
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
	private final HttpGateway httpGateway;

	public ServerService(final ServersJpa serversJpa, final EventManager eventManager, final HttpGateway httpGateway) {
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

	public Servers createServer(final Servers servers) {
		servers.setServerStatus(PlanStatusType.NOT_STARTED);
		final Servers server = serversJpa.save(servers);
		eventManager.sendAction(ActionType.REGISTER_SERVER, server.getId());
		return server;
	}

	public void deleteById(final UUID id) {
		serversJpa.deleteById(id);
	}

	public ServerAdapter findNearestServer() {
		final List<Servers> lst = serversJpa.findByServerStatusIn(List.of(PlanStatusType.SUCCESS));
		if (lst.isEmpty()) {
			LOG.warn("Unable to find a remote server.");
			return new ServerAdapter(httpGateway, new Servers());
		}
		if (lst.size() > 1) {
			LOG.warn("More than one server exist, picking the first one.");
		}
		final Servers server = lst.get(0);
		return new ServerAdapter(httpGateway, server);
	}

	public void retryById(final UUID id) {
		eventManager.sendAction(ActionType.REGISTER_SERVER, id);
	}

	public ServerAdapter buildServerAdapter(final Subscription subscription) {
		final Servers server = Servers.builder()
				.authentification(subscription.getAuthentication())
				.url(subscription.getCallbackUri())
				.build();
		return new ServerAdapter(httpGateway, server);
	}

}
