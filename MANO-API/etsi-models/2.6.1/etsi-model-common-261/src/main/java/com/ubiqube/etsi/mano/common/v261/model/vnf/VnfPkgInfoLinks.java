package com.ubiqube.etsi.mano.common.v261.model.vnf;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource. ")
@Validated
public class VnfPkgInfoLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("vnfd")
	private Link vnfd = null;

	@JsonProperty("packageContent")
	private Link packageContent = null;

	public VnfPkgInfoLinks self(final Link self) {
		this.self = self;
		return this;
	}

	/**
	 * URI of this resource.
	 *
	 * @return self
	 **/
	@ApiModelProperty(required = true, value = "URI of this resource. ")
	@NotNull

	@Valid

	public Link getSelf() {
		return self;
	}

	public void setSelf(final Link self) {
		this.self = self;
	}

	public VnfPkgInfoLinks vnfd(final Link vnfd) {
		this.vnfd = vnfd;
		return this;
	}

	/**
	 * Link to the VNFD resource.
	 *
	 * @return vnfd
	 **/
	@ApiModelProperty(value = "Link to the VNFD resource. ")

	@Valid

	public Link getVnfd() {
		return vnfd;
	}

	public void setVnfd(final Link vnfd) {
		this.vnfd = vnfd;
	}

	public VnfPkgInfoLinks packageContent(final Link packageContent) {
		this.packageContent = packageContent;
		return this;
	}

	/**
	 * Link to the \"VNF package content\" resource.
	 *
	 * @return packageContent
	 **/
	@ApiModelProperty(required = true, value = "Link to the \"VNF package content\" resource. ")
	@NotNull

	@Valid

	public Link getPackageContent() {
		return packageContent;
	}

	public void setPackageContent(final Link packageContent) {
		this.packageContent = packageContent;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfPkgInfoLinks vnfPkgInfoLinks = (VnfPkgInfoLinks) o;
		return Objects.equals(this.self, vnfPkgInfoLinks.self) &&
				Objects.equals(this.vnfd, vnfPkgInfoLinks.vnfd) &&
				Objects.equals(this.packageContent, vnfPkgInfoLinks.packageContent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, vnfd, packageContent);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfPkgInfoLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    vnfd: ").append(toIndentedString(vnfd)).append("\n");
		sb.append("    packageContent: ").append(toIndentedString(packageContent)).append("\n");
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
