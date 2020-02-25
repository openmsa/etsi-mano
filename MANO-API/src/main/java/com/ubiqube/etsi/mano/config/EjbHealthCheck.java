package com.ubiqube.etsi.mano.config;

import java.util.Optional;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.etsi.mano.service.ejb.RepositoryServiceEjb;

@Component
public class EjbHealthCheck implements HealthIndicator {
	private static final String STATUS = "status";
	private final RepositoryServiceEjb ejb;

	public EjbHealthCheck(final RepositoryServiceEjb _ejb) {
		ejb = _ejb;
	}

	@Override
	public Health health() {
		try {
			final Optional<RepositoryElement> res = Optional.ofNullable(ejb.getElement("/Process/ETSI-MANO/composer.json"));
			if (!res.isPresent()) {
				return Health.down().withDetail(STATUS, "Element /Process/ETSI-MANO/composer.json not found.").build();
			}
		} catch (final Exception e) {
			return Health.down().withDetail(STATUS, e.getMessage()).build();
		}
		return Health.up().withDetail(STATUS, "EJB services online.").build();
	}

}
