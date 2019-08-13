package com.ubiqube.etsi.mano.factory;

import java.util.ArrayList;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinks;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinksSelf;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo.PnfdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo.PnfdUsageStateEnum;

public class NsdFactories {

	private NsdFactories() {
		// Nothing.
	}

	public static NsDescriptorsNsdInfoIdGetResponse createNsDescriptorsNsdInfoIdGetResponse(final String _id) {
		final NsDescriptorsNsdInfoIdGetResponse ret = new NsDescriptorsNsdInfoIdGetResponse();
		final NsDescriptorsNsdInfo nsdInfo = createNsDescriptorsNsdInfo(_id);
		ret.setNsdInfo(nsdInfo);
		return ret;
	}

	public static NsDescriptorsNsdInfoLinks createNsDescriptorsNsdInfoLinks(final String _self, final String _nsdContent) {
		final NsDescriptorsNsdInfoLinks ret = new NsDescriptorsNsdInfoLinks();
		final NsDescriptorsNsdInfoLinksSelf nsdContent = new NsDescriptorsNsdInfoLinksSelf();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);
		final NsDescriptorsNsdInfoLinksSelf self = new NsDescriptorsNsdInfoLinksSelf();
		self.setHref(_self);
		ret.setSelf(self);

		return ret;
	}

	public static NsDescriptorsNsdInfo createNsDescriptorsNsdInfo(final String _id) {
		final NsDescriptorsNsdInfo nsdInfo = new NsDescriptorsNsdInfo();
		nsdInfo.setNestedNsdInfoIds(new ArrayList<String>());
		nsdInfo.setNsdOnboardingState(NsdOnboardingStateEnum.CREATED);
		nsdInfo.setNsdOperationalState(NsdOperationalStateEnum.ENABLED);
		nsdInfo.setNsdUsageState(NsdUsageStateEnum.NOT_IN_USE);
		nsdInfo.setPnfdInfoIds(new ArrayList<String>());
		nsdInfo.setVnfPkgIds(new ArrayList<String>());
		nsdInfo.setId(_id);
		return nsdInfo;
	}

	public static PnfDescriptorsPnfdInfo createPnfDescriptorsPnfdInfo(final String _id) {
		final PnfDescriptorsPnfdInfo pnfDescriptorsPnfdInfo = new PnfDescriptorsPnfdInfo();
		pnfDescriptorsPnfdInfo.setId(_id);
		pnfDescriptorsPnfdInfo.setPnfdOnboardingState(PnfdOnboardingStateEnum.CREATED);
		pnfDescriptorsPnfdInfo.setPnfdUsageState(PnfdUsageStateEnum.NOT_IN_USE);
		return pnfDescriptorsPnfdInfo;
	}
}
