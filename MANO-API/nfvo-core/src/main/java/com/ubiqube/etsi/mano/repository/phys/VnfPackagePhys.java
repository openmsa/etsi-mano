package com.ubiqube.etsi.mano.repository.phys;

import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.repository.Low;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Profile("phys")
@Service
public class VnfPackagePhys extends GenericBinaryRepository<VnfPackage> implements VnfPackageRepository {

	public VnfPackagePhys(final ObjectMapper objectMapper, final JsonFilter jsonFilter, final Low low, final NamingStrategy _namingStrategy) {
		super(objectMapper, jsonFilter, low, _namingStrategy);
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

}
