package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;

import ma.glasnost.orika.MapperFacade;

public class NsInstanceDb extends AbstractJpa<NsInstance, NsdInstance> implements NsInstanceRepository {

	public NsInstanceDb(final EntityManager em, final CrudRepository<NsdInstance, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(em, repository, mapper, contentManager, jsonMapper);
	}

	@Override
	public void changeNsdUpdateState(final NsInstance nsInstance, final InstantiationStateEnum notInstantiated) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Class getFrontClass() {
		return NsInstance.class;
	}

	@Override
	protected Class getDbClass() {
		return NsdInstance.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<NsdInstance> root) {
		return null;
	}

}
