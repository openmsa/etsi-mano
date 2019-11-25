package com.ubiqube.etsi.mano.repository.msa;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

/**
 * A single way to handle subscription storage.
 *
 * @author ovi@ubiqube.com
 *
 */
@Profile("!RDBMS")
@Service
public class SubscriptionMsa extends AbstractGenericRepository<SubscriptionObject> implements SubscriptionRepository {

	private static final Logger LOG = LoggerFactory.getLogger(SubscriptionMsa.class);

	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";

	public SubscriptionMsa(final ObjectMapper _mapper, final RepositoryService _repositoryService, final JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, _jsonFilter);
	}

	@Override
	String setId(final SubscriptionObject _entity) {
		final String id = _entity.getPkgmSubscription().getId();
		if (null == id) {
			_entity.getPkgmSubscription().setId(UUID.randomUUID().toString());
		}

		return _entity.getPkgmSubscription().getId();
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

	@Override
	public List<SubscriptionObject> selectNotifications(final String vnfPkgId, final String event) {
		final StringBuilder sb = new StringBuilder("filter.vnfProductsFromProviders.vnfPkgId.eq=").append(vnfPkgId);
		sb.append("&").append("filter.notificationTypes.eq=").append(event);

		return query(sb.toString());
	}

}
