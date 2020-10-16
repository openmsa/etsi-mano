package com.ubiqube.etsi.mano.repository.jpa;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class VnfPackageDb extends AbstractDirectJpa<VnfPackage> implements VnfPackageRepository {

	public VnfPackageDb(final EntityManager em, final CrudRepository<VnfPackage, UUID> repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<VnfPackage> getFrontClass() {
		return VnfPackage.class;
	}

}
