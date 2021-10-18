package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

@Service
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
		return serversJpa.findById(id).orElseThrow();
	}

	public Servers createServer(final Servers servers) {
		servers.setServerStatus(PlanStatusType.NOT_STARTED);
		final Servers server = serversJpa.save(servers);
		eventManager.sendAction(ActionType.REGISTER_NFVO, server.getId());
		return server;
	}

	public void deleteById(final UUID id) {
		serversJpa.deleteById(id);
	}

	public ServerAdapter findNearestServer() {
		final List<Servers> lst = serversJpa.findByServerStatusIn(List.of(PlanStatusType.SUCCESS));
		if (lst.isEmpty()) {
			throw new GenericException("Unable to find a remote server.");
		}
		if (lst.size() > 1) {
			LOG.warn("More than one server exist, picking the first one.");
		}
		final Servers server = lst.get(0);
		return new ServerAdapter(httpGateway, server);
	}
}
