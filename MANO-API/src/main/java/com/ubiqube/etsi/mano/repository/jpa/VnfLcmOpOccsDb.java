package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@Service
public class VnfLcmOpOccsDb extends AbstractDirectJpa<VnfLcmOpOccs> implements VnfLcmOpOccsRepository {
	private final CrudRepository<VnfLcmOpOccs, UUID> repository;

	public VnfLcmOpOccsDb(final EntityManager em, final CrudRepository<VnfLcmOpOccs, UUID> _repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
	}

	@Override
	public void save(final List<VnfLcmOpOccs> vnfLcmOpOccsIds) {
		vnfLcmOpOccsIds.forEach(this::save);
	}

	@Override
	protected Class<VnfLcmOpOccs> getFrontClass() {
		return VnfLcmOpOccs.class;
	}

	@Override
	public void updateState(final VnfLcmOpOccs lcmOpOccs, final LcmOperationStateType operationState) {
		lcmOpOccs.setOperationState(operationState);
		lcmOpOccs.setStateEnteredTime(new Date());
		save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(final String id, final String processId) {
		final Optional<VnfLcmOpOccs> optLcm = repository.findById(UUID.fromString(id));
		final VnfLcmOpOccs lcm = optLcm.orElseThrow(() -> new NotFoundException("VNF LcmOpOccs " + id + " could not be found."));
		lcm.setExternalProcessId(processId);
		repository.save(lcm);
	}

}
