package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Service
public class VnfInstanceDb extends AbstractDirectJpa<VnfInstance> implements VnfInstancesRepository {

	private final VnfInstanceJpa repository;

	public VnfInstanceDb(final EntityManager em, final VnfInstanceJpa _repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
	}

	@Override
	protected Class<VnfInstance> getFrontClass() {
		return VnfInstance.class;
	}

	@Override
	public boolean isInstantiate(@NotNull final String vnfPkgId) {
		return 0 == repository.countByVnfPkgId(UUID.fromString(vnfPkgId));
	}

}
