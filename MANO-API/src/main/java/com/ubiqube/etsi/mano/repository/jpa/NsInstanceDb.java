package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;

@Service
public class NsInstanceDb extends AbstractDirectJpa<NsdInstance> implements NsInstanceRepository {

	public NsInstanceDb(final EntityManager em, final CrudRepository<NsdInstance, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	public void changeNsdUpdateState(final NsdInstance nsInstance, final InstantiationStateEnum notInstantiated) {
		// TODO Auto-generated method stub
	}

	@Override
	protected Class<NsdInstance> getFrontClass() {
		return NsdInstance.class;
	}

	protected void mapChild(final NsdInstance vnf) {
		if (null != vnf.getVnfInstance()) {
			vnf.getVnfInstance().forEach(x -> x.setNsInstance(vnf));
		}
	}

}
