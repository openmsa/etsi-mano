package com.ubiqube.etsi.mano.repository.msa;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
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

	private static final Logger LOG = LoggerFactory.getLogger(SubscriptionMsa.class);

	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";
	private final JsonFilter jsonFilter;

	public SubscriptionMsa(ObjectMapper _mapper, RepositoryService _repositoryService, JsonFilter _jsonFilter) {
		super(_mapper, _repositoryService, null);
		jsonFilter = _jsonFilter;
	}

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

	@Override
	public List<SubscriptionObject> query(String _query) {
		final AstBuilder astBuilder = new AstBuilder(_query);
		List<String> listFilesInFolder;
		try {
			listFilesInFolder = repositoryService.doSearch(REPOSITORY_SUBSCRIPTION_BASE_PATH, "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		final ArrayList<SubscriptionObject> ret = new ArrayList<>();
		for (final String entry : listFilesInFolder) {
			final String path = entry.substring((REPOSITORY_SUBSCRIPTION_BASE_PATH + '/').length());
			final File file = new File(path);
			LOG.info("Retreiving: {}", file.getParent());
			final SubscriptionObject subscriptionObject = get(file.getParent());
			if (jsonFilter.apply(subscriptionObject.getSubscriptionsPkgmSubscription(), astBuilder)) {
				ret.add(subscriptionObject);
			}
		}
		return ret;
	}
}
