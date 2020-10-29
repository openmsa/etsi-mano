/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type creates a completely new NS descriptor resource.
 */
@ApiModel(description = "This type creates a completely new NS descriptor resource. ")
@Validated


public class CreateNsdInfoRequest {
	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	public CreateNsdInfoRequest userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 *
	 * @return userDefinedData
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CreateNsdInfoRequest createNsdInfoRequest = (CreateNsdInfoRequest) o;
		return Objects.equals(this.userDefinedData, createNsdInfoRequest.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateNsdInfoRequest {\n");

		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
