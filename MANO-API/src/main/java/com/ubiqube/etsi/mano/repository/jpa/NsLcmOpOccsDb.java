package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

@Service
public class NsLcmOpOccsDb extends AbstractDirectJpa<NsLcmOpOccs> implements NsLcmOpOccsRepository {

	private final CrudRepository<NsLcmOpOccs, UUID> repository;

	public NsLcmOpOccsDb(final EntityManager em, final CrudRepository<NsLcmOpOccs, UUID> _repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
	}

	@Override
	protected Class<NsLcmOpOccs> getFrontClass() {
		return NsLcmOpOccs.class;
	}

	@Override
	public NsLcmOpOccs createLcmOpOccs(final NsdInstance nsInstanceId, final NsLcmOpType state) {
		final NsLcmOpOccs lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceId, state);
		return save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final UUID lcmOpOccsId, final String processId) {
		final Optional<NsLcmOpOccs> optLcm = repository.findById(lcmOpOccsId);
		final NsLcmOpOccs lcm = optLcm.orElseThrow(() -> new NotFoundException("VNF LcmOpOccs " + lcmOpOccsId + " could not be found."));
		lcm.setExternalProcessId(processId);
		repository.save(lcm);
	}

}
