package com.ubiqube.etsi.mano.controller.vnf.sol005;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.etsi.mano.controller.vnf.sol003.VnfPkgSol003;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class VnfManagement {
	private static final Logger LOG = LoggerFactory.getLogger(VnfPkgSol003.class);
	protected final VnfPackageRepository vnfPackageRepository = new VnfPackageRepository();

	public VnfPkgInfo vnfPackagesVnfPkgIdGet(@PathParam("vnfPkgId") String vnfPkgId, @HeaderParam("Accept") String accept) throws ServiceException {
		final VnfPkgInfo vnfPkgInfo = getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, false);
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPkgInfo;
	}

	private VnfPkgInfo getVnfPkgIndividualInfoOrCheckOnboardingStatus(String vnfPkgId, boolean isCheckOnbordingStatus) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);

		if (isCheckOnbordingStatus) {
			final String onboardingState = vnfPkgInfo.getOnboardingState();
			if (OnboardingStateEnum.PROCESSING.toString().equalsIgnoreCase(onboardingState)
					|| OnboardingStateEnum.UPLOADING.toString().equalsIgnoreCase(onboardingState.toUpperCase())) {
				throw new ConflictException("Conflict with the current VNF Package onbording state: " + onboardingState.toUpperCase());
			}
		} else {
			return vnfPkgInfo;
		}
		return vnfPkgInfo;
	}
}
