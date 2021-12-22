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
package com.ubiqube.etsi.mano.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.config.properties.ManoProperties;

import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;

@Service
public class KeycloakAuth implements SecutiryConfig {

	private final Http403EntryPoint http403EntryPoint;

	public KeycloakAuth(final Http403EntryPoint http403EntryPoint) {
		super();
		this.http403EntryPoint = http403EntryPoint;
	}

	/**
	 * All request must be authenticated, No login page.
	 */
	@Override
	public void configure(final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry http) throws Exception {
		http.and()
				.oauth2ResourceServer()
				.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
				.authenticationEntryPoint(http403EntryPoint);
	}

	private static Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
		final JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
		return jwtConverter;
	}

	@Override
	public SecurityScheme getSwaggerSecurityScheme(final ManoProperties oauth2Params) {
		final OAuthFlow clientCredential = new OAuthFlow().authorizationUrl(oauth2Params.getSwaggerOAuth2())
				.tokenUrl(oauth2Params.getSwaggerOAuth2());
		final OAuthFlows flows = new OAuthFlows().clientCredentials(clientCredential);
		return new SecurityScheme()
				.type(SecurityScheme.Type.OAUTH2)
				.scheme("bearer")
				.bearerFormat("JWT")
				.in(In.HEADER)
				.name("Authorization")
				.flows(flows);
	}

}
