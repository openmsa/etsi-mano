package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.common.v261.model.vnf.Checksum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

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

}
