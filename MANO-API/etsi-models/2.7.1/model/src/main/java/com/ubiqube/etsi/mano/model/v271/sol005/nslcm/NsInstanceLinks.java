/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvo.v271.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource.")
@Validated
public class NsInstanceLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("nestedNsInstances")
	@Valid
	private List<Link> nestedNsInstances = null;

	@JsonProperty("instantiate")
	private Link instantiate = null;

	@JsonProperty("terminate")
	private Link terminate = null;

	@JsonProperty("update")
	private Link update = null;

	@JsonProperty("scale")
	private Link scale = null;

	@JsonProperty("heal")
	private Link heal = null;

	public NsInstanceLinks self(final Link self) {
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

	public NsInstanceLinks nestedNsInstances(final List<Link> nestedNsInstances) {
		this.nestedNsInstances = nestedNsInstances;
		return this;
	}

	public NsInstanceLinks addNestedNsInstancesItem(final Link nestedNsInstancesItem) {
		if (this.nestedNsInstances == null) {
			this.nestedNsInstances = new ArrayList<>();
		}
		this.nestedNsInstances.add(nestedNsInstancesItem);
		return this;
	}

	/**
	 * Links to resources related to this notification.
	 *
	 * @return nestedNsInstances
	 **/
	@ApiModelProperty(value = "Links to resources related to this notification. ")
	@Valid
	public List<Link> getNestedNsInstances() {
		return nestedNsInstances;
	}

	public void setNestedNsInstances(final List<Link> nestedNsInstances) {
		this.nestedNsInstances = nestedNsInstances;
	}

	public NsInstanceLinks instantiate(final Link instantiate) {
		this.instantiate = instantiate;
		return this;
	}

	/**
	 * Get instantiate
	 *
	 * @return instantiate
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Link getInstantiate() {
		return instantiate;
	}

	public void setInstantiate(final Link instantiate) {
		this.instantiate = instantiate;
	}

	public NsInstanceLinks terminate(final Link terminate) {
		this.terminate = terminate;
		return this;
	}

	/**
	 * Get terminate
	 *
	 * @return terminate
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Link getTerminate() {
		return terminate;
	}

	public void setTerminate(final Link terminate) {
		this.terminate = terminate;
	}

	public NsInstanceLinks update(final Link update) {
		this.update = update;
		return this;
	}

	/**
	 * Get update
	 *
	 * @return update
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Link getUpdate() {
		return update;
	}

	public void setUpdate(final Link update) {
		this.update = update;
	}

	public NsInstanceLinks scale(final Link scale) {
		this.scale = scale;
		return this;
	}

	/**
	 * Get scale
	 *
	 * @return scale
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Link getScale() {
		return scale;
	}

	public void setScale(final Link scale) {
		this.scale = scale;
	}

	public NsInstanceLinks heal(final Link heal) {
		this.heal = heal;
		return this;
	}

	/**
	 * Get heal
	 *
	 * @return heal
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Link getHeal() {
		return heal;
	}

	public void setHeal(final Link heal) {
		this.heal = heal;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsInstanceLinks nsInstanceLinks = (NsInstanceLinks) o;
		return Objects.equals(this.self, nsInstanceLinks.self) &&
				Objects.equals(this.nestedNsInstances, nsInstanceLinks.nestedNsInstances) &&
				Objects.equals(this.instantiate, nsInstanceLinks.instantiate) &&
				Objects.equals(this.terminate, nsInstanceLinks.terminate) &&
				Objects.equals(this.update, nsInstanceLinks.update) &&
				Objects.equals(this.scale, nsInstanceLinks.scale) &&
				Objects.equals(this.heal, nsInstanceLinks.heal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, nestedNsInstances, instantiate, terminate, update, scale, heal);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsInstanceLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    nestedNsInstances: ").append(toIndentedString(nestedNsInstances)).append("\n");
		sb.append("    instantiate: ").append(toIndentedString(instantiate)).append("\n");
		sb.append("    terminate: ").append(toIndentedString(terminate)).append("\n");
		sb.append("    update: ").append(toIndentedString(update)).append("\n");
		sb.append("    scale: ").append(toIndentedString(scale)).append("\n");
		sb.append("    heal: ").append(toIndentedString(heal)).append("\n");
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
