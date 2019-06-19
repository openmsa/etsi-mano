package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parameters for authentication/authorization using BASIC. Shall be present if authType is \&quot;BASIC\&quot; and the contained information has not been provisioned out of band. Shall be absent otherwise.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. ")

public class SubscriptionsAuthenticationParamsBasic {

	private @Valid String userName = null;
	private @Valid String password = null;

	/**
	 * Username to be used in HTTP Basic authentication. Shall be present if it has
	 * not been provisioned out of band.
	 **/
	public SubscriptionsAuthenticationParamsBasic userName(String userName) {
		this.userName = userName;
		return this;
	}

	@ApiModelProperty(value = "Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
	@JsonProperty("userName")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Password to be used in HTTP Basic authentication. Shall be present if it has
	 * not been provisioned out of band.
	 **/
	public SubscriptionsAuthenticationParamsBasic password(String password) {
		this.password = password;
		return this;
	}

	@ApiModelProperty(value = "Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final SubscriptionsAuthenticationParamsBasic subscriptionsAuthenticationParamsBasic = (SubscriptionsAuthenticationParamsBasic) o;
		return Objects.equals(userName, subscriptionsAuthenticationParamsBasic.userName) &&
				Objects.equals(password, subscriptionsAuthenticationParamsBasic.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, password);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SubscriptionsAuthenticationParamsBasic {\n");

		sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
