package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoAdditionalArtifacts;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgInfoChecksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;

public class VnfPackageFactory {

	public static VnfPkgInfo createVnfPkgInfo(String vnfPkgId, Object userData) {
		final VnfPkgInfo vnfPkgInfo = new VnfPkgInfo();
		vnfPkgInfo.setId(vnfPkgId);
		vnfPkgInfo.setOnboardingState(OnboardingStateEnum.CREATED);
		vnfPkgInfo.setUserDefinedData(userData);
		vnfPkgInfo.setOperationalState(OperationalStateEnum.DISABLED);
		vnfPkgInfo.setUsageState(UsageStateEnum.NOT_IN_USE);

		return vnfPkgInfo;
	}

	public static VnfPackagesVnfPkgInfoAdditionalArtifacts createArtefact(String _filename, String _checksum) {
		final VnfPackagesVnfPkgInfoAdditionalArtifacts artefact = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		artefact.artifactPath(_filename);
		final VnfPackagesVnfPkgInfoChecksum checksum = new VnfPackagesVnfPkgInfoChecksum();
		checksum.algorithm("SHA-256");
		checksum.setHash(_checksum);
		artefact.setChecksum(checksum);
		return artefact;
	}

	public static VnfPackagesVnfPkgInfoAdditionalArtifacts createArtefact(String _filename, VnfPackagesVnfPkgInfoChecksum _checksum) {
		final VnfPackagesVnfPkgInfoAdditionalArtifacts artefact = new VnfPackagesVnfPkgInfoAdditionalArtifacts();
		artefact.artifactPath(_filename);
		artefact.setChecksum(_checksum);
		return artefact;
	}
}
