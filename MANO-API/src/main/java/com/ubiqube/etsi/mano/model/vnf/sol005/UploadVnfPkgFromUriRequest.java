package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * \&quot;The payload body contains the address information based on which the
 * NFVO can obtain the content of the VNF package\&quot;
 */
@ApiModel(description = "\"The payload body contains the address information based on which the NFVO can obtain the content of the VNF package\" ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T10:00:04.549+01:00")

public class UploadVnfPkgFromUriRequest {
	@JsonProperty("userDefinedData")
	private KeyValuePairs userDefinedData = null;

	public UploadVnfPkgFromUriRequest userDefinedData(final KeyValuePairs userDefinedData) {
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

	public KeyValuePairs getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final KeyValuePairs userDefinedData) {
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
		final UploadVnfPkgFromUriRequest uploadVnfPkgFromUriRequest = (UploadVnfPkgFromUriRequest) o;
		return Objects.equals(this.userDefinedData, uploadVnfPkgFromUriRequest.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class UploadVnfPkgFromUriRequest {\n");

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
