package com.ubiqube.etsi.mano.factory;

import java.util.ArrayList;

import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOnboardingStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdOperationalStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinks;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfoLinksSelf;

public class NsdFactories {

	private NsdFactories() {
		// Nothing.
	}

	public static NsDescriptorsNsdInfoIdGetResponse createNsDescriptorsNsdInfoIdGetResponse(String _id, String _self, String _nsdContent) {
		final NsDescriptorsNsdInfoIdGetResponse ret = new NsDescriptorsNsdInfoIdGetResponse();
		final NsDescriptorsNsdInfo nsdInfo = createNsDescriptorsNsdInfo(_id, _self, _nsdContent);
		ret.setNsdInfo(nsdInfo);
		return ret;
	}

	public static NsDescriptorsNsdInfoLinks createNsDescriptorsNsdInfoLinks(String _self, String _nsdContent) {
		final NsDescriptorsNsdInfoLinks ret = new NsDescriptorsNsdInfoLinks();
		final NsDescriptorsNsdInfoLinksSelf nsdContent = new NsDescriptorsNsdInfoLinksSelf();
		nsdContent.setHref(_nsdContent);
		ret.setNsdContent(nsdContent);
		final NsDescriptorsNsdInfoLinksSelf self = new NsDescriptorsNsdInfoLinksSelf();
		self.setHref(_self);
		ret.setSelf(self);

		return ret;
	}

	public static NsDescriptorsNsdInfo createNsDescriptorsNsdInfo(String _id) {
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

}
