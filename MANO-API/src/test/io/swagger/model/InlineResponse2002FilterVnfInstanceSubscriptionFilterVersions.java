package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions {

	private @Valid String vnfSoftwareVersion = null;
	private @Valid List<String> vnfdVersions = new ArrayList<String>();

	/**
	 * A version.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions vnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	@ApiModelProperty(required = true, value = "A version. ")
	@JsonProperty("vnfSoftwareVersion")
	@NotNull
	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain VNFD
	 * versions, a certain software version and a certain product name, from one
	 * particular provider.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions vnfdVersions(List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")
	@JsonProperty("vnfdVersions")
	public List<String> getVnfdVersions() {
		return vnfdVersions;
	}

	public void setVnfdVersions(List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions inlineResponse2002FilterVnfInstanceSubscriptionFilterVersions = (InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions) o;
		return Objects.equals(vnfSoftwareVersion, inlineResponse2002FilterVnfInstanceSubscriptionFilterVersions.vnfSoftwareVersion) &&
				Objects.equals(vnfdVersions, inlineResponse2002FilterVnfInstanceSubscriptionFilterVersions.vnfdVersions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfSoftwareVersion, vnfdVersions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions {\n");

		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
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
