package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Profile("!RDBMS")
public class VnfInstancesMsa extends AbstractGenericRepository<VnfInstance> implements VnfInstancesRepository {
	private static final String INDEXES_JSON = "indexes.json";
	private static final String REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_instances";
	private final VnfPackageMsa vnfPackageRepository;

	public VnfInstancesMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter, final VnfPackageMsa _vnfPackageRepository) {
		super(_mapper, _repositoryService, _jsonFilter);
		vnfPackageRepository = _vnfPackageRepository;
	}

	@Override
	String setId(final VnfInstance _entity) {
		final String id = _entity.getId().toString();
		if (null == id) {
			_entity.setId(UUID.randomUUID());
		}

		return _entity.getId().toString();
	}

	@Override
	Class<?> getClazz() {
		return VnfInstance.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "vnfInstance.json";
	}

	@Override
	public VnfInstance save(final VnfInstance _entity) {
		final VnfInstance vnfInstance = super.save(_entity);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, VnfPkgIndex.class);
		vnfPkgIndex.addVnfPkgInstance(new VnfPkgInstance(vnfInstance.getId().toString()));
		vnfPackageRepository.storeObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, vnfPkgIndex);
		return vnfInstance;
	}

	@Override
	public void delete(final String _id) {
		final VnfInstance vnfInstance = get(_id);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, VnfPkgIndex.class);
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance(_id);
		// TODO We should remove lcm but this will lead to a circular dependency.
		// instance.getOperations().values().stream().forEach(x ->
		// lcmOpOccsMsa.delete(x.getId()));
		vnfPkgIndex.remove(instance);
		vnfPackageRepository.storeObject(vnfInstance.getVnfPkg().getId().toString(), INDEXES_JSON, vnfPkgIndex);
		super.delete(_id);
	}

	@Override
	public boolean isInstantiate(@NotNull final String vnfPkgId) {
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfPkgId, INDEXES_JSON, VnfPkgIndex.class);
		final VnfPkgInstance instance = vnfPkgIndex.getVnfPkgInstance(vnfPkgId);
		return instance == null;
	}

}
