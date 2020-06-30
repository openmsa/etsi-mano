package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgOnboardingNotification;
import com.ubiqube.etsi.mano.controller.vnf.VnfSubscriptionManagement;
import com.ubiqube.etsi.mano.dao.mano.Subscription;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!VNFM" })
@RestController
public class VnfSubscriptionSol005Api implements VnfSubscriptionSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(VnfSubscriptionSol005Api.class);

	private final VnfSubscriptionManagement vnfSubscriptionManagement;

	private final Linkable links = new Sol005Linkable();

	private final MapperFacade mapper;

	public VnfSubscriptionSol005Api(final VnfSubscriptionManagement _vnfSubscriptionManagement, final MapperFacade _mapper) {
		vnfSubscriptionManagement = _vnfSubscriptionManagement;
		mapper = _mapper;
		LOG.info("Starting VNF Subscription Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsGet(@RequestParam(value = "filter", required = false) final String filters) {
		final List<Subscription> list = vnfSubscriptionManagement.subscriptionsGet(filters);
		final List<PkgmSubscription> pkgms = mapper.mapAsList(list, PkgmSubscription.class);
		pkgms.stream().forEach(x -> x.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(x.getId())));
		return ResponseEntity.ok(pkgms);
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsPost(final PkgmSubscriptionRequest subscriptionsPostQuery) {
		Subscription subscription = mapper.map(subscriptionsPostQuery, Subscription.class);
		subscription = vnfSubscriptionManagement.subscriptionsPost(subscription);
		final PkgmSubscription pkgmSubscription = mapper.map(subscription, PkgmSubscription.class);
		pkgmSubscription.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(pkgmSubscription.getId()));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.OK);
	}

	@Override
	public void subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		vnfSubscriptionManagement.subscriptionsSubscriptionIdDelete(subscriptionId);
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final Subscription subscription = vnfSubscriptionManagement.subscriptionsSubscriptionIdGet(UUID.fromString(subscriptionId));
		final PkgmSubscription pkgmSubscription = mapper.map(subscription, PkgmSubscription.class);
		pkgmSubscription.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(subscriptionId));
		return new ResponseEntity<>(pkgmSubscription, HttpStatus.OK);

	}

	@Override
	public void vnfPackageChangeNotificationPost(final VnfPackageChangeNotification notificationsMessage) {
		final VnfPkgChangeNotification msg = mapper.map(notificationsMessage, VnfPkgChangeNotification.class);
		vnfSubscriptionManagement.vnfPackageChangeNotificationPost(msg);
	}

	@Override
	public void vnfPackageOnboardingNotificationPost(final VnfPackageOnboardingNotification notificationsMessage) {
		final VnfPkgOnboardingNotification msg = mapper.map(notificationsMessage, VnfPkgOnboardingNotification.class);
		vnfSubscriptionManagement.vnfPackageOnboardingNotificationPost(msg);
	}

}
