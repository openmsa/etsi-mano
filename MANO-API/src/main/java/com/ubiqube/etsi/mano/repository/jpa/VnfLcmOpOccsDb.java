package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfLcmOpOccsDb extends AbstractJpa<VnfLcmOpOcc, VnfLcmOpOccs> implements VnfLcmOpOccsRepository {
	private final CrudRepository<VnfLcmOpOccs, UUID> repository;

	public VnfLcmOpOccsDb(final EntityManager em, final CrudRepository<VnfLcmOpOccs, UUID> _repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, mapper, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
	}

	@Override
	public void save(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		vnfLcmOpOccsIds.forEach(this::save);
	}

	@Override
	protected Class<VnfLcmOpOcc> getFrontClass() {
		return VnfLcmOpOcc.class;
	}

	@Override
	protected Class<VnfLcmOpOccs> getDbClass() {
		return VnfLcmOpOccs.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<VnfLcmOpOccs> root) {
		return new HashMap<>();
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType operation) {
		final VnfLcmOpOcc vnfLcmOpOcc = LcmFactory.createVnfLcmOpOccs(operation, vnfInstanceId);
		return save(vnfLcmOpOcc);
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType operationState) {
		lcmOpOccs.setOperationState(operationState);
		lcmOpOccs.setStateEnteredTime(new Date());
		save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String id, final String processId) {
		final Optional<VnfLcmOpOccs> optLcm = repository.findById(UUID.fromString(id));
		final VnfLcmOpOccs lcm = optLcm.orElseThrow(() -> new NotFoundException("VNF LcmOpOccs " + id + " could not be found."));
		lcm.setExternalProcessId(processId);
		repository.save(lcm);
	}

}
