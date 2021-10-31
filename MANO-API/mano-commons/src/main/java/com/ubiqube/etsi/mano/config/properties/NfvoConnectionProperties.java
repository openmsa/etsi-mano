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
package com.ubiqube.etsi.mano.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Connection parameters to the NFVO instance.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "mano.vnfm.nfvo")
public class NfvoConnectionProperties {
	/**
	 * NFVO url.
	 */
	private String url;
	/**
	 * NFVO protocol version IE: 2.6.1
	 */
	private String version;
	/**
	 * Basic connection information.
	 */
	private Basic basic;
	/**
	 * OAuth2 informations.
	 */
	private Oauth2 oauth2;

	@Getter
	@Setter
	public static class Basic {
		/**
		 * VNFM User name.
		 */
		private String username;
		/**
		 * VNFM password.
		 */
		private String password;

	}

	@Getter
	@Setter
	public static class Oauth2 {
		private String oauthUrl;
		private String grantType;
		private String clientId;
		private String clientSecret;
		private String username;
		private String password;
	}

}
