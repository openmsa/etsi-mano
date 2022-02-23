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
package com.ubiqube.etsi.mano.v271.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.CancelModeTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.GrantInterface;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.CreateVnfRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.Link;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.OperateVnfRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.ScaleVnfRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.TerminateVnfRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.VnfInstance;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.Grant;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.GrantRequestLinks;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.CreateVnfPkgInfoRequest;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.PkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.v271.sol005.nsd.CreateNsdInfoRequest;
import com.ubiqube.etsi.mano.model.v271.sol005.nsd.NsdInfo;
import com.ubiqube.etsi.mano.service.AbstractHttpGateway;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class HttpGateway271 extends AbstractHttpGateway {
	private final NfvoFactory nfvoFactory;
	private final VnfmFactory vnfmFactory;
	private final MapperFacade mapper;

	public HttpGateway271(final ObjectProvider<VnfmFactory> vnfmFactory, final ObjectProvider<NfvoFactory> nfvoFactory, final MapperFacade mapper) {
		this.vnfmFactory = vnfmFactory.getIfAvailable();
		this.nfvoFactory = nfvoFactory.getIfAvailable();
		this.mapper = mapper;
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
	public Class<?> getVnfInstanceClass() {
		return VnfInstance.class;
	}

	@Override
	public ParameterizedTypeReference<List<Class<?>>> getVnfInstanceListParam() {
		final ParameterizedTypeReference<List<VnfInstance>> res = new ParameterizedTypeReference<>() {
			// Nothing.
		};
		return (ParameterizedTypeReference<List<Class<?>>>) (Object) res;
	}

	@Override
	public ParameterizedTypeReference<List<Class<?>>> getListVnfLcmOpOccs() {
		final ParameterizedTypeReference<List<VnfLcmOpOcc>> res = new ParameterizedTypeReference<>() {
			// Nothing.
		};
		return (ParameterizedTypeReference<List<Class<?>>>) (Object) res;
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
		return VnfLcmOpOcc.class;
	}

	@Override
	public Object createVnfInstanceTerminate(final CancelModeTypeEnum terminationType, final Integer gracefulTerminationTimeout) {
		final TerminateVnfRequest ret = new TerminateVnfRequest();
		ret.setTerminationType(TerminationTypeEnum.fromValue(terminationType.toString()));
		ret.setGracefulTerminationTimeout(gracefulTerminationTimeout);
		return ret;
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

	@Override
	public ParameterizedTypeReference<List<Class<?>>> getVnfPackageClassList() {
		final ParameterizedTypeReference<List<VnfPackage>> res = new ParameterizedTypeReference<>() {
			// Nothing.
		};
		return (ParameterizedTypeReference<List<Class<?>>>) (Object) res;
	}

	@Override
	public String getVersion() {
		return "2.7.1";
	}

	@Override
	public Object createVnfPackageRequest(final Map<String, String> userDefinedData) {
		final CreateVnfPkgInfoRequest req = new CreateVnfPkgInfoRequest();
		req.setUserDefinedData(userDefinedData);
		return req;
	}

	@Override
	public ParameterizedTypeReference<List<Class<?>>> getNsdPackageClassList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> getNsdPackageClass() {
		return NsdInfo.class;
	}

	@Override
	public Object createNsdPackageRequest(final Map<String, Object> userDefinedData) {
		final CreateNsdInfoRequest req = new CreateNsdInfoRequest();
		req.setUserDefinedData(userDefinedData.entrySet().stream().map(x -> Map.entry(x.getKey(), x.getValue().toString())).collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
		return req;
	}

	@Override
	public Object createGrantRequest(final GrantInterface grant) {
		final GrantRequest g = mapper.map(grant, GrantRequest.class);
		final GrantRequestLinks links = new GrantRequestLinks();
		final Link vnfLink = new Link();
		vnfLink.setHref("http://");
		links.setVnfInstance(vnfLink);
		links.setVnfLcmOpOcc(vnfLink);
		g.setLinks(links);
		return g;
	}

}
