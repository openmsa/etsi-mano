package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type specifies existing VNF instances to be moved from one NS instance
 * (source) to another NS instance (destination). The NS instance defined in the
 * Update NS operation indicates the source NS instance and the destination NS
 * instance is specified in this data type (referred to targetNsInstanceId). It
 * shall comply with the provisions defined in Table 6.5.3.35-1.
 */
@ApiModel(description = "This type specifies existing VNF instances to be moved from one NS instance (source) to another NS instance (destination). The NS instance defined in the Update NS operation indicates the source NS instance and the destination NS instance is specified in this data type (referred to targetNsInstanceId). It shall comply with the provisions defined in Table 6.5.3.35-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-12-05T16:49:58.135+01:00")

public class MoveVnfInstanceData {
	@JsonProperty("targetNsInstanceId")
	private String targetNsInstanceId = null;

	@JsonProperty("vnfInstanceId")
	@Valid
	private List<String> vnfInstanceId = null;

	public MoveVnfInstanceData targetNsInstanceId(final String targetNsInstanceId) {
		this.targetNsInstanceId = targetNsInstanceId;
		return this;
	}

	/**
	 * Specify the target NS instance where the VNF instances are moved to.
	 * 
	 * @return targetNsInstanceId
	 **/
	@ApiModelProperty(required = true, value = "Specify the target NS instance where the VNF instances are moved to. ")
	@NotNull

	public String getTargetNsInstanceId() {
		return targetNsInstanceId;
	}

	public void setTargetNsInstanceId(final String targetNsInstanceId) {
		this.targetNsInstanceId = targetNsInstanceId;
	}

	public MoveVnfInstanceData vnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	public MoveVnfInstanceData addVnfInstanceIdItem(final String vnfInstanceIdItem) {
		if (this.vnfInstanceId == null) {
			this.vnfInstanceId = new ArrayList<>();
		}
		this.vnfInstanceId.add(vnfInstanceIdItem);
		return this;
	}

	/**
	 * Specify the VNF instance that is moved.
	 * 
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(value = "Specify the VNF instance that is moved. ")

	public List<String> getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final List<String> vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final MoveVnfInstanceData moveVnfInstanceData = (MoveVnfInstanceData) o;
		return Objects.equals(this.targetNsInstanceId, moveVnfInstanceData.targetNsInstanceId) &&
				Objects.equals(this.vnfInstanceId, moveVnfInstanceData.vnfInstanceId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(targetNsInstanceId, vnfInstanceId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class MoveVnfInstanceData {\n");

		sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
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
