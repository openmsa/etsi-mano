package com.ubiqube.etsi.mano.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PkgmNotificationsFilter.NotificationTypesEnum;

@Repository
public interface SubscriptionJpa extends CrudRepository<Subscription, UUID> {

	@Query("select s from Subscription s")
	List<Subscription> findEventAndVnfPkg(NotificationTypesEnum notificationTypesEnum, String vnfPkgId);
}
