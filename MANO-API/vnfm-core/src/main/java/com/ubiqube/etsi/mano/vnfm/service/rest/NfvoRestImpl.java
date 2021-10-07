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
package com.ubiqube.etsi.mano.vnfm.service.rest;
import java.util.Optional;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties.Basic;
import com.ubiqube.etsi.mano.config.properties.NfvoConnectionProperties.Oauth2;
import com.ubiqube.etsi.mano.service.rest.AbstractRest;

/**
 * HTTP way to exchange with NFVO.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoRestImpl extends AbstractRest {
	private final String url;
	private final MultiValueMap<String, String> auth = new LinkedMultiValueMap<>();

	public NfvoRestImpl(final NfvoConnectionProperties props) {
		url = props.getUrl();
		if (null != props.getOauth2()) {
			final Oauth2 oauth = props.getOauth2();
			final var resource = new ResourceOwnerPasswordResourceDetails();
			resource.setClientId(oauth.getClientId());
			resource.setClientSecret(oauth.getClientSecret());
			resource.setAccessTokenUri(oauth.getOauthUrl());
			resource.setUsername(oauth.getUsername());
			resource.setPassword(oauth.getPassword());
			final var oauth2 = new OAuth2RestTemplate(resource);
			setRestTemplate(oauth2);
		}
		if (props.getBasic() != null) {
			final Basic basic = props.getBasic();
			final String user = basic.getUsername();
			if (null != user) {
				final String password = Optional.of(basic.getPassword()).orElse("");
				auth.add("Authorization", authBasic(user, password));
			}
		}
		Assert.notNull(url, "nfvo.url is not declared in property file.");
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	public MultiValueMap<String, String> getAutorization() {
		return auth;
	}

}
