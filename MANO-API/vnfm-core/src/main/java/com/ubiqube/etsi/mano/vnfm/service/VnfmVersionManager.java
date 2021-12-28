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

import java.nio.file.Path;
import java.util.List;
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
import com.ubiqube.etsi.mano.service.rest.ManoClientFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfmVersionManager {
	private final Environment env;
	private final ManoProperties manoProperties;
	private final ManoClientFactory manoClientFactory;

	public VnfmVersionManager(final Environment env, final ManoProperties manoProperties, final ManoClientFactory manoClientFactory) {
		super();
		this.env = env;
		this.manoProperties = manoProperties;
		this.manoClientFactory = manoClientFactory;
	}

	public VnfPackage findVnfPkgById(final String pkgId) {
		return manoClientFactory.getClient()
				.vnfPackage(UUID.fromString(pkgId))
				.find();
	}

	public void getPackageContent(final String pkgId, final Path file) {
		manoClientFactory.getClient()
				.vnfPackage(UUID.fromString(pkgId))
				.downloadContent(file);
	}

	public Subscription subscribe(final Subscription subscription) {
		subscription.setApi(ApiTypesEnum.SOL003);
		final AuthentificationInformations auth = createAuthInformation();
		subscription.setAuthentication(auth);
		subscription.setCallbackUri(manoProperties.getFrontendUrl() + "/vnfpkgm/v1/notification/onboarding");
		return manoClientFactory.getClient()
				.vnfPackage()
				.subscribe(subscription);
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
