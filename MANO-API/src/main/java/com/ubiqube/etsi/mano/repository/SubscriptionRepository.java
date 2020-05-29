package com.ubiqube.etsi.mano.repository;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.model.vnf.SubscriptionObject;

public interface SubscriptionRepository extends CrudRepository<SubscriptionObject>, BinaryRepository {

	List<SubscriptionObject> selectNotifications(UUID vnfPkgId, String event);

}
