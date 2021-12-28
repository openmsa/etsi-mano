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
package com.ubiqube.etsi.mano.service.rest;

import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.vnfi.ChangeExtVnfConnRequest;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.service.HttpGateway;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoVnfInstanceId {

	private final ManoClient client;

	public ManoVnfInstanceId(final ManoClient manoClient, final UUID vnfInstanceId) {
		this.client = manoClient;
		manoClient.setQueryType(ApiVersionType.SOL003_VNFLCM);
		manoClient.setObjectId(vnfInstanceId);
		manoClient.setFragment("vnf_instances/{id}");
	}

	public VnfBlueprint instantiate(final VnfInstantiate instantiateVnfRequest) {
		client.setFragment("vnf_instances/{id}/instantiate");
		return client.createQuery()
				.setWireInClass(HttpGateway::getVnfInstanceInstantiateRequestClass)
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post(instantiateVnfRequest);
	}

	public void delete() {
		client.createQuery()
				.delete();
	}

	public VnfBlueprint terminate(final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		client.setFragment("vnf_instances/{id}/terminate");
		return client.createQuery(httpGateway -> httpGateway.createVnfInstanceTerminate(terminationType, gracefulTerminationTimeout))
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post();
	}

	public VnfBlueprint scaleToLevel(final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		client.setFragment("vnf_instances/{id}/scale_to_level");
		return client.createQuery()
				.setWireInClass(HttpGateway::getVnfInstanceScaleToLevelRequest)
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post(scaleVnfToLevelRequest);

	}

	public VnfBlueprint scale(final VnfScaleRequest scaleVnfRequest) {
		client.setFragment("vnf_instances/{id}/scale");
		return client.createQuery()
				.setWireInClass(HttpGateway::getVnfInstanceScaleRequest)
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post(scaleVnfRequest);
	}

	public VnfBlueprint operate(final VnfOperateRequest operateVnfRequest) {
		client.setFragment("vnf_instances/{id}/operate");
		return client.createQuery()
				.setWireInClass(HttpGateway::getVnfInstanceOperateRequest)
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post(operateVnfRequest);
	}

	public VnfBlueprint changeExtConn(final ChangeExtVnfConnRequest cevcr) {
		client.setFragment("vnf_instances/{id}/change_ext_conn");
		return client.createQuery()
				.setWireInClass(HttpGateway::getVnfInstanceChangeExtConnRequest)
				.setWireOutClass(HttpGateway::getVnfLcmOpOccs)
				.setOutClass(VnfBlueprint.class)
				.post(cevcr);
	}

	public VnfInstance find() {
		return client.createQuery()
				.setWireOutClass(HttpGateway::getVnfInstanceClass)
				.setOutClass(VnfInstance.class)
				.getSingle();
	}

}
