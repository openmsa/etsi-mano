package com.ubiqube.etsi.mano.repository;

import java.util.List;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;

public interface SubscriptionRepository extends CrudRepository<SubscriptionObject>, BinaryRepository {

	List<SubscriptionObject> selectNotifications(String vnfPkgId, String event);

}
