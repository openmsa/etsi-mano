/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
