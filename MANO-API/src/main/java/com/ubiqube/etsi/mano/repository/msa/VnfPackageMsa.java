package com.ubiqube.etsi.mano.repository.msa;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgOperation;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 * Implementation of a repository for a VNFPackage document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPackageMsa extends AbstractGenericRepository<VnfPkgInfo> implements VnfPackageRepository {
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageMsa.class);
	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;
	private final VnfInstancesRepository vnfInstancesRepository;

	public VnfPackageMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final VnfInstancesRepository _vnfInstancesRepository) {
		super(_mapper, _repositoryService, _jsonFilter);
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		vnfInstancesRepository = _vnfInstancesRepository;
		LOG.debug("Starting VNF Package MSA.");
	}

	@Override
	String setId(final VnfPkgInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return VnfPkgInfo.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_NVFO_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "vnfPkgInfo.json";
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType operation) {
		final VnfLcmOpOcc vnfLcmOpOcc = LcmFactory.createVnfLcmOpOccs(operation, vnfInstanceId);
		vnfLcmOpOccsRepository.save(vnfLcmOpOcc);

		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final VnfPkgIndex vnfPkgIndex = loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final VnfPkgInstance instance = new VnfPkgInstance(vnfInstanceId);
		final VnfPkgOperation vnfPackageOperation = new VnfPkgOperation(vnfLcmOpOcc.getId());
		instance.addOperation(vnfPackageOperation);
		vnfPkgIndex.addVnfPkgInstance(instance);
		storeObject(vnfInstanceId, vnfPkgIndex, "indexes.json");
		return vnfLcmOpOcc;
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType operationState) {
		lcmOpOccs.setOperationState(operationState);
		lcmOpOccs.setStateEnteredTime(new Date());
		vnfLcmOpOccsRepository.save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String id, final String processId) {
		final VnfLcmOpOcc lcmOpOccs = vnfLcmOpOccsRepository.get(id);
		@NotNull
		final String vnfInstanceId = lcmOpOccs.getVnfInstanceId();
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final VnfPkgIndex vnfPkgIndex = loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final VnfPkgInstance indexInstance = vnfPkgIndex.getVnfPkgInstance(vnfInstanceId);
		final VnfPkgOperation operation = indexInstance.getOperation(id);
		operation.setProcessId(processId);
		storeObject(vnfInstance.getVnfPkgId(), vnfPkgIndex, "indexes.json");
	}
}
