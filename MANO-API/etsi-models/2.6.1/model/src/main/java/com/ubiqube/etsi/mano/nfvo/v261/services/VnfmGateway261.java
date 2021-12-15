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
package com.ubiqube.etsi.mano.nfvo.v261.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.model.lcmgrant.Grant;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsLcmOpOcc;
import com.ubiqube.etsi.mano.service.HttpGateway;
import com.ubiqube.etsi.mano.service.NfvoFactory;
import com.ubiqube.etsi.mano.service.VnfmFactory;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.TerminateVnfRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmGateway261 implements HttpGateway {

	private final NfvoFactory nfvoFactory;
	private final VnfmFactory vnfmFactory;

	public VnfmGateway261(final ObjectProvider<VnfmFactory> vnfmFactory, final ObjectProvider<NfvoFactory> nfvoFactory) {
		this.vnfmFactory = vnfmFactory.getIfAvailable();
		this.nfvoFactory = nfvoFactory.getIfAvailable();
	}

	@Override
	public Class<?> getVnfPackageClass() {
		return VnfPkgInfo.class;
	}

	@Override
	public Class<?> getVnfPackageSubscriptionClass() {
		return PkgmSubscription.class;
	}

	@Override
	public Class<?> getPkgmSubscriptionRequest() {
		return PkgmSubscriptionRequest.class;
	}

	@Override
	public Class<?> getGrantRequest() {
		return GrantRequest.class;
	}

	@Override
	public Class<?> getGrantResponse() {
		return Grant.class;
	}

	@Override
	public void makeGrantLinks(final Object manoGrant) {
		if (manoGrant instanceof final GrantRequest grant) {
			vnfmFactory.makeGrantRequestLink(grant);
		}
	}

	@Override
	public String getUrlFor(final ApiVersionType type) {
		switch (type) {
		case SOL003_VNFFM:
			return "vnffm/v1/";
		case SOL003_VNFIND:
			return "vnfind/v1/";
		case SOL003_VNFPM:
			return "vnfpm/v1/";
		case SOL003_VNFSNAPSHOTPKGM:
			return "vnfsnapshotpkgm/v1/";
		case SOL003_VNFLCM:
			return "vnflcm/v1/";
		case SOL003_VRQAN:
			return "vrqan/v1/";
		case SOL003_GRANT:
			return "grant/v1/";
		case SOL003_VNFPKGM:
			return "vnfpkgm/v1/";
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

	@Override
	public Class<?> getVnfInstanceClass() {
		return VnfInstance.class;
	}

	@Override
	public ParameterizedTypeReference<List<?>> getVnfInstanceListParam() {
		final ParameterizedTypeReference<List<VnfInstance>> res = new ParameterizedTypeReference<>() {
			// Nothing.
		};
		return (ParameterizedTypeReference<List<?>>) (Object) res;
	}

	@Override
	public Object createVnfInstanceRequest(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		final var req = new CreateVnfRequest();
		req.setVnfdId(vnfdId);
		req.setVnfInstanceDescription(vnfInstanceDescription);
		req.setVnfInstanceName(vnfInstanceName);
		return req;
	}

	@Override
	public Class<?> getVnfInstanceInstantiateRequestClass() {
		return InstantiateVnfRequest.class;
	}

	@Override
	public Class<?> getVnfLcmOpOccs() {
		return NsLcmOpOcc.class;
	}

	@Override
	public Object createVnfInstanceTerminate(final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		return TerminateVnfRequest.class;
	}

	@Override
	public Class<?> getVnfInstanceScaleToLevelRequest() {
		return ScaleVnfToLevelRequest.class;
	}

	@Override
	public Class<?> getVnfInstanceScaleRequest() {
		return ScaleVnfRequest.class;
	}

	@Override
	public Class<?> getVnfInstanceOperateRequest() {
		return OperateVnfRequest.class;
	}

	@Override
	public Class<?> getVnfInstanceChangeExtConnRequest() {
		return ChangeExtVnfConnectivityRequest.class;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		return nfvoFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId);
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		return nfvoFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId);
	}

	@Override
	public Object createNotificationVnfIdentifierCreationNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNotificationVnfIdentifierDeletionNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNotificationVnfLcmOperationOccurrenceNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}
}
