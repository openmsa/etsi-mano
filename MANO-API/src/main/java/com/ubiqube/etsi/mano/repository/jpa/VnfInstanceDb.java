package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Service
public class VnfInstanceDb extends AbstractJpaOnly<VnfInstance> implements VnfInstancesRepository {

	private final VnfInstanceJpa repository;

	public VnfInstanceDb(final EntityManager em, final VnfInstanceJpa _repository) {
		super(em, _repository);
		repository = _repository;
	}

	@Override
	protected Class<VnfInstance> getFrontClass() {
		return VnfInstance.class;
	}

	@Override
	public boolean isInstantiate(@NotNull final UUID vnfPkgId) {
		return 0 == repository.countByVnfPkgId(vnfPkgId);
	}

}
