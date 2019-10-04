package com.ubiqube.etsi.mano.repository.msa;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@Service
public class VnfLcmOpOccsMsa extends AbstractGenericRepository<VnfLcmOpOcc> implements VnfLcmOpOccsRepository {

	private static final String REPOSITORY_VNF_LCM_OP_OCCS_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf-lcm-op-occs";

	public VnfLcmOpOccsMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
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

}
