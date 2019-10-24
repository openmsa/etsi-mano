package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

import ma.glasnost.orika.MapperFacade;

public class PnfDescriptorsDb extends AbstractJpa<PnfDescriptorsPnfdInfo, PnfDescriptor> implements PnfdInfoRepository {

	public PnfDescriptorsDb(final EntityManager em, final CrudRepository<PnfDescriptor, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(em, repository, mapper, contentManager, jsonMapper);
	}

	@Override
	protected Class getFrontClass() {
		return PnfDescriptorsPnfdInfo.class;
	}

	@Override
	protected Class getDbClass() {
		return PnfDescriptor.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<PnfDescriptor> root) {
		return null;
	}

}
