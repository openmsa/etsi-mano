package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpType;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("RDBMS")
@Service
public class NsLcmOpOccsDb extends AbstractJpa<NsLcmOpOcc, NsLcmOpOccs> implements NsLcmOpOccsRepository {
	private final MapperFacade mapper;

	private final CrudRepository<NsLcmOpOccs, UUID> repository;

	public NsLcmOpOccsDb(final EntityManager em, final CrudRepository<NsLcmOpOccs, UUID> _repository, final MapperFacade _mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, _mapper, contentManager, jsonMapper, namingStrategy);
		mapper = _mapper;
		repository = _repository;
	}

	@Override
	protected Class<NsLcmOpOcc> getFrontClass() {
		return NsLcmOpOcc.class;
	}

	@Override
	protected Class<NsLcmOpOccs> getDbClass() {
		return NsLcmOpOccs.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<NsLcmOpOccs> root) {
		return null;
	}

	@Override
	public NsLcmOpOcc createLcmOpOccs(final String nsInstanceId, final NsLcmOpType state) {
		final NsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOcc(nsInstanceId, state);
		return save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String lcmOpOccsId, final String processId) {
		final Optional<NsLcmOpOccs> optLcm = repository.findById(UUID.fromString(lcmOpOccsId));
		final NsLcmOpOccs lcm = optLcm.orElseThrow(() -> new NotFoundException("VNF LcmOpOccs " + lcmOpOccsId + " could not be found."));
		lcm.setExternalProcessId(processId);
		repository.save(lcm);
	}

}
