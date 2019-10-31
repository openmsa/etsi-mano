package com.ubiqube.etsi.mano.repository.msa;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgOperation;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@Service
public class VnfLcmOpOccsMsa extends AbstractGenericRepository<VnfLcmOpOcc> implements VnfLcmOpOccsRepository {
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageMsa vnfPackageMsa;
	private static final String REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf-lcm-op-occs";

	public VnfLcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final VnfInstancesRepository _vnfInstancesRepository, final VnfPackageMsa _vnfPackageMsa) {
		super(_mapper, _repositoryService, _jsonFilter);
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfPackageMsa = _vnfPackageMsa;
	}

	@Override
	String setId(final VnfLcmOpOcc _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	String getRoot() {
		return REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "vnfLcmOpOccs.json";
	}

	@Override
	Class<?> getClazz() {
		return VnfLcmOpOcc.class;
	}

	@Override
	public void save(final List<VnfLcmOpOcc> vnfLcmOpOccsIds) {
		vnfLcmOpOccsIds.forEach(this::save);
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType operation) {
		final VnfLcmOpOcc vnfLcmOpOcc = LcmFactory.createVnfLcmOpOccs(operation, vnfInstanceId);
		save(vnfLcmOpOcc);

		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final VnfPkgIndex vnfPkgIndex = vnfPackageMsa.loadObject(vnfInstance.getVnfPkgId(), "indexes.json", VnfPkgIndex.class);
		final VnfPkgInstance instance = new VnfPkgInstance(vnfInstanceId);
		final VnfPkgOperation vnfPackageOperation = new VnfPkgOperation(vnfLcmOpOcc.getId());
		instance.addOperation(vnfPackageOperation);
		vnfPkgIndex.addVnfPkgInstance(instance);
		vnfPackageMsa.storeObject(vnfInstance.getVnfPkgId(), "indexes.json", vnfPkgIndex);
		return vnfLcmOpOcc;
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType operationState) {
		lcmOpOccs.setOperationState(operationState);
		lcmOpOccs.setStateEnteredTime(new Date());
		save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(final String id, final String processId) {
		final VnfLcmOpOcc lcmOpOccs = get(id);
		@NotNull
		final String vnfInstanceId = lcmOpOccs.getVnfInstanceId();
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final VnfPkgIndex vnfPkgIndex = vnfPackageMsa.loadObject(vnfInstance.getVnfPkgId(), "indexes.json", VnfPkgIndex.class);
		final VnfPkgInstance indexInstance = vnfPkgIndex.getVnfPkgInstance(vnfInstanceId);
		final VnfPkgOperation operation = indexInstance.getOperation(id);
		operation.setProcessId(processId);
		vnfPackageMsa.storeObject(vnfInstance.getVnfPkgId(), "indexes.json", vnfPkgIndex);
	}

}
