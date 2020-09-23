package com.ubiqube.etsi.mano.repository;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.Subscription;

public interface SubscriptionRepository extends CrudRepositoryNg<Subscription> {

	List<Subscription> selectNotifications(UUID vnfPkgId, String event);

}
