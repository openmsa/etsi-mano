package com.ubiqube.etsi.mano.factory;

import java.util.ArrayList;

import com.ubiqube.etsi.mano.model.Link;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfoLinks;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdOperationalStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdOnboardingStateType;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfdUsageStateType;

public final class NsdFactories {

	private NsdFactories() {
		// Nothing.
	}

	public static NsdInfoLinks createNsdInfoLinks(final String _self, final String _nsdContent) {
		final NsdInfoLinks ret = new NsdInfoLinks();
		final Link nsdContent = new Link();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);
		final Link self = new Link();
		self.setHref(_self);
		ret.setSelf(self);

		return ret;
	}

	public static NsdInfo createNsdInfo() {
		final NsdInfo nsdInfo = new NsdInfo();
		nsdInfo.setNestedNsdInfoIds(new ArrayList<String>());
		nsdInfo.setNsdOnboardingState(NsdOnboardingStateType.CREATED);
		nsdInfo.setNsdOperationalState(NsdOperationalStateType.DISABLED);
		nsdInfo.setNsdUsageState(NsdUsageStateType.NOT_IN_USE);
		nsdInfo.setPnfdInfoIds(new ArrayList<String>());
		nsdInfo.setVnfPkgIds(new ArrayList<String>());
		return nsdInfo;
	}

	public static PnfdInfo createPnfDescriptorsPnfdInfo(final String _id) {
		final PnfdInfo pnfDescriptorsPnfdInfo = new PnfdInfo();
		pnfDescriptorsPnfdInfo.setId(_id);
		pnfDescriptorsPnfdInfo.setPnfdOnboardingState(PnfdOnboardingStateType.CREATED);
		pnfDescriptorsPnfdInfo.setPnfdUsageState(PnfdUsageStateType.NOT_IN_USE);
		return pnfDescriptorsPnfdInfo;
	}
}
