package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

public class SubscriptionsAuthentication {

	public enum AuthTypeEnum {

		BASIC(String.valueOf("BASIC")), OAUTH2_CLIENT_CREDENTIALS(String.valueOf("OAUTH2_CLIENT_CREDENTIALS")), TLS_CERT(String.valueOf("TLS_CERT"));

		private final String value;

		AuthTypeEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static AuthTypeEnum fromValue(String v) {
			for (final AuthTypeEnum b : AuthTypeEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}

	private @Valid List<AuthTypeEnum> authType = new ArrayList<AuthTypeEnum>();
	private @Valid SubscriptionsAuthenticationParamsBasic paramsBasic = null;
	private @Valid SubscriptionsAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials = null;

	/**
	 * Defines the types of Authentication / Authorization which the API consumer is
	 * willing to accept when receiving a notification. Permitted values: * BASIC:
	 * In every HTTP request to the notification endpoint, use HTTP Basic
	 * authentication with the client credentials. * OAUTH2_CLIENT_CREDENTIALS: In
	 * every HTTP request to the notification endpoint, use an OAuth 2.0 Bearer
	 * token, obtained using the client credentials grant type. * TLS_CERT: Every
	 * HTTP request to the notification endpoint is sent over a mutually
	 * authenticated TLS session, i.e. not only the server is authenticated, but
	 * also the client is authenticated during the TLS tunnel setup.
	 **/
	public SubscriptionsAuthentication authType(List<AuthTypeEnum> authType) {
		this.authType = authType;
		return this;
	}

	@ApiModelProperty(required = true, value = "Defines the types of Authentication / Authorization which the API consumer is willing to accept when receiving a notification. Permitted values: * BASIC: In every HTTP request to the notification endpoint, use   HTTP Basic authentication with the client credentials.  * OAUTH2_CLIENT_CREDENTIALS: In every HTTP request to the   notification endpoint, use an OAuth 2.0 Bearer token, obtained   using the client credentials grant type. * TLS_CERT: Every HTTP request to the notification endpoint is sent   over a mutually authenticated TLS session, i.e. not only the   server is authenticated, but also the client is authenticated   during the TLS tunnel setup. ")
	@JsonProperty("authType")
	@NotNull
	public List<AuthTypeEnum> getAuthType() {
		return authType;
	}

	public void setAuthType(List<AuthTypeEnum> authType) {
		this.authType = authType;
	}

	/**
	 **/
	public SubscriptionsAuthentication paramsBasic(SubscriptionsAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("paramsBasic")
	public SubscriptionsAuthenticationParamsBasic getParamsBasic() {
		return paramsBasic;
	}

	public void setParamsBasic(SubscriptionsAuthenticationParamsBasic paramsBasic) {
		this.paramsBasic = paramsBasic;
	}

	/**
	 **/
	public SubscriptionsAuthentication paramsOauth2ClientCredentials(SubscriptionsAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
		return this;
	}

	@ApiModelProperty(value = "")
	@JsonProperty("paramsOauth2ClientCredentials")
	public SubscriptionsAuthenticationParamsOauth2ClientCredentials getParamsOauth2ClientCredentials() {
		return paramsOauth2ClientCredentials;
	}

	public void setParamsOauth2ClientCredentials(SubscriptionsAuthenticationParamsOauth2ClientCredentials paramsOauth2ClientCredentials) {
		this.paramsOauth2ClientCredentials = paramsOauth2ClientCredentials;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final SubscriptionsAuthentication subscriptionsAuthentication = (SubscriptionsAuthentication) o;
		return Objects.equals(authType, subscriptionsAuthentication.authType) &&
				Objects.equals(paramsBasic, subscriptionsAuthentication.paramsBasic) &&
				Objects.equals(paramsOauth2ClientCredentials, subscriptionsAuthentication.paramsOauth2ClientCredentials);
	}

	@Override
	public int hashCode() {
		return Objects.hash(authType, paramsBasic, paramsOauth2ClientCredentials);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsAuthentication {\n");

		sb.append("    authType: ").append(toIndentedString(authType)).append("\n");
		sb.append("    paramsBasic: ").append(toIndentedString(paramsBasic)).append("\n");
		sb.append("    paramsOauth2ClientCredentials: ").append(toIndentedString(paramsOauth2ClientCredentials)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
