package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("RDBMS")
@Service
public class PnfDescriptorsDb extends AbstractJpa<PnfdInfo, PnfDescriptor> implements PnfdInfoRepository {

	public PnfDescriptorsDb(final EntityManager em, final CrudRepository<PnfDescriptor, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, mapper, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<PnfdInfo> getFrontClass() {
		return PnfdInfo.class;
	}

	@Override
	protected Class<PnfDescriptor> getDbClass() {
		return PnfDescriptor.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<PnfDescriptor> root) {
		return null;
	}

}
