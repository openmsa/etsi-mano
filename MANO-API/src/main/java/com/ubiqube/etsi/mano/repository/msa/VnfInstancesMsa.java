package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@Repository
public class VnfInstancesMsa extends AbstractGenericRepository<VnfInstance> implements VnfInstancesRepository {
	private static final String REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_instances";

	@Inject
	public VnfInstancesMsa(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_mapper, _repositoryService);
	}

	@Override
	String getUriForId(String _id) {
		return REPOSITORY_VNF_INSTANCE_DATAFILE_BASE_PATH + "/" + _id + "/vnfInstance.json";
	}

	@Override
	String setId(VnfInstance _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	Class<?> getClazz() {
		return VnfInstance.class;
	}

	@Override
	public Object query() {
		return null;
	}

}
