package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;

@Service
public class NsInstanceDb extends AbstractDirectJpa<NsdInstance> implements NsInstanceRepository {

	public NsInstanceDb(final EntityManager em, final CrudRepository<NsdInstance, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<NsdInstance> getFrontClass() {
		return NsdInstance.class;
	}

}
