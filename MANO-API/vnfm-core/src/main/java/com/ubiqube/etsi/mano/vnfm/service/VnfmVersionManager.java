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
package com.ubiqube.etsi.mano.vnfm.service;

import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UriComponents;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.VnfmGateway;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;
import com.ubiqube.etsi.mano.vnfm.service.rest.NfvoRestImpl;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfmVersionManager {
	private final List<VnfmGateway> vnfmGateway;
	private final NfvoRest vnfmRest;
	private final MapperFacade mapper;
	private final Environment env;
	private final ManoProperties manoProperties;

	public VnfmVersionManager(final List<VnfmGateway> vnfmGateway, final NfvoRestImpl vnfmRest, final MapperFacade mapper, final Environment env, final ManoProperties manoProperties) {
		super();
		this.vnfmGateway = vnfmGateway;
		this.vnfmRest = vnfmRest;
		this.mapper = mapper;
		this.env = env;
		this.manoProperties = manoProperties;
		if (vnfmGateway.isEmpty()) {
			throw new GenericException("No VNFM gateway found. At leat onr gateway is needed.");
		}
	}

	public VnfPackage findVnfPkgById(final String pkgId) {
		final Class<?> clazz = vnfmGateway.get(0).getVnfPackageClass();
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = vnfmRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{id}")
				.buildAndExpand(uriVariables)
				.toUri();
		final Object res = vnfmRest.get(uri, clazz);
		return mapper.map(res, VnfPackage.class);
	}

	public void getPackageContent(final String pkgId, final Path file) {
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = vnfmRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{id}/package_content")
				.buildAndExpand(uriVariables)
				.toUri();
		vnfmRest.get(uri, clientHttpResponse -> StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(file.toFile())));
	}

	public Subscription subscribe(final Subscription subscription) {
		subscription.setApi(ApiTypesEnum.SOL003);
		final AuthentificationInformations auth = new AuthentificationInformations();
		final AuthParamOauth2 oauth2 = new AuthParamOauth2();
		oauth2.setClientId(env.getProperty("keycloak.resource"));
		oauth2.setClientSecret(env.getProperty("keycloak.credentials.secret"));
		oauth2.setTokenEndpoint(env.getProperty("mano.swagger-o-auth2"));
		auth.setAuthParamOath2(oauth2);
		auth.setAuthType(List.of(AuthType.OAUTH2_CLIENT_CREDENTIALS));
		subscription.setAuthentication(auth);
		subscription.setCallbackUri(manoProperties.getFrontendUrl() + "/vnfpkgm/v1/notification/onboarding");
		subscription.setSubscriptionType(SubscriptionType.NSDVNF);
		final UriComponents uri = vnfmRest.uriBuilder().pathSegment("vnfpkgm/v1/subscriptions").build();
		final Class<?> clazz = vnfmGateway.get(0).getVnfPackageSubscriptionClass();
		final Class<?> clazzWire = vnfmGateway.get(0).getPkgmSubscriptionRequest();
		final Object wire = mapper.map(subscription, clazzWire);
		final Object res = vnfmRest.post(uri.toUri(), wire, clazz);
		return mapper.map(res, Subscription.class);
	}

}
