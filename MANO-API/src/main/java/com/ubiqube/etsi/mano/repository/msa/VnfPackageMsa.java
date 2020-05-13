package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.repository.phys.GenericBinaryRepository;

/**
 * Implementation of a repository for a VNFPackage document.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile("!RDBMS")
public class VnfPackageMsa extends GenericBinaryRepository<VnfPackage> implements VnfPackageRepository {

	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageMsa.class);

	public VnfPackageMsa(final ObjectMapper _mapper, final JsonFilter _jsonFilter, final Low low, final NamingStrategy _namingStrategy) {
		super(_mapper, _jsonFilter, low, _namingStrategy);
		LOG.debug("Starting VNF Package MSA.");
	}

	@Override
	protected UUID setId(final VnfPackage _entity) {
		final UUID id = _entity.getId();
		if (null == id) {
			_entity.setId(UUID.randomUUID());
		}

		return _entity.getId();
	}

	@Override
	protected Class<VnfPackage> getClazz() {
		return VnfPackage.class;
	}

	@Override
	protected String getFilename() {
		return "vnfPkgInfo.json";
	}

	@Override
	public VnfPackage save(final VnfPackage _entity) {
		final VnfPackage vnfPkgInfo = super.save(_entity);
		storeObject(vnfPkgInfo.getId(), "indexes.json", new VnfPkgIndex());
		return vnfPkgInfo;
	}

}
