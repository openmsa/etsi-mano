package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * \"The payload body contains the address information based on which the NFVO
 * can obtain the content of the VNF package\"
 **/
@ApiModel(description = "\"The payload body contains the address information based on which the NFVO can obtain the content of the VNF package\" ")
public class VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest {

	@ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 **/
	private Map<String, Object> userDefinedData = null;

	/**
	 * This type represents a list of key-value pairs. The order of the pairs in the
	 * list is not significant. In JSON, a set of key- value pairs is represented as
	 * an object. It shall comply with the provisions defined in clause 4 of IETF
	 * RFC 7159.
	 *
	 * @return userDefinedData
	 **/
	@JsonProperty("userDefinedData")
	public Map<String, Object> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, Object> _userDefinedData) {
		this.userDefinedData = _userDefinedData;
	}

	public VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest userDefinedData(final Map<String, Object> _userDefinedData) {
		this.userDefinedData = _userDefinedData;
		return this;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPackagesvnfPkgIdpackageContentuploadFromUriUploadVnfPkgFromUriRequest {\n");

		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
