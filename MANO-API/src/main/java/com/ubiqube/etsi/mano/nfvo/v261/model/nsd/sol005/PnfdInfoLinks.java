package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvo.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-11-25T16:34:13.188+01:00")

public class PnfdInfoLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("pnfd_content")
	private Link pnfdContent = null;

	public PnfdInfoLinks self(final Link self) {
		this.self = self;
		return this;
	}

	/**
	 * Get self
	 *
	 * @return self
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Link getSelf() {
		return self;
	}

	public void setSelf(final Link self) {
		this.self = self;
	}

	public PnfdInfoLinks pnfdContent(final Link pnfdContent) {
		this.pnfdContent = pnfdContent;
		return this;
	}

	/**
	 * Get pnfdContent
	 *
	 * @return pnfdContent
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Link getPnfdContent() {
		return pnfdContent;
	}

	public void setPnfdContent(final Link pnfdContent) {
		this.pnfdContent = pnfdContent;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfdInfoLinks pnfdInfoLinks = (PnfdInfoLinks) o;
		return Objects.equals(this.self, pnfdInfoLinks.self) &&
				Objects.equals(this.pnfdContent, pnfdInfoLinks.pnfdContent);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, pnfdContent);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdInfoLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    pnfdContent: ").append(toIndentedString(pnfdContent)).append("\n");
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
