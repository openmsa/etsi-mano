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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class SubscriptionsPmSubscriptionRequestAuthentication {

	@XmlType(name = "AuthTypeEnum")
	@XmlEnum(String.class)
	public enum AuthTypeEnum {

		@XmlEnumValue("BASIC")
		BASIC(String.valueOf("BASIC")), @XmlEnumValue("OAUTH2_CLIENT_CREDENTIALS")
		OAUTH2_CLIENT_CREDENTIALS(String.valueOf("OAUTH2_CLIENT_CREDENTIALS")), @XmlEnumValue("TLS_CERT")
		TLS_CERT(String.valueOf("TLS_CERT"));

		private final String value;

		AuthTypeEnum(final String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		public static AuthTypeEnum fromValue(final String v) {
			for (final AuthTypeEnum b : AuthTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	@Schema(required = true, description = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
	/**
	 * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent
	 * over a mutually authenticated TLS session, i.e. not only the server is authenticated, but also the client is authenticated during the TLS tunnel setup.
	 **/
	private List<AuthTypeEnum> authType = new ArrayList<>();

	@Schema(description = "")
	@Valid
	private SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic = null;

	@Schema(description = "")
	@Valid
	private SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

	/**
	 * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent
	 * over a mutually authenticated TLS session, i.e. not only the server is authenticated, but also the client is authenticated during the TLS tunnel setup.
	 *
	 * @return authType
	 **/
	@JsonProperty("authType")
	@NotNull
	public List<AuthTypeEnum> getAuthType() {
		return authType;
	}

	public void setAuthType(final List<AuthTypeEnum> authType) {
		this.authType = authType;
	}

	public SubscriptionsPmSubscriptionRequestAuthentication authType(final List<AuthTypeEnum> authType) {
		this.authType = authType;
		return this;
	}

	public SubscriptionsPmSubscriptionRequestAuthentication addAuthTypeItem(final AuthTypeEnum authTypeItem) {
		this.authType.add(authTypeItem);
		return this;
	}

	/**
	 * Get paramsBasic
	 *
	 * @return paramsBasic
	 **/
	@JsonProperty("paramsBasic")
	public SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic getParamsBasic() {
		return paramsBasic;
	}

	public void setParamsBasic(final SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
	}

	public SubscriptionsPmSubscriptionRequestAuthentication paramsBasic(final SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
		return this;
	}

	/**
	 * Get paramsOauth2ClientCredentials
	 *
	 * @return paramsOauth2ClientCredentials
	 **/
	@JsonProperty("paramsOauth2ClientCredentials")
	public SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
		return paramsOauth2ClientCredentials;
	}

	public void setParamsOauth2ClientCredentials(final SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
	}

	public SubscriptionsPmSubscriptionRequestAuthentication paramsOauth2ClientCredentials(final SubscriptionsPmSubscriptionRequestAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsPmSubscriptionRequestAuthentication {\n");

		sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
		sb.append("    paramsBasic: ").append(toIndentedString(paramsBasic)).append("\n");
		sb.append("    paramsOauth2ClientCredentials: ").append(toIndentedString(paramsOauth2ClientCredentials)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
