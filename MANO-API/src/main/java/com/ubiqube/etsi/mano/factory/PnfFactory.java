package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.CreatePnfdInfoRequest;

public class PnfFactory {
	private PnfFactory() {
		// Nothing.
	}

	public static PnfDescriptor createPnfDescriptorsPnfdInfo(final CreatePnfdInfoRequest query) {
		final PnfDescriptor pnfd = new PnfDescriptor();
		pnfd.setPnfdOnboardingState(OnboardingStateType.CREATED);
		pnfd.setPnfdUsageState(PackageUsageState.NOT_IN_USE);
		return pnfd;
	}
}
