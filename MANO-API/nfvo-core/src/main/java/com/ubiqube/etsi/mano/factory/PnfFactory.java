package com.ubiqube.etsi.mano.factory;

import java.util.Map;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageUsageState;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;

public class PnfFactory {
	private PnfFactory() {
		// Nothing.
	}

	@Nonnull
	public static PnfDescriptor createPnfDescriptorsPnfdInfo(final Map<String, Object> userDefinedData) {
		final PnfDescriptor pnfd = new PnfDescriptor();
		pnfd.setPnfdOnboardingState(OnboardingStateType.CREATED);
		pnfd.setPnfdUsageState(PackageUsageState.NOT_IN_USE);
		return pnfd;
	}
}
