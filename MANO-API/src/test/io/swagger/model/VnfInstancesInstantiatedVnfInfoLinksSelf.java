package io.swagger.model;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This type represents a link to a resource.
 **/
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "This type represents a link to a resource. ")

public class VnfInstancesInstantiatedVnfInfoLinksSelf {

	private @Valid String href = null;

	/**
	 * URI of the referenced resource.
	 **/
	public VnfInstancesInstantiatedVnfInfoLinksSelf href(String href) {
		this.href = href;
		return this;
	}

	@ApiModelProperty(required = true, value = "URI of the referenced resource. ")
	@JsonProperty("href")
	@NotNull
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstancesInstantiatedVnfInfoLinksSelf vnfInstancesInstantiatedVnfInfoLinksSelf = (VnfInstancesInstantiatedVnfInfoLinksSelf) o;
		return Objects.equals(href, vnfInstancesInstantiatedVnfInfoLinksSelf.href);
	}

	@Override
	public int hashCode() {
		return Objects.hash(href);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstancesInstantiatedVnfInfoLinksSelf {\n");

		sb.append("    href: ").append(toIndentedString(href)).append("\n");
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
