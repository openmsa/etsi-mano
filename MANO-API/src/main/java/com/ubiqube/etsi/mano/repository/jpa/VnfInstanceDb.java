package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

import ma.glasnost.orika.MapperFacade;

public class VnfInstanceDb extends AbstractJpa<VnfInstance, com.ubiqube.etsi.mano.dao.mano.VnfInstance> implements VnfInstancesRepository {

	private final VnfInstanceJpa repository;

	public VnfInstanceDb(final EntityManager em, final VnfInstanceJpa _repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(em, _repository, mapper, contentManager, jsonMapper);
		repository = _repository;
	}

	@Override
	protected Class getFrontClass() {
		return VnfInstance.class;
	}

	@Override
	protected Class getDbClass() {
		return com.ubiqube.etsi.mano.dao.mano.VnfInstance.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<com.ubiqube.etsi.mano.dao.mano.VnfInstance> root) {
		return null;
	}

	@Override
	public boolean isInstantiate(@NotNull final String vnfPkgId) {
		return 0 == repository.countByVnfPkgId(UUID.fromString(vnfPkgId));
	}

}
