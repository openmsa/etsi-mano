package com.ubiqube.etsi.mano.factory;

import java.util.Date;
import java.util.Map;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationVnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotificationVnfPackageChangeNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotificationVnfPackageChangeNotification.ChangeTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoAdditionalArtifacts;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

public class VnfPackageFactory {
	private VnfPackageFactory() {
		// Nothing.
	}

	public static VnfPkgInfo createVnfPkgInfo(final Map<String, Object> userData) {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.DISABLED);
		vnfPkgInfo.setUsageState(UsageStateEnum.NOT_IN_USE);

		return vnfPkgInfo;
	}

	public static VnfPackagesVnfPkgInfoAdditionalArtifacts createArtefact(final String _filename, final String _checksum) {
		final VnfPackagesVnfPkgInfoAdditionalArtifacts artefact = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		artefact.artifactPath(_filename);
		final VnfPackagesVnfPkgInfoChecksum checksum = new VnfPackagesVnfPkgInfoChecksum();
		checksum.algorithm(Constants.HASH_ALGORITHM);
		checksum.setHash(_checksum);
		artefact.setChecksum(checksum);
		return artefact;
	}

	public static VnfPackagesVnfPkgInfoAdditionalArtifacts createArtefact(final String _filename, final VnfPackagesVnfPkgInfoChecksum _checksum) {
		final VnfPackagesVnfPkgInfoAdditionalArtifacts artefact = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		artefact.artifactPath(_filename);
		artefact.setChecksum(_checksum);
		return artefact;
	}

	public static VnfPackageChangeNotification createVnfPackageChangeNotification(final String _subscriptionId, final String _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageChangeNotification ret = new VnfPackageChangeNotification();
		final VnfPackageChangeNotificationVnfPackageChangeNotification obj = createVnfPackageChangeNotificationVnfPackageChangeNotification(_subscriptionId, _vnfPkgId, _vnfdId, links);
		ret.setVnfPackageChangeNotification(obj);
		return ret;
	}

	public static VnfPackageChangeNotificationVnfPackageChangeNotification createVnfPackageChangeNotificationVnfPackageChangeNotification(final String _subscriptionId, final String _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageChangeNotificationVnfPackageChangeNotification ret = new VnfPackageChangeNotificationVnfPackageChangeNotification();
		ret.setChangeType(ChangeTypeEnum.OP_STATE_CHANGE);
		ret.setNotificationType("VnfPackageChangeNotification");
		ret.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotificationVnfPackageChangeNotification.OperationalStateEnum.DISABLED);
		ret.setSubscriptionId(_subscriptionId);
		ret.setTimeStamp(new Date());
		ret.setVnfdId(_vnfdId);
		ret.setVnfPkgId(_vnfPkgId);
		ret.setLinks(links.createNotificationLink(_vnfPkgId, _subscriptionId));
		return ret;
	}

	public static NotificationVnfPackageOnboardingNotification createNotificationVnfPackageOnboardingNotification(final String _subscriptionId, final String _vnfPkgId, final String _vnfdId, final Linkable links) {
		final NotificationVnfPackageOnboardingNotification ret = new NotificationVnfPackageOnboardingNotification();
		ret.setTimeStamp(new Date());
		ret.setNotificationType("VnfPackageOnboardingNotification");
		ret.setSubscriptionId(_subscriptionId);
		ret.setVnfPkgId(_vnfPkgId);
		ret.setVnfdId(_vnfdId);
		ret.setLinks(links.createVnfPackageOnboardingNotificationLinks(_vnfPkgId, _subscriptionId));
		return ret;
	}

	public static SubscriptionsPkgmSubscription createSubscriptionsPkgmSubscription(final String _callbackUri, final SubscriptionsPkgmSubscriptionFilter _filter) {
		final SubscriptionsPkgmSubscription subscriptionsPkgmSubscription = new SubscriptionsPkgmSubscription();
		subscriptionsPkgmSubscription.setCallbackUri(_callbackUri);
		subscriptionsPkgmSubscription.setFilter(_filter);
		return subscriptionsPkgmSubscription;
	}

	public static SubscriptionsPkgmSubscription createSubscriptionsPkgmSubscription(final SubscriptionObject subscriptionObject) {
		final SubscriptionsPkgmSubscription ret = new SubscriptionsPkgmSubscription();
		ret.setCallbackUri(subscriptionObject.getSubscriptionsPkgmSubscription().getCallbackUri());
		ret.setId(subscriptionObject.getSubscriptionsPkgmSubscription().getId());
		ret.setFilter(subscriptionObject.getSubscriptionsPkgmSubscription().getFilter());
		return ret;
	}

}
