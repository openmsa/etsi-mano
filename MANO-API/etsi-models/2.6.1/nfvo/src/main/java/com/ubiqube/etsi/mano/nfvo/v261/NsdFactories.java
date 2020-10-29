/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.ArrayList;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.NsdOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdOperationalStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdOnboardingStateType;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdUsageStateType;

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
		nsdInfo.setNestedNsdInfoIds(new ArrayList<>());
		nsdInfo.setNsdOnboardingState(NsdOnboardingStateType.CREATED);
		nsdInfo.setNsdOperationalState(NsdOperationalStateType.DISABLED);
		nsdInfo.setNsdUsageState(NsdUsageStateType.NOT_IN_USE);
		nsdInfo.setPnfdInfoIds(new ArrayList<>());
		nsdInfo.setVnfPkgIds(new ArrayList<>());
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
