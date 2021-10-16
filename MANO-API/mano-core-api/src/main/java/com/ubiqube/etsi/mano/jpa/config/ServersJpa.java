package com.ubiqube.etsi.mano.jpa.config;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.v2.PlanStatusType;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface ServersJpa extends PagingAndSortingRepository<Servers, UUID> {

	List<Servers> findByServerStatusIn(List<PlanStatusType> asList);
}
