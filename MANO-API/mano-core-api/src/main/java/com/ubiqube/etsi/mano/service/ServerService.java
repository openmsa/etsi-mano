package com.ubiqube.etsi.mano.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.jpa.config.ServersJpa;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Service
public class ServerService {
	private final ServersJpa serversJpa;
	private final EventManager eventManager;

	public ServerService(final ServersJpa serversJpa, final EventManager eventManager) {
		super();
		this.serversJpa = serversJpa;
		this.eventManager = eventManager;
	}

	public Page<Servers> findAll(final Pageable pageable) {
		return serversJpa.findAll(pageable);
	}

	public Servers findById(final UUID id) {
		return serversJpa.findById(id).orElseThrow();
	}

	public Servers createServer(final Servers servers) {
		final Servers server = serversJpa.save(servers);
		eventManager.sendAction(ActionType.REGISTER_NFVO, server.getId());
		return server;
	}

	public void deleteById(final UUID id) {
		serversJpa.deleteById(id);
	}

}
