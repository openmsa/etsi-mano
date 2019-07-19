package com.ubiqube.etsi.mano.repository.msa;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

/**
 * A single way to handle subscription storage.
 *
 * @author ovi@ubiqube.com
 *
 */
@Repository
public class SubscriptionMsa extends AbstractGenericRepository<SubscriptionObject> implements SubscriptionRepository {
	@Inject
	public SubscriptionMsa(ObjectMapper _mapper, RepositoryService _repositoryService) {
		super(_mapper, _repositoryService);
	}

	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";

	@Override
	String setId(SubscriptionObject _entity) {
		final String id = _entity.getSubscriptionsPkgmSubscription().getId();
		if (null == id) {
			_entity.getSubscriptionsPkgmSubscription().setId(UUID.randomUUID().toString());
		}

		return _entity.getSubscriptionsPkgmSubscription().getId();
	}

	@Override
	Class<?> getClazz() {
		return SubscriptionObject.class;
	}

	@Override
	String getRoot() {
		return REPOSITORY_SUBSCRIPTION_BASE_PATH;
	}

	@Override
	String getFilename() {
		return "susbscription.json";
	}

}
