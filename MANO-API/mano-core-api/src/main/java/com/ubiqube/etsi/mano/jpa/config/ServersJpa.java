package com.ubiqube.etsi.mano.jpa.config;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ubiqube.etsi.mano.dao.mano.config.Servers;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface ServersJpa extends PagingAndSortingRepository<Servers, UUID> {
	// Nothing.
}
