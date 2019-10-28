package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

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
@Profile("!RDBMS")
@Service
public class VnfPackageMsa extends AbstractGenericRepository<VnfPkgInfo> implements VnfPackageRepository {
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageMsa.class);

	public VnfPackageMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
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

}
