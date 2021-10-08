package com.ubiqube.etsi.mano.vnfm.jpa;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;

public interface VnfPackageOnboardingNotificationJpa extends CrudRepository<VnfPackageOnboardingNotification, UUID> {
	// Nothing.
}
