package io.swagger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts {

	private @Valid String vnfProductName = null;
	private @Valid List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions> versions = new ArrayList<InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions>();

	/**
	 * Name of the VNF product to match.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts vnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
		return this;
	}

	@ApiModelProperty(required = true, value = "Name of the VNF product to match. ")
	@JsonProperty("vnfProductName")
	@NotNull
	public String getVnfProductName() {
		return vnfProductName;
	}

	public void setVnfProductName(String vnfProductName) {
		this.vnfProductName = vnfProductName;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain
	 * versions and a certain product name, from one particular provider.
	 **/
	public InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts versions(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions> versions) {
		this.versions = versions;
		return this;
	}

	@ApiModelProperty(value = "If present, match VNF instances that belong to VNF products with certain versions and a certain product name, from one particular provider. ")
	@JsonProperty("versions")
	public List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions> getVersions() {
		return versions;
	}

	public void setVersions(List<InlineResponse2002FilterVnfInstanceSubscriptionFilterVersions> versions) {
		this.versions = versions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts = (InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts) o;
		return Objects.equals(vnfProductName, inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts.vnfProductName) &&
				Objects.equals(versions, inlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts.versions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProductName, versions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class InlineResponse2002FilterVnfInstanceSubscriptionFilterVnfProducts {\n");

		sb.append("    vnfProductName: ").append(toIndentedString(vnfProductName)).append("\n");
		sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
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
