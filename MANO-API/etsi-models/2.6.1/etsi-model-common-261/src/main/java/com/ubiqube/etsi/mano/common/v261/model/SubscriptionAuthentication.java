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

package com.ubiqube.etsi.mano.common.v261.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * SubscriptionAuthentication
 */
@Validated
public class SubscriptionAuthentication {
	/**
	 * Gets or Sets authType
	 */
	public enum AuthTypeEnum {
		BASIC("BASIC"),

		OAUTH2_CLIENT_CREDENTIALS("OAUTH2_CLIENT_CREDENTIALS"),

		TLS_CERT("TLS_CERT");

		private final String value;

		AuthTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AuthTypeEnum fromValue(final String text) {
			for (final AuthTypeEnum b : AuthTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("authType")
	@Valid
	private List<AuthTypeEnum> authType = new ArrayList<>();

	@JsonProperty("paramsBasic")
	private SubscriptionAuthenticationParamsBasic paramsBasic = null;

	@JsonProperty("paramsOauth2ClientCredentials")
	private SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

	public SubscriptionAuthentication authType(final List<AuthTypeEnum> authType) {
		this.authType = authType;
		return this;
	}

	public SubscriptionAuthentication addAuthTypeItem(final AuthTypeEnum authTypeItem) {
		this.authType.add(authTypeItem);
		return this;
	}

	/**
	 * Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer token, obtained using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent
	 * over a mutually authenticated TLS session, i.e. not only the server is authenticated, but also the client is authenticated during the TLS tunnel setup.
	 *
	 * @return authType
	 **/
	@Schema(required = true, description = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: - BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials. - OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. - TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
	@NotNull

	public List<AuthTypeEnum> getAuthType() {
		return authType;
	}

	public void setAuthType(final List<AuthTypeEnum> authType) {
		this.authType = authType;
	}

	public SubscriptionAuthentication paramsBasic(final SubscriptionAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
		return this;
	}

	/**
	 * Get paramsBasic
	 *
	 * @return paramsBasic
	 **/
	@Schema(description = "")

	@Valid

	public SubscriptionAuthenticationParamsBasic getParamsBasic() {
		return paramsBasic;
	}

	public void setParamsBasic(final SubscriptionAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
	}

	public SubscriptionAuthentication paramsOauth2ClientCredentials(final SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
		return this;
	}

	/**
	 * Get paramsOauth2ClientCredentials
	 *
	 * @return paramsOauth2ClientCredentials
	 **/
	@Schema(description = "")

	@Valid

	public SubscriptionAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
		return paramsOauth2ClientCredentials;
	}

	public void setParamsOauth2ClientCredentials(final SubscriptionAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final SubscriptionAuthentication subscriptionAuthentication = (SubscriptionAuthentication) o;
		return Objects.equals(this.authType, subscriptionAuthentication.authType) &&
				Objects.equals(this.paramsBasic, subscriptionAuthentication.paramsBasic) &&
				Objects.equals(this.paramsOauth2ClientCredentials, subscriptionAuthentication.paramsOauth2ClientCredentials);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authType, paramsBasic, paramsOauth2ClientCredentials);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionAuthentication {\n");

		sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
		sb.append("    paramsBasic: ").append(toIndentedString(paramsBasic)).append("\n");
		sb.append("    paramsOauth2ClientCredentials: ").append(toIndentedString(paramsOauth2ClientCredentials)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
