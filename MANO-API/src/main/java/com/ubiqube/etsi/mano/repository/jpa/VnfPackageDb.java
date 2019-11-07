package com.ubiqube.etsi.mano.repository.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("RDBMS")
@Service
public class VnfPackageDb extends AbstractJpa<VnfPkgInfo, VnfPackage> implements VnfPackageRepository {

	public VnfPackageDb(final EntityManager em, final CrudRepository<VnfPackage, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, mapper, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class getFrontClass() {
		return VnfPkgInfo.class;
	}

	@Override
	protected Class getDbClass() {
		return VnfPackage.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<VnfPackage> root) {
		final Map<String, From<?, ?>> joins = new HashMap<>();
		joins.put("ROOT", root);
		Join<Object, Object> jTmp = root.join("softwareImages", JoinType.LEFT);
		joins.put("softwareImages", jTmp);
		jTmp = jTmp.join("checksum");
		joins.put("checksum", jTmp);
		jTmp = root.join("additionalArtifacts", JoinType.LEFT);
		joins.put("additionalArtifacts", jTmp);
		return joins;
	}
}
