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
package com.ubiqube.etsi.mano.vnfm.v261.model.faultmngt;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.Link;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Links for this resource.
 */
@ApiModel(description = "Links for this resource. ")
@Validated


public class AlarmLinks {
	@JsonProperty("self")
	private Link self = null;

	@JsonProperty("objectInstance")
	private Link objectInstance = null;

	public AlarmLinks self(final Link self) {
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

	public AlarmLinks objectInstance(final Link objectInstance) {
		this.objectInstance = objectInstance;
		return this;
	}

	/**
	 * Link to the resource representing the VNF instance to which the notified
	 * alarm is correlated. Shall be present if the VNF instance information is
	 * accessible as a resource.
	 * 
	 * @return objectInstance
	 **/
	@ApiModelProperty(value = "Link to the resource representing the VNF instance to which the notified alarm is correlated. Shall be present if the VNF instance information is accessible as a resource. ")

	@Valid

	public Link getObjectInstance() {
		return objectInstance;
	}

	public void setObjectInstance(final Link objectInstance) {
		this.objectInstance = objectInstance;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AlarmLinks alarmLinks = (AlarmLinks) o;
		return Objects.equals(this.self, alarmLinks.self) &&
				Objects.equals(this.objectInstance, alarmLinks.objectInstance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(self, objectInstance);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AlarmLinks {\n");

		sb.append("    self: ").append(toIndentedString(self)).append("\n");
		sb.append("    objectInstance: ").append(toIndentedString(objectInstance)).append("\n");
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
