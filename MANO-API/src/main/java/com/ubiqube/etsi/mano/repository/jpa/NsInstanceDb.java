package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;

@Service
public class NsInstanceDb extends AbstractJpaOnly<NsdInstance> implements NsInstanceRepository {

	public NsInstanceDb(final EntityManager em, final CrudRepository<NsdInstance, UUID> repository) {
		super(em, repository);
	}

	@Override
	protected Class<NsdInstance> getFrontClass() {
		return NsdInstance.class;
	}

}
