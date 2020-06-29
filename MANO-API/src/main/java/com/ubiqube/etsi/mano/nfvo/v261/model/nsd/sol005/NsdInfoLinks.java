package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:34:13.188+01:00")

public class NsdInfoLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("nsd_content")
	private Link nsdContent = null;

	public NsdInfoLinks self(final Link self) {
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

	public NsdInfoLinks nsdContent(final Link nsdContent) {
		this.nsdContent = nsdContent;
		return this;
	}

	/**
	 * Link to the NSD content resource.
	 *
	 * @return nsdContent
	 **/
	@ApiModelProperty(required = true, value = "Link to the NSD content resource. ")
	@NotNull

	@Valid

	public Link getNsdContent() {
		return nsdContent;
	}

	public void setNsdContent(final Link nsdContent) {
		this.nsdContent = nsdContent;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsdInfoLinks nsdInfoLinks = (NsdInfoLinks) o;
		return Objects.equals(this.self, nsdInfoLinks.self) &&
				Objects.equals(this.nsdContent, nsdInfoLinks.nsdContent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, nsdContent);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdInfoLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    nsdContent: ").append(toIndentedString(nsdContent)).append("\n");
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
