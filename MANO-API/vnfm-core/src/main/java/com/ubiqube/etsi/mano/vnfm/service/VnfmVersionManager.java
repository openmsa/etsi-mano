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

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.ServerService;
import com.ubiqube.etsi.mano.service.rest.ServerAdapter;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmVersionManager {
	private final MapperFacade mapper;
	private final Environment env;
	private final ManoProperties manoProperties;
	private final ServerService serverService;

	public VnfmVersionManager(final MapperFacade mapper, final Environment env, final ManoProperties manoProperties, final ServerService serverService) {
		super();
		this.mapper = mapper;
		this.env = env;
		this.manoProperties = manoProperties;
		this.serverService = serverService;
	}

	public VnfPackage findVnfPkgById(final String pkgId) {
		final ServerAdapter server = serverService.findNearestServer();
		final Class<?> clazz = server.httpGateway().getVnfPackageClass();
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = server.getUriFor(ApiVersionType.SOL003_VNFPKGM, "/vnf_packages/{id}", uriVariables);
		final Object res = server.rest().get(uri, clazz);
		return mapper.map(res, VnfPackage.class);
	}

	public void getPackageContent(final String pkgId, final Path file) {
		final ServerAdapter server = serverService.findNearestServer();
		final Map<String, Object> uriVariables = Map.of("id", pkgId);
		final URI uri = server.getUriFor(ApiVersionType.SOL003_VNFPKGM, "/vnf_packages/{id}/package_content", uriVariables);
		server.rest().download(uri, file);
	}

	public Subscription subscribe(final Subscription subscription) {
		final ServerAdapter server = serverService.findNearestServer();
		subscription.setApi(ApiTypesEnum.SOL003);
		final AuthentificationInformations auth = createAuthInformation();
		subscription.setAuthentication(auth);
		subscription.setCallbackUri(manoProperties.getFrontendUrl() + "/vnfpkgm/v1/notification/onboarding");
		subscription.setSubscriptionType(SubscriptionType.NSDVNF);
		final URI uri = server.getUriFor(ApiVersionType.SOL003_VNFPKGM, "/subscriptions", Map.of());
		final Class<?> clazz = server.httpGateway().getVnfPackageSubscriptionClass();
		final Class<?> clazzWire = server.httpGateway().getPkgmSubscriptionRequest();
		final Object wire = mapper.map(subscription, clazzWire);
		final Object res = server.rest().post(uri, wire, clazz);
		return mapper.map(res, Subscription.class);
	}

	private AuthentificationInformations createAuthInformation() {
		final AuthentificationInformations auth = new AuthentificationInformations();
		if (null != env.getProperty("keycloak.resource")) {
			final AuthParamOauth2 oauth2 = new AuthParamOauth2();
			oauth2.setClientId(env.getProperty("keycloak.resource"));
			oauth2.setClientSecret(env.getProperty("keycloak.credentials.secret"));
			oauth2.setTokenEndpoint(env.getProperty("mano.swagger-o-auth2"));
			auth.setAuthParamOath2(oauth2);
			auth.setAuthType(List.of(AuthType.OAUTH2_CLIENT_CREDENTIALS));
		} else {
			final AuthParamBasic basic = new AuthParamBasic();
			basic.setPassword(UUID.randomUUID().toString());
			basic.setUserName("nfvo");
			auth.setAuthType(List.of(AuthType.BASIC));
		}
		return auth;
	}

}
