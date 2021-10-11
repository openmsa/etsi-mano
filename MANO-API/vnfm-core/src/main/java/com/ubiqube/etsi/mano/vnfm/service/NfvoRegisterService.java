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

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties.Oauth2;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;

@Service
public class NfvoRegisterService {
	NfvoConnectionProperties nfvoConnectionProperties;
	Environment env;
	ManoProperties manoProperties;

	public void register() {
		final Oauth2 nfvoOauth2 = nfvoConnectionProperties.getOauth2();
		nfvoConnectionProperties.getUrl();
		final Subscription subscription = new Subscription();
		subscription.setApi(ApiTypesEnum.SOL003);
		final AuthentificationInformations auth = new AuthentificationInformations();
		final AuthParamOauth2 oauth2 = new AuthParamOauth2();
		oauth2.setClientId(env.getProperty("keycloak.resource"));
		oauth2.setClientSecret(env.getProperty("keycloak.credentials.99373315-d8d2-495d-a45d-73aa8cfcd580"));
		oauth2.setTokenEndpoint(env.getProperty("keycloak.auth-server-url"));
		auth.setAuthParamOath2(oauth2);
		auth.setAuthType(AuthType.OAUTH2_CLIENT_CREDENTIALS);
		subscription.setAuthentication(auth);
		subscription.setCallbackUri(manoProperties.getFrontendUrl() + "ubi-etsi-mano/sol003/vnfpkgm/v1/notification/onboarding");
		subscription.setSubscriptionType(SubscriptionType.NSDVNF);
	}
}
