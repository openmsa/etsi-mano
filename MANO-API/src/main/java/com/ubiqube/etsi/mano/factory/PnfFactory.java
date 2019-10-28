package com.ubiqube.etsi.mano.factory;

import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo.PnfdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo.PnfdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPostQuery;

public class PnfFactory {
	private PnfFactory() {
		// Nothing.
	}

	public static PnfDescriptorsPnfdInfo createPnfDescriptorsPnfdInfo(final PnfDescriptorsPostQuery query) {
		final PnfDescriptorsPnfdInfo pnfd = new PnfDescriptorsPnfdInfo();
		pnfd.setPnfdOnboardingState(PnfdOnboardingStateEnum.CREATED);
		pnfd.setPnfdUsageState(PnfdUsageStateEnum.NOT_IN_USE);
		pnfd.setUserDefinedData(query.getCreatePnfdInfoRequest().getUserDefinedData());
		return pnfd;
	}
}
