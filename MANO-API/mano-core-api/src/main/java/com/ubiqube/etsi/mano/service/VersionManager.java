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
package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties;
import com.ubiqube.etsi.mano.config.properties.VnfmConnectionProperties;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;

@Service
public class VersionManager {

	private final List<VersionService> services;
	private final VersionService nfvoConn;
	private final VersionService vnfmConn;

	public VersionManager(final List<VersionService> _services, final VnfmConnectionProperties _vnfmConn, final NfvoConnectionProperties _nfvoConn) {
		services = _services;
		nfvoConn = _services.stream().filter(x -> x.getVersion().equals(_nfvoConn.getVersion())).findAny().orElse(null);
		vnfmConn = _services.stream().filter(x -> x.getVersion().equals(_vnfmConn.getVersion())).findAny().orElse(null);
	}

	public VersionService getSubscriptionService(final String event) {
		//
		return services.get(0);
	}

	public Blueprint vnfInceGet(final UUID id) {
		return vnfmConn.vnfInstanceGet(id);
	}

	public List<VnfInstance> vnfInstanceGet(final MultiValueMap<String, String> params) {
		return vnfmConn.vnfInstanceGet(params);
	}

	public VnfInstance vnfInstancePost(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		return vnfmConn.vnfInstancePost(vnfdId, vnfInstanceName, vnfInstanceDescription);
	}

	public void vnfInstanceDelete(final UUID vnfInstanceId) {
		vnfmConn.vnfInstanceDelete(vnfInstanceId);
	}

	public Blueprint vnfInstanceInstantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		return vnfmConn.vnfInstanceInstantiate(vnfInstanceId, instantiateVnfRequest);
	}

	public Blueprint vnfInstanceTerminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		return vnfmConn.vnfInstanceTerminate(vnfInstanceId, terminationType, gracefulTerminationTimeout);
	}

	public Blueprint vnfInstanceScaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		return vnfmConn.vnfInstanceScaleToLevel(uuid, scaleVnfToLevelRequest);
	}

	public Blueprint vnfInstanceScale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		return vnfmConn.vnfInstanceScale(uuid, scaleVnfRequest);
	}

	public Blueprint vnfInstanceOperate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		return vnfmConn.vnfInstanceOperate(uuid, operateVnfRequest);
	}
}
