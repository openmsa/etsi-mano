package com.ubiqube.etsi.mano.jpa.config;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.config.Servers;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface ServersJpa extends CrudRepository<Servers, UUID> {
	// Nothing.
}
