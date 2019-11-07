package com.ubiqube.etsi.mano.repository.jpa;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("RDBMS")
@Service
public class SubscriptionDb extends AbstractJpa<SubscriptionObject, Subscription> implements SubscriptionRepository {

	public SubscriptionDb(final EntityManager em, final CrudRepository<Subscription, UUID> repository, final MapperFacade mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, repository, mapper, contentManager, jsonMapper, namingStrategy);
	}

	@Override
	protected Class getFrontClass() {
		return SubscriptionObject.class;
	}

	@Override
	protected Class getDbClass() {
		return Subscription.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<Subscription> root) {
		return null;
	}

}
