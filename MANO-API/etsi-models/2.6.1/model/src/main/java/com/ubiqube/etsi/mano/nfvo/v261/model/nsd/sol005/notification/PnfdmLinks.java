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
package com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.notification;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the links to resources that a PNFD management
 * notification can contain.
 */
@ApiModel(description = "This type represents the links to resources that a PNFD management notification can contain.")
@Validated


public class PnfdmLinks {
	@JsonProperty("pnfdInfo")
	private Link pnfdInfo = null;

	@JsonProperty("subscription")
	private Link subscription = null;

	public PnfdmLinks pnfdInfo(final Link pnfdInfo) {
		this.pnfdInfo = pnfdInfo;
		return this;
	}

	/**
	 * Get pnfdInfo
	 * 
	 * @return pnfdInfo
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Link getPnfdInfo() {
		return pnfdInfo;
	}

	public void setPnfdInfo(final Link pnfdInfo) {
		this.pnfdInfo = pnfdInfo;
	}

	public PnfdmLinks subscription(final Link subscription) {
		this.subscription = subscription;
		return this;
	}

	/**
	 * Get subscription
	 * 
	 * @return subscription
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public Link getSubscription() {
		return subscription;
	}

	public void setSubscription(final Link subscription) {
		this.subscription = subscription;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfdmLinks pnfdmLinks = (PnfdmLinks) o;
		return Objects.equals(this.pnfdInfo, pnfdmLinks.pnfdInfo) &&
				Objects.equals(this.subscription, pnfdmLinks.subscription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pnfdInfo, subscription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfdmLinks {\n");

		sb.append("    pnfdInfo: ").append(toIndentedString(pnfdInfo)).append("\n");
		sb.append("    subscription: ").append(toIndentedString(subscription)).append("\n");
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
