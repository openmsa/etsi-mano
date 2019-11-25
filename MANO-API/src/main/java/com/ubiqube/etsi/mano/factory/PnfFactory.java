package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.model.nsd.sol005.CreatePnfdInfoRequest;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnboardingStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdUsageStateType;

public class PnfFactory {
	private PnfFactory() {
		// Nothing.
	}

	public static PnfdInfo createPnfDescriptorsPnfdInfo(final CreatePnfdInfoRequest query) {
		final PnfdInfo pnfd = new PnfdInfo();
		pnfd.setPnfdOnboardingState(PnfdOnboardingStateType.CREATED);
		pnfd.setPnfdUsageState(PnfdUsageStateType.NOT_IN_USE);
		pnfd.setUserDefinedData(query.getUserDefinedData());
		return pnfd;
	}
}
