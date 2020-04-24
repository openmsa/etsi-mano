package com.ubiqube.etsi.mano.repository.msa;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgOperation;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("!RDBMS")
public class VnfLcmOpOccsMsa extends AbstractGenericRepository<VnfLcmOpOccs> implements VnfLcmOpOccsRepository {
	private static final String INDEXES_JSON = "indexes.json";
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageMsa vnfPackageMsa;
	private final MapperFacade mapper;
	private static final String REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf-lcm-op-occs";

	public VnfLcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final VnfInstancesRepository _vnfInstancesRepository, final VnfPackageMsa _vnfPackageMsa, final MapperFacade _orikamapper) {
		super(_mapper, _repositoryService, _jsonFilter);
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfPackageMsa = _vnfPackageMsa;
		mapper = _orikamapper;
	}

	@Override
	String setId(final VnfLcmOpOccs _entity) {
		final String id = _entity.getId().toString();
		if (null == id) {
			_entity.setId(UUID.randomUUID());
		}

		return _entity.getId().toString();
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
	public void save(final List<VnfLcmOpOccs> vnfLcmOpOccsIds) {
		vnfLcmOpOccsIds.forEach(this::save);
	}

	public VnfLcmOpOccs createLcmOpOccs(final UUID vnfInstanceId, final LcmOperationType operation) {
		final VnfLcmOpOccs vnfLcmOpOcc = LcmFactory.createVnfLcmOpOccs(operation, vnfInstanceId);
		save(vnfLcmOpOcc);

		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId.toString());
		final VnfPkgIndex vnfPkgIndex = vnfPackageMsa.loadObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, VnfPkgIndex.class);
		final VnfPkgInstance instance = new VnfPkgInstance(vnfInstanceId.toString());
		final VnfPkgOperation vnfPackageOperation = new VnfPkgOperation(vnfLcmOpOcc.getId().toString());
		instance.addOperation(vnfPackageOperation);
		vnfPkgIndex.addVnfPkgInstance(instance);
		vnfPackageMsa.storeObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, vnfPkgIndex);
		return vnfLcmOpOcc;
	}

	@Override
	public void updateState(final VnfLcmOpOccs lcmOpOccs, final LcmOperationStateType operationState) {
		lcmOpOccs.setOperationState(operationState);
		lcmOpOccs.setStateEnteredTime(new Date());
		save(lcmOpOccs);
	}

	@Override
	public void attachProcessIdToLcmOpOccs(final String id, final String processId) {
		final VnfLcmOpOccs lcmOpOccs = get(id);
		@NotNull
		final String vnfInstanceId = lcmOpOccs.getVnfInstance().getId().toString();
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final VnfPkgIndex vnfPkgIndex = vnfPackageMsa.loadObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, VnfPkgIndex.class);
		final VnfPkgInstance indexInstance = vnfPkgIndex.getVnfPkgInstance(vnfInstanceId);
		final VnfPkgOperation operation = indexInstance.getOperation(id);
		operation.setProcessId(processId);
		vnfPackageMsa.storeObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, vnfPkgIndex);
	}

	@Override
	public VnfLcmOpOccs get(final UUID id) {
		return get(id.toString());
	}

	@Override
	public void delete(final UUID id) {
		delete(id.toString());
	}

}
