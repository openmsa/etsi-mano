package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.repository.phys.GenaricBinaryRepository;

/**
 * Implementation of a repository for a VNFPackage document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile("!RDBMS")
@Service
public class VnfPackageMsa extends GenaricBinaryRepository<VnfPkgInfo> implements VnfPackageRepository {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageMsa.class);

	public VnfPackageMsa(final ObjectMapper _mapper, final JsonFilter _jsonFilter, final Low low, final NamingStrategy _namingStrategy) {
		super(_mapper, _jsonFilter, low, _namingStrategy);
		LOG.debug("Starting VNF Package MSA.");
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
	public VnfPkgInfo save(final VnfPkgInfo _entity) {
		final VnfPkgInfo vnfPkgInfo = super.save(_entity);
		storeObject(vnfPkgInfo.getId(), "indexes.json", new VnfPkgIndex());
		return vnfPkgInfo;
	}

}
