package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
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
	@Inject
	public VnfPackageMsa(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_mapper, _repositoryService);
	}

	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";

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
