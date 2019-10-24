package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

import ma.glasnost.orika.MapperFacade;

public class VnfLcmOpOccsDb extends AbstractJpa<VnfLcmOpOcc, VnfLcmOpOccs> implements VnfLcmOpOccsRepository {

	public VnfLcmOpOccsDb(final EntityManager em, final CrudRepository<VnfLcmOpOccs, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper) {
		super(em, repository, mapper, contentManager, jsonMapper);
	}

	@Override
	public void save(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		vnfLcmOpOccsIds.forEach(this::save);
	}

	@Override
	protected Class getFrontClass() {
		return VnfLcmOpOcc.class;
	}

	@Override
	protected Class getDbClass() {
		return VnfLcmOpOccs.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<VnfLcmOpOccs> root) {
		// TODO Auto-generated method stub
		return null;
	}

}
