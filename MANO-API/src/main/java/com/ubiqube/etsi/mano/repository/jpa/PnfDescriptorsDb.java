package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

@Service
public class PnfDescriptorsDb extends AbstractDirectJpa<PnfDescriptor> implements PnfdInfoRepository {

	public PnfDescriptorsDb(final EntityManager em, final CrudRepository<PnfDescriptor, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<PnfDescriptor> getFrontClass() {
		return PnfDescriptor.class;
	}

}
