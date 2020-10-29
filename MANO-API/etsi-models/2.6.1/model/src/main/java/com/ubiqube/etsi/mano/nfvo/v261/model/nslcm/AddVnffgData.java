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
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies the parameters used for the creation of a new VNFFG
 * instance. It shall comply with the provisions defined in Table 6.5.3.36-1.
 */
@ApiModel(description = "This type specifies the parameters used for the creation of a new VNFFG instance. It shall comply with the provisions defined in Table 6.5.3.36-1. ")
@Validated


public class AddVnffgData {
	@JsonProperty("targetNsInstanceId")
	private String targetNsInstanceId = null;

	@JsonProperty("vnffgName")
	private String vnffgName = null;

	@JsonProperty("description")
	private String description = null;

	public AddVnffgData targetNsInstanceId(final String targetNsInstanceId) {
		this.targetNsInstanceId = targetNsInstanceId;
		return this;
	}

	/**
	 * Identifier of the VNFFGD used to create this VNFFG instance.
	 * 
	 * @return targetNsInstanceId
	 **/
	@ApiModelProperty(value = "Identifier of the VNFFGD used to create this VNFFG instance. ")

	public String getTargetNsInstanceId() {
		return targetNsInstanceId;
	}

	public void setTargetNsInstanceId(final String targetNsInstanceId) {
		this.targetNsInstanceId = targetNsInstanceId;
	}

	public AddVnffgData vnffgName(final String vnffgName) {
		this.vnffgName = vnffgName;
		return this;
	}

	/**
	 * Human readable name for the VNFFG.
	 * 
	 * @return vnffgName
	 **/
	@ApiModelProperty(required = true, value = "Human readable name for the VNFFG. ")
	@NotNull

	public String getVnffgName() {
		return vnffgName;
	}

	public void setVnffgName(final String vnffgName) {
		this.vnffgName = vnffgName;
	}

	public AddVnffgData description(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * Human readable description for the VNFFG.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(required = true, value = "Human readable description for the VNFFG. ")
	@NotNull

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AddVnffgData addVnffgData = (AddVnffgData) o;
		return Objects.equals(this.targetNsInstanceId, addVnffgData.targetNsInstanceId) &&
				Objects.equals(this.vnffgName, addVnffgData.vnffgName) &&
				Objects.equals(this.description, addVnffgData.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(targetNsInstanceId, vnffgName, description);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AddVnffgData {\n");

		sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
		sb.append("    vnffgName: ").append(toIndentedString(vnffgName)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
