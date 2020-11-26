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

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.common.v261.VnfSubscriptionFactory;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.model.VnfInstantiate;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mano.model.VnfScaleRequest;
import com.ubiqube.etsi.mano.model.VnfScaleToLevelRequest;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.Sol005Linkable;
import com.ubiqube.etsi.mano.service.VersionService;
import com.ubiqube.etsi.mano.service.rest.VnfmRest;

import ma.glasnost.orika.MapperFacade;

public class Nfvo261VersionService implements VersionService {

	private final VnfmRest vnfmRest;
	private final MapperFacade mapper;

	public Nfvo261VersionService(final VnfmRest _vnfmRest, final MapperFacade _mapper) {
		vnfmRest = _vnfmRest;
		mapper = _mapper;
	}

	@Override
	public String getVersion() {
		return "2.6.1";
	}

	@Override
	public boolean isNfvo() {
		return true;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final VnfPackageOnboardingNotification obj = VnfSubscriptionFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId, null, null);
		obj.setLinks(new Sol005Linkable().createVnfPackageOnboardingNotificationLinks(vnfPkgId, subscriptionId));
		return obj;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final VnfPackageChangeNotification obj = VnfSubscriptionFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId, null, null);
		obj.setLinks(new Sol005Linkable().createNotificationLink(vnfPkgId, subscriptionId));
		return obj;
	}

	@Override
	public Blueprint vnfInstanceGet(final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VnfInstance> vnfInstanceGet(final MultiValueMap<String, String> params) {
		final URI uri = vnfmRest.uriBuilder()
				.pathSegment("vnflcm/v1/vnf_instances")
				.queryParams(params)
				.build()
				.toUri();
		final ParameterizedTypeReference<List<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance>> myBean = new ParameterizedTypeReference<>() {
		};
		final List<com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance> res = vnfmRest.get(uri, myBean);
		return res.stream().map(x -> mapper.map(x, VnfInstance.class)).collect(Collectors.toList());
	}

	@Override
	public VnfInstance vnfInstancePost(final String vnfdId, final String vnfInstanceName, final String vnfInstanceDescription) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint vnfInstanceOperate(final UUID uuid, final VnfOperateRequest operateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint vnfInstanceScale(final UUID uuid, final VnfScaleRequest scaleVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint vnfInstanceScaleToLevel(final UUID uuid, final VnfScaleToLevelRequest scaleVnfToLevelRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint vnfInstanceTerminate(final UUID vnfInstanceId, final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blueprint vnfInstanceInstantiate(final UUID vnfInstanceId, final VnfInstantiate instantiateVnfRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void vnfInstanceDelete(final UUID vnfInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<VnfInstance> vnfInstanceGet(final Map<String, String> queryParameters) {

		return null;
	}

}
