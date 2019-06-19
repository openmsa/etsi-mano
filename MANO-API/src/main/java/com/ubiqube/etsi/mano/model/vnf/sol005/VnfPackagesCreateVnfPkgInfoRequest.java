package com.ubiqube.etsi.mano.model.vnf.sol005;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IndividualVNF package resource creation parameters, as defined in clause 9.5.2.2.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "IndividualVNF package resource creation parameters, as defined in clause 9.5.2.2.       ")

public class VnfPackagesCreateVnfPkgInfoRequest {

	private @Valid Object userDefinedData = null;

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	public VnfPackagesCreateVnfPkgInfoRequest userDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	@JsonProperty("userDefinedData")
	public Object getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(Object userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackagesCreateVnfPkgInfoRequest {\n");

		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
