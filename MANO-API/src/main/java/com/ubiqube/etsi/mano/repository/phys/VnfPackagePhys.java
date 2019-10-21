package com.ubiqube.etsi.mano.repository.phys;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Configuration;

@Profile("phys")
@Service
public class VnfPackagePhys extends GenaricBinaryRepository<VnfPkgInfo> implements VnfPackageRepository {

	public VnfPackagePhys(final Configuration _conf, final ObjectMapper objectMapper, final JsonFilter jsonFilter) {
		super(_conf.get("repository.phys.root"), objectMapper, jsonFilter);
	}

	@Override
	protected String setId(final VnfPkgInfo _entity) {
		final String id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID().toString());
		}

		return _entity.getId();
	}

	@Override
	protected Class<VnfPkgInfo> getClazz() {
		return VnfPkgInfo.class;
	}

	@Override
	protected String getFilename() {
		return "vnfPkgInfo.json";
	}

	@Override
	protected String getDir() {
		return "vnf_packages";
	}

	@Override
	public VnfLcmOpOcc createLcmOpOccs(final String vnfInstanceId, final LcmOperationType terminate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateState(final VnfLcmOpOcc lcmOpOccs, final LcmOperationStateType processing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attachProcessIdToLcmOpOccs(@NotNull final String id, final String processId) {
		// TODO Auto-generated method stub

	}

}
