/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
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
