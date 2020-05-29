package com.ubiqube.etsi.mano.repository.jpa;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.NsdRepository;

@Service
public class NsdPackageDb extends AbstractDirectJpa<NsdPackage> implements NsdRepository {

	public NsdPackageDb(final EntityManager em, final NsdPackageJpa repository, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class<NsdPackage> getFrontClass() {
		return NsdPackage.class;
	}

	@Override
	public void changeNsdUpdateState(final NsdPackage nsdPackage, final PackageUsageStateType state) {
		nsdPackage.setNsdUsageState(state);
		save(nsdPackage);
	}

}
