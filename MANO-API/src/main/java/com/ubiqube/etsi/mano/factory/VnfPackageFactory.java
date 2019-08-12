package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoAdditionalArtifacts;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

public class VnfPackageFactory {

	public static VnfPkgInfo createVnfPkgInfo(final String vnfPkgId, final Object userData) {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		vnfPkgInfo.setId(vnfPkgId);
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.ENABLED);
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
}
