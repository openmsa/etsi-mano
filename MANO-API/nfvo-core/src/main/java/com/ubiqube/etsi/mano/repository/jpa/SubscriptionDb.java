package com.ubiqube.etsi.mano.repository.jpa;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.jpa.SubscriptionJpa;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

@Service
public class SubscriptionDb extends AbstractJpaOnly<Subscription> implements SubscriptionRepository {

	private final SubscriptionJpa repository;

	public SubscriptionDb(final EntityManager em, final SubscriptionJpa _repository) {
		super(em, _repository);
		repository = _repository;
	}

	@Override
	protected Class<Subscription> getFrontClass() {
		return Subscription.class;
	}

	@Override
	public List<Subscription> selectNotifications(final UUID vnfPkgId, final String event) {
		return repository.findEventAndVnfPkg(event, vnfPkgId.toString());
	}

}
