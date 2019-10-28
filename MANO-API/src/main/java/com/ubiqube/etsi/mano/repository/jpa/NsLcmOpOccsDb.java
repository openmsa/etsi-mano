package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

import ma.glasnost.orika.MapperFacade;

public class NsLcmOpOccsDb extends AbstractJpa<NsLcmOpOccsNsLcmOpOcc, NsLcmOpOccs> implements NsLcmOpOccsRepository {

	public NsLcmOpOccsDb(final EntityManager em, final CrudRepository<NsLcmOpOccs, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(em, repository, mapper, contentManager, jsonMapper);
	}

	@Override
	protected Class getFrontClass() {
		return NsLcmOpOccsNsLcmOpOcc.class;
	}

	@Override
	protected Class getDbClass() {
		return NsLcmOpOccs.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<NsLcmOpOccs> root) {
		return null;
	}

}
