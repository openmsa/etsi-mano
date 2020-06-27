package com.ubiqube.etsi.mano.nfvo.v261;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.Checksum;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.notification.PackageChangeType;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.notification.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.notification.VnfPackageOnboardingNotification;

public class VnfPackageFactory {
	private VnfPackageFactory() {
		// Nothing.
	}

	@Nonnull
	public static VnfPackage createVnfPkgInfo(final Map<String, String> userData) {
		final VnfPackage vnfPkgInfo = new VnfPackage();
		vnfPkgInfo.setOnboardingState(OnboardingStateType.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(PackageOperationalState.DISABLED);
		vnfPkgInfo.setUsageState(PackageUsageState.NOT_IN_USE);

		return vnfPkgInfo;
	}

	@Nonnull
	public static VnfPackageArtifactInfo createArtefact(final String _filename, final String _checksum) {
		final VnfPackageArtifactInfo artefact = new VnfPackageArtifactInfo();
		artefact.artifactPath(_filename);
		final Checksum checksum = new Checksum();
		checksum.algorithm(Constants.HASH_ALGORITHM);
		checksum.setHash(_checksum);
		artefact.setChecksum(checksum);
		return artefact;
	}

	@Nonnull
	public static VnfPackageArtifactInfo createArtefact(final String _filename, final Checksum _checksum) {
		final VnfPackageArtifactInfo artefact = new VnfPackageArtifactInfo();
		artefact.artifactPath(_filename);
		artefact.setChecksum(_checksum);
		return artefact;
	}

	@Nonnull
	public static VnfPackageChangeNotification createVnfPackageChangeNotification(final UUID _subscriptionId, @Nonnull final UUID _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageChangeNotification ret = new VnfPackageChangeNotification();
		ret.setChangeType(PackageChangeType.OP_STATE_CHANGE);
		ret.setNotificationType("VnfPackageChangeNotification");
		ret.setOperationalState(PackageOperationalStateType.DISABLED);
		ret.setSubscriptionId(_subscriptionId.toString());
		ret.setTimeStamp(OffsetDateTime.now());
		ret.setVnfdId(_vnfdId);
		ret.setVnfPkgId(_vnfPkgId.toString());
		ret.setLinks(links.createNotificationLink(_vnfPkgId, _subscriptionId));
		return ret;
	}

	@Nonnull
	public static VnfPackageOnboardingNotification createNotificationVnfPackageOnboardingNotification(final UUID _subscriptionId, @Nonnull final UUID _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageOnboardingNotification ret = new VnfPackageOnboardingNotification();
		ret.setTimeStamp(OffsetDateTime.now());
		ret.setNotificationType("VnfPackageOnboardingNotification");
		ret.setSubscriptionId(_subscriptionId.toString());
		ret.setVnfPkgId(_vnfPkgId.toString());
		ret.setVnfdId(_vnfdId);
		ret.setLinks(links.createVnfPackageOnboardingNotificationLinks(_vnfPkgId, _subscriptionId));
		return ret;
	}
}
