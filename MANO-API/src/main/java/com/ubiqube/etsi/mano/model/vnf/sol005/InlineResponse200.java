package com.ubiqube.etsi.mano.model.vnf.sol005;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * InlineResponse200
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T10:00:04.549+01:00")

public class InlineResponse200 {
	@JsonProperty("VnfPkgInfo")
	private VnfPkgInfo vnfPkgInfo = null;

	public InlineResponse200 vnfPkgInfo(final VnfPkgInfo vnfPkgInfo) {
		this.vnfPkgInfo = vnfPkgInfo;
		return this;
	}

	/**
	 * Get vnfPkgInfo
	 * 
	 * @return vnfPkgInfo
	 **/
	@ApiModelProperty(value = "")

	@Valid

	public VnfPkgInfo getVnfPkgInfo() {
		return vnfPkgInfo;
	}

	public void setVnfPkgInfo(final VnfPkgInfo vnfPkgInfo) {
		this.vnfPkgInfo = vnfPkgInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse200 inlineResponse200 = (InlineResponse200) o;
		return Objects.equals(this.vnfPkgInfo, inlineResponse200.vnfPkgInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfPkgInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse200 {\n");

		sb.append("    vnfPkgInfo: ").append(toIndentedString(vnfPkgInfo)).append("\n");
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
