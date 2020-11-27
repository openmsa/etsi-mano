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
package com.ubiqube.etsi.mano.nfvo.v271;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.service.VersionService;

public class Nfvo271VersionService implements VersionService {

	@Override
	public String getVersion() {
		return "2.7.1";
	}

	@Override
	public boolean isNfvo() {
		return true;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfInstance> vnfInstanceGet(final MultiValueMap<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfInstance vnfInstancePost(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceOperate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceScale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceScaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceTerminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VnfBlueprint vnfInstanceInstantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void vnfInstanceDelete(final UUID vnfInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public VnfBlueprint vnfLcmOpOccsGet(@NotNull final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
