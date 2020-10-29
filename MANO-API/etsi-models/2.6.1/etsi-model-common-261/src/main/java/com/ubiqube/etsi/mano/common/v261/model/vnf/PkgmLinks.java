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
 * This type represents the links to resources that a VNF package management
 * notification can contain.
 */
@ApiModel(description = "This type represents the links to resources that a VNF package management notification can contain.   ")
@Validated
public class PkgmLinks {
	@JsonProperty("vnfPackage")
	private Link vnfPackage = null;

	@JsonProperty("subscription")
	private Link subscription = null;

	public PkgmLinks vnfPackage(final Link vnfPackage) {
		this.vnfPackage = vnfPackage;
		return this;
	}

	/**
	 * Link to the resource representing the VNF package to which the notified
	 * change applies, i.e. the individual on boarded VNF package resource that
	 * represents the VNF package.
	 *
	 * @return vnfPackage
	 **/
	@ApiModelProperty(required = true, value = "Link to the resource representing the VNF package to which the notified change applies, i.e. the individual on boarded VNF package resource that represents the VNF package. ")
	@NotNull

	@Valid

	public Link getVnfPackage() {
		return vnfPackage;
	}

	public void setVnfPackage(final Link vnfPackage) {
		this.vnfPackage = vnfPackage;
	}

	public PkgmLinks subscription(final Link subscription) {
		this.subscription = subscription;
		return this;
	}

	/**
	 * Link to the related subscription.
	 *
	 * @return subscription
	 **/
	@ApiModelProperty(required = true, value = "Link to the related subscription. ")
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
		final PkgmLinks pkgmLinks = (PkgmLinks) o;
		return Objects.equals(this.vnfPackage, pkgmLinks.vnfPackage) &&
				Objects.equals(this.subscription, pkgmLinks.subscription);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfPackage, subscription);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PkgmLinks {\n");

		sb.append("    vnfPackage: ").append(toIndentedString(vnfPackage)).append("\n");
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
