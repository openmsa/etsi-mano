package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

/**
 * Implementation of a repository for a VNFPackage document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Repository
public class VnfPackageMsa extends AbstractGenericRepository<VnfPkgInfo> implements VnfPackageRepository {
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageMsa.class);

	public VnfPackageMsa(ObjectMapper _mapper, RepositoryService _repositoryService, JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(VnfPkgInfo _entity) {
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

}
