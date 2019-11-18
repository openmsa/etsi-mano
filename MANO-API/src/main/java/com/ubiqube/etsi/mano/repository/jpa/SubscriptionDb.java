package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Root;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.jpa.SubscriptionJpa;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.repository.ContentManager;
import com.ubiqube.etsi.mano.repository.NamingStrategy;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

import ma.glasnost.orika.MapperFacade;

@Profile("RDBMS")
@Service
public class SubscriptionDb extends AbstractJpa<SubscriptionObject, Subscription> implements SubscriptionRepository {

	private final SubscriptionJpa repository;
	private final MapperFacade mapper;

	public SubscriptionDb(final EntityManager em, final SubscriptionJpa _repository, final MapperFacade _mapper, final ContentManager contentManager, final ObjectMapper jsonMapper, final NamingStrategy namingStrategy) {
		super(em, _repository, _mapper, contentManager, jsonMapper, namingStrategy);
		repository = _repository;
		mapper = _mapper;
	}

	@Override
	protected Class<SubscriptionObject> getFrontClass() {
		return SubscriptionObject.class;
	}

	@Override
	protected Class<Subscription> getDbClass() {
		return Subscription.class;
	}

	@Override
	Map<String, From<?, ?>> getJoin(final Root<Subscription> root) {
		return null;
	}

	@Override
	public List<SubscriptionObject> selectNotifications(final String vnfPkgId, final String event) {
		final List<Subscription> lst = repository.findEventAndVnfPkg(NotificationTypesEnum.fromValue(event), vnfPkgId);
		return lst.stream()
				.map(x -> mapper.map(x, SubscriptionObject.class))
				.collect(Collectors.toList());
	}

}
